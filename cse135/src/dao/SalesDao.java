package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.SalesByCustomer;
import model.SalesByProduct;
import model.SalesByState;
import model.User;
import util.DbUtil;

public class SalesDao {
	private Connection connection;

	public SalesDao() {
		connection = DbUtil.getConnection();
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public List<SalesByState> getSalesByState(String season, int offset) {
		ResultSet result = null;
		List<SalesByState> sales = new ArrayList<SalesByState>();
		try {
			String seasonCondition = "";
			if (season != null && season.trim() != "") {
				seasonCondition = "WHERE season='" + season + "' ";
			}
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM sales_by_state " + seasonCondition + "ORDER BY state LIMIT 10 OFFSET ?;");
			preparedStatement.setInt(1, offset);
			result = preparedStatement.executeQuery();
			while (result.next()) {
				SalesByState sale = new SalesByState();
				sale.setSales(result.getInt("sales"));
				sale.setSeason(result.getString("season"));
				sale.setState(result.getString("state"));
				sales.add(sale);
			}
			result.close();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sales;
	}
	
	public List<SalesByProduct> getSalesByProduct(String season, int offset) {
		ResultSet result = null;
		List<SalesByProduct> sales = new ArrayList<SalesByProduct>();
		try {
			String seasonCondition = "";
			if (season != null && season.trim() != "") {
				seasonCondition = "WHERE season='" + season + "' ";
			}
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM sales_by_product" + seasonCondition + "ORDER BY sales LIMIT 10 OFFSET ?;");
			preparedStatement.setInt(1, offset);
			result = preparedStatement.executeQuery();
			while (result.next()) {
				SalesByProduct sale = new SalesByProduct();
				sale.setSales(result.getInt("sales"));
				sale.setSeason(result.getString("season"));
				sale.setState(result.getString("state"));
				sales.add(sale);
			}
			result.close();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sales;
	}
	
	public List<SalesByCustomer> getSalesByCustomer(String season, int offset) {
		ResultSet result = null;
		List<SalesByCustomer> sales = new ArrayList<SalesByCustomer>();
		try {
			String seasonCondition = "";
			if (season != null && season.trim() != "") {
				seasonCondition = "WHERE season='" + season + "' ";
			}
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM sales_by_product" + seasonCondition + "ORDER BY sales LIMIT 10 OFFSET ?;");
			preparedStatement.setInt(1, offset);
			result = preparedStatement.executeQuery();
			while (result.next()) {
				SalesByCustomer sale = new SalesByCustomer();
				sale.setSales(result.getInt("sales"));
				sale.setSeason(result.getString("season"));
				
				UserDao udao = new UserDao();				
				sale.setCustomer(udao.getUser(result.getInt("customer")));
				sales.add(sale);
			}
			result.close();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sales;
	}
	
	/*
	public List<SalesByProduct> getProducts(int offset){
		List<SalesByProduct> products = new ArrayList<SalesByProduct>();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM sales_by_product ORDER BY sales DESC LIMIT 10 OFFSET ?;");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				SalesByProduct product = new SalesByProduct();
				products.add(product);
			}
			rs.close();
			// Close the Statement
			connection.commit();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
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
					.prepareStatement("select * from products where id = ?");
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
	*/

}