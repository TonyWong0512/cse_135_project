package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import util.DbUtil;

public class ProductDao {
	private Connection connection;
	
	public ProductDao() {
        connection = DbUtil.getConnection();
    }
	
 	public int addProduct(Product product) {
 		int result = 0;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into products(name, sku, price, category) values(?, ?, ?, ?)");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getSKU());
            preparedStatement.setBigDecimal(3, product.getPrice());
            preparedStatement.setInt(4, product.getCategory());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
 	
	public List<Product> getProduct(String name) {
        List<Product> result = new ArrayList<Product>();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from products where name = '?'");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            toList(result, rs);
            rs.close();
			ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

	private void toList(List<Product> result, ResultSet rs) throws SQLException {
		while (rs.next()) {
		    Product p = new Product();
		    p.setID(rs.getInt("id"));
		    p.setName(rs.getString("name"));
		    p.setSKU(rs.getString("SKU"));
		    p.setPrice(rs.getBigDecimal("price"));
		    p.setCategory(rs.getInt("category"));
		    result.add(p);
		}
	}
	
	public List<Product> getAllProducts() {
        List<Product> result = new ArrayList<Product>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from products");
            toList(result, rs);
            rs.close();
			statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

}
