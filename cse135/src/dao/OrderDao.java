package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import model.Product;
import model.User;
import util.DbUtil;

public class OrderDao {
	private Connection connection;

	public OrderDao() {
		connection = DbUtil.getConnection();
	}

	/**
	 * @param user
	 * @return id of the order
	 */
	public int createOrder(User user) {
		int ret = 0;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO orders (user_pk) VALUES (?) RETURNING id");

			preparedStatement.setInt(1, user.getId());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				// Read values using column name
				ret = rs.getInt("id");
			}
			rs.close();
			// Close the Statement
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public int addProduct(Product product, int quantity, int user_pk) {
		int result = 0;
		while (--quantity > 0) {
			result += addProduct(product, user_pk);
		}
		return result;
	}
	
	/**
	 * @param user 
	 * @param Product product, int quantity, int order_pk
	 * @return 0 if there was an error, 1 if the insertion was correct
	 */
	public int addProduct(Product product, int user_pk) {
		int ret = 0;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO ordered (product, order_pk) VALUES (?, ?)");
			preparedStatement.setInt(1, product.getId());
			preparedStatement.setInt(2, user_pk);
			ret = preparedStatement.executeUpdate();
			// Close the Statement
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public Map<String, Double> getTotals() {
		Map<String, Double> result = new HashMap<String, Double>();
		try {
			PreparedStatement ps = connection
					.prepareStatement("select sum(price) as cost, state, category from ordered, orders, products, users where orders.id = ordered.order_pk and products.id = ordered.product and user_pk=users.id group by state, category");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				double cost = rs.getDouble("cost");
				String state = rs.getString("state");
				if (state == null) continue;
				state = state.toUpperCase();
				String category = String.valueOf(rs.getInt("category"));
				
				result.put(category + "," + state, cost);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
