package dao;

import java.math.BigDecimal;
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
	
	public int deleteProduct(int id) {
		int result = 0;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from products where id = ?");
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int updateProduct(int id, Product product) {
		int result = 0;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update products set name = ?, sku = ?, price = ?, category = ? where id = ?");
			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getSKU());
			preparedStatement.setBigDecimal(3, product.getPrice());
			preparedStatement.setInt(4, product.getCategory());
			preparedStatement.setInt(5, product.getId());
			System.out.println(preparedStatement.toString());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Product> getProductByName(String name) {
		List<Product> result = new ArrayList<Product>();
		try {
			PreparedStatement ps = connection
					.prepareStatement("select * from products where name = ?");
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

	public List<Product> getProductByCategory(int id) {
		List<Product> result = new ArrayList<Product>();
		try {
			PreparedStatement ps = connection
					.prepareStatement("select * from products, categories where categories.id = ? and products.category = categories.id");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			toList(result, rs);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public List<Product> getProductById(int id) {
		List<Product> result = new ArrayList<Product>();
		try {
			PreparedStatement ps = connection
					.prepareStatement("select * from products where sku = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			toList(result, rs);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
public Product getProductByIdReturnProd(int id) {
		
		
		Product p = null;
		try {
			PreparedStatement ps = connection
					.prepareStatement("select * from products where sku = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p = new Product();
				p.setId(rs.getInt("sku"));
				p.setName(rs.getString("name"));
				p.setPrice(new BigDecimal(rs.getInt("price")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	private void toList(List<Product> result, ResultSet rs) throws SQLException {
		while (rs.next()) {
			Product p = new Product();
			p.setId(rs.getInt("id"));
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

	public List<model.Product> getProduct(String product, int category) {
		List<Product> result = new ArrayList<Product>();
		try {
			PreparedStatement ps = connection
					.prepareStatement("select * from products, categories where products.name = ? and categories.id = ? and products.category = categories.id");
			ps.setString(1, product);
			ps.setInt(2, category);
			ResultSet rs = ps.executeQuery();
			toList(result, rs);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
