package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;
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
					.prepareStatement("SELECT state, SUM(sales) AS sales FROM sales_by_state "
							+ seasonCondition
							+ "GROUP BY state ORDER BY state LIMIT 10 OFFSET ?;");
			preparedStatement.setInt(1, offset);
			result = preparedStatement.executeQuery();
			while (result.next()) {
				SalesByState sale = new SalesByState();
				sale.setSales(result.getInt("sales"));
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

	public List<SalesByProduct> getSalesByProduct(String customer,
			String state, String season, int offset) {
		ResultSet result = null;
		List<SalesByProduct> sales = new ArrayList<SalesByProduct>();
		try {
			String seasonCondition = "";
			if (season != null && season.trim() != "") {
				if (customer != null && customer.trim() != "") {
					if (state != null && state.trim() != "") {
						// season, customer and state
						seasonCondition = "WHERE season='" + season
								+ "' AND state=" + state.trim() + " AND "
								+ customer.trim() + " ";
					} else {
						// season and customer
						seasonCondition = "WHERE season='" + season + "' AND "
								+ customer.trim() + " ";
					}
				} else {
					if (state != null && state.trim() != "") {
						// season and state
						seasonCondition = "WHERE season='" + season
								+ "' AND state=" + state.trim() + " ";
					} else {
						// season
						seasonCondition = "WHERE season='" + season + "' ";
					}
				}
			} else if (customer != null && customer.trim() != "") {
				if (state != null && state.trim() != "") {
					// customer and state
					seasonCondition = "WHERE customer=" + customer.trim()
							+ " AND state=" + state.trim() + " ";
				} else {
					// customer
					seasonCondition = "WHERE customer=" + customer.trim() + " ";
				}
			} else if (state != null && state.trim() != "") {
				// state
				seasonCondition = "WHERE state=" + state.trim() + " ";
			}

			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM sales_by_product "
							+ seasonCondition
							+ "ORDER BY sales LIMIT 10 OFFSET ?;");
			preparedStatement.setInt(1, offset);
			System.out.println(preparedStatement.toString());
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
					.prepareStatement("SELECT customer, season, SUM(sales) as sales FROM sales_by_customer "
							+ seasonCondition
							+ "GROUP BY season, customer, sales ORDER BY sales DESC LIMIT 10 OFFSET ?;");
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

	public List<SalesByProduct> getProducts(String state, String season,
			int offset) {
		ResultSet result = null;
		List<SalesByProduct> sales = new ArrayList<SalesByProduct>();
		try {
			String seasonCondition = "";
			if (season != null && season.trim() != "") {
				if (state != null && state.trim() != "") {
					// season and state
					seasonCondition = "WHERE season='" + season
							+ "' AND state=" + state.trim() + " ";
				} else {
					// season
					seasonCondition = "WHERE season='" + season + " ";
				}
			} else {

				if (state != null && state.trim() != "") {
					// state
					seasonCondition = "WHERE state=" + state.trim() + " ";
				} else{
					// none
					seasonCondition = " ";
				}
			}

			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT sku, SUM(sales) AS sales FROM sales_by_product "
							+ seasonCondition
							+ "GROUP BY sku ORDER BY sales DESC LIMIT 10 OFFSET ?;");
			preparedStatement.setInt(1, offset);
			result = preparedStatement.executeQuery();
			while (result.next()) {
				SalesByProduct sale = new SalesByProduct();
				sale.setSales(result.getInt("sales"));
				
				ProductDao pdao = new ProductDao();
				String sku = result.getString("sku");
				Product product = pdao.getProductByIdReturnProd(Integer.parseInt(sku));
				sale.setProduct(product);
				sales.add(sale);
			}
			result.close();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sales;
	}
	
	public SalesByProduct getSalesByCustomerAndProduct(User customer, Product product) {
		ResultSet result = null;
		SalesByProduct sale = new SalesByProduct();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT SUM(sales) AS sales FROM sales_by_product WHERE customer = ? AND sku = ? GROUP BY sku;");
			preparedStatement.setInt(1, customer.getId());
			preparedStatement.setInt(2, product.getId());
			result = preparedStatement.executeQuery();
			
			while (result.next()) {				
				sale.setSales(result.getInt("sales"));
			}
			result.close();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sale;
		
	}
	
	/*
	 * public int addProduct(Product product) { int result = 0; try {
	 * PreparedStatement preparedStatement = connection .prepareStatement(
	 * "insert into products(name, sku, price, category) values(?, ?, ?, ?)");
	 * preparedStatement.setString(1, product.getName());
	 * preparedStatement.setString(2, product.getSKU());
	 * preparedStatement.setBigDecimal(3, product.getPrice());
	 * preparedStatement.setInt(4, product.getCategory()); result =
	 * preparedStatement.executeUpdate(); } catch (SQLException e) {
	 * e.printStackTrace(); } return result; }
	 * 
	 * public int deleteProduct(int id) { int result = 0; try {
	 * PreparedStatement preparedStatement = connection
	 * .prepareStatement("delete from products where id = ?");
	 * preparedStatement.setInt(1, id); result =
	 * preparedStatement.executeUpdate(); } catch (SQLException e) {
	 * e.printStackTrace(); } return result; }
	 * 
	 * public int updateProduct(int id, Product product) { int result = 0; try {
	 * PreparedStatement preparedStatement = connection .prepareStatement(
	 * "update products set name = ?, sku = ?, price = ?, category = ? where id = ?"
	 * ); preparedStatement.setString(1, product.getName());
	 * preparedStatement.setString(2, product.getSKU());
	 * preparedStatement.setBigDecimal(3, product.getPrice());
	 * preparedStatement.setInt(4, product.getCategory());
	 * preparedStatement.setInt(5, product.getId());
	 * System.out.println(preparedStatement.toString()); result =
	 * preparedStatement.executeUpdate(); } catch (SQLException e) {
	 * e.printStackTrace(); } return result; }
	 * 
	 * public List<Product> getProductByName(String name) { List<Product> result
	 * = new ArrayList<Product>(); try { PreparedStatement ps = connection
	 * .prepareStatement("select * from products where name = ?");
	 * ps.setString(1, name); ResultSet rs = ps.executeQuery(); toList(result,
	 * rs); rs.close(); ps.close(); } catch (SQLException e) {
	 * e.printStackTrace(); }
	 * 
	 * return result; }
	 * 
	 * public List<Product> getProductByCategory(int id) { List<Product> result
	 * = new ArrayList<Product>(); try { PreparedStatement ps = connection
	 * .prepareStatement(
	 * "select * from products, categories where categories.id = ? and products.category = categories.id"
	 * ); ps.setInt(1, id); ResultSet rs = ps.executeQuery(); toList(result,
	 * rs); rs.close(); ps.close(); } catch (SQLException e) {
	 * e.printStackTrace(); }
	 * 
	 * return result; }
	 * 
	 * public List<Product> getProductById(int id) { List<Product> result = new
	 * ArrayList<Product>(); try { PreparedStatement ps = connection
	 * .prepareStatement("select * from products where id = ?"); ps.setInt(1,
	 * id); ResultSet rs = ps.executeQuery(); toList(result, rs); rs.close();
	 * ps.close(); } catch (SQLException e) { e.printStackTrace(); }
	 * 
	 * return result; }
	 * 
	 * private void toList(List<Product> result, ResultSet rs) throws
	 * SQLException { while (rs.next()) { Product p = new Product();
	 * p.setId(rs.getInt("id")); p.setName(rs.getString("name"));
	 * p.setSKU(rs.getString("SKU")); p.setPrice(rs.getBigDecimal("price"));
	 * p.setCategory(rs.getInt("category")); result.add(p); } }
	 * 
	 * public List<Product> getAllProducts() { List<Product> result = new
	 * ArrayList<Product>(); try { Statement statement =
	 * connection.createStatement(); ResultSet rs =
	 * statement.executeQuery("select * from products"); toList(result, rs);
	 * rs.close(); statement.close(); } catch (SQLException e) {
	 * e.printStackTrace(); }
	 * 
	 * return result; }
	 * 
	 * public List<model.Product> getProduct(String product, int category) {
	 * List<Product> result = new ArrayList<Product>(); try { PreparedStatement
	 * ps = connection .prepareStatement(
	 * "select * from products, categories where products.name = ? and categories.id = ? and products.category = categories.id"
	 * ); ps.setString(1, product); ps.setInt(2, category); ResultSet rs =
	 * ps.executeQuery(); toList(result, rs); rs.close(); ps.close(); } catch
	 * (SQLException e) { e.printStackTrace(); } return result; }
	 */

	

}