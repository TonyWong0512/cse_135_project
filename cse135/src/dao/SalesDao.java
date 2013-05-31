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

	public List<SalesByState> getSalesByState(String season, int offset,
			String state) {
		ResultSet result = null;
		List<SalesByState> sales = new ArrayList<SalesByState>();
		try {
			String seasonCondition = "";
			if (season != null && season.trim() != "") {
				// State and season
				if (state != null && state.trim() != "") {
					seasonCondition = "WHERE season='" + season
							+ "' AND state='" + state.trim() + "' ";
				} else {
					// season
					seasonCondition = "WHERE season='" + season + "' ";
				}
			} else {
				// State
				if (state != null && state.trim() != "") {
					seasonCondition = "WHERE state='" + state.trim() + "' ";
				}
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

	public List<SalesByCustomer> getSalesByCustomer(String season, int offset, String state, String category) {
		ResultSet result = null;
		List<SalesByCustomer> sales = new ArrayList<SalesByCustomer>();
		try {
			String condition = "WHERE products.sku = sales_by_product.sku AND sales_by_customer.customer=sales_by_product.customer ";
			if (state != null && state.trim() != "") {
				condition += "AND state='" + state.trim() +  "' ";
			}
			if (season != null && season.trim() != "") {
				condition += "AND season='" + season.trim() +  "' ";
			}
			if (category != null && category.trim() != "") {
				condition += "AND cat_id='" + category.trim() + "' ";
			}
			System.out.println(condition);

			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT sales_by_customer.customer, SUM(sales_by_customer.sales) as sales FROM sales_by_customer, sales_by_product, products "
							+ condition
							+ "GROUP BY sales_by_customer.customer ORDER BY sales DESC LIMIT 10 OFFSET ?;");
			System.out.println(preparedStatement.toString());
			preparedStatement.setInt(1, offset);
			result = preparedStatement.executeQuery();
			while (result.next()) {
				SalesByCustomer sale = new SalesByCustomer();
				sale.setSales(result.getLong("sales"));
				try {
					sale.setSeason(result.getString("season"));
				} catch (Exception e) {

				}
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
			String category, int offset) {
		ResultSet result = null;
		List<SalesByProduct> sales = new ArrayList<SalesByProduct>();
		System.out.println(state + season);
		try {
			String condition = " WHERE products.sku = sales_by_product.sku ";
			if (state != null && state.trim() != "") {
				condition += "AND state='" + state.trim() + "' ";
			}
			if (season != null && season.trim() != "") {
				condition += "AND season='" + season.trim() + "' ";
			}
			if (category != null && category.trim() != "") {
				condition += "AND cat_id='" + category.trim() + "' ";
			}
			System.out.println(condition);

			String query = "SELECT products.sku, SUM(sales) AS sales FROM sales_by_product, products "
					+ condition
					+ "GROUP BY products.sku ORDER BY sales DESC LIMIT 10 OFFSET ?;";
			PreparedStatement preparedStatement = connection
					.prepareStatement(query);
			System.out.println(preparedStatement.toString());
			preparedStatement.setInt(1, offset);
			result = preparedStatement.executeQuery();
			while (result.next()) {
				SalesByProduct sale = new SalesByProduct();
				sale.setSales(result.getInt("sales"));

				ProductDao pdao = new ProductDao();
				String sku = result.getString("sku");
				Product product = pdao.getProductByIdReturnProd(Integer
						.parseInt(sku));
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

	public int getSalesByCustomerAndProduct(User customer, Product product,
			String season) {
		ResultSet result = null;
		int sales = 0;
		SalesByProduct sale = new SalesByProduct();
		try {
			String condition = "";
			if (season != null && season.trim() != "") {
				condition += "AND season='" + season.trim() + "' ";
			}
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT SUM(sales) AS sales FROM sales_by_product WHERE customer = ? AND sku = ? "
							+ condition
							+ "GROUP BY sku;");
			preparedStatement.setInt(1, customer.getId());
			preparedStatement.setInt(2, product.getId());
			result = preparedStatement.executeQuery();

			while (result.next()) {
				sales = result.getInt("sales");
				System.out.println(customer.getName() + " " + product.getName()
						+ " " + result.getInt("sales"));
			}
			result.close();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sales;

	}

	public int getSalesByCustomerAndState(String state, Product product) {
		ResultSet result = null;
		int sale = 0;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT SUM(sales) AS sales FROM sales_by_product WHERE state = ? AND sku = ? GROUP BY sku;");
			preparedStatement.setString(1, state);
			preparedStatement.setInt(2, product.getId());
			result = preparedStatement.executeQuery();

			while (result.next()) {
				sale = result.getInt("sales");
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
