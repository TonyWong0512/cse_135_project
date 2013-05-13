package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
 	
 	/*
 	public int addProduct(Product p) {
 		int result = 0;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into products(name, sku, price, category) values(?, ?, ?, ?)");
            preparedStatement.setString(1, p.getName());
            preparedStatement.setString(2, p.getSKU());
            preparedStatement.setBigDecimal(3, p.getPrice());
            preparedStatement.setInt(4, p.getCategory());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    */
	
	public List<Product> getAllProducts() {
		return null;
    }
}
