package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Order;
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
	
	/**
	 * @param Product product, int quantity, int order_pk
	 * @return 0 if there was an error, 1 if the insertion was correct
	 */
	public int addProduct(Product product, int quantity, int order_pk) {
		int ret = 0;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO ordered (product, quantity, order_pk) VALUES (?, ?, ?)");

			preparedStatement.setInt(1, product.getId());
			preparedStatement.setInt(2, quantity);
			preparedStatement.setInt(3, order_pk);
			ret = preparedStatement.executeUpdate();
			// Close the Statement
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;

	}
}
