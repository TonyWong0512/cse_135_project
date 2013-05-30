package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Product;
import model.User;
import util.DbUtil;

public class UserDao {
	private Connection connection;

	public UserDao() {
		connection = DbUtil.getConnection();
	}

	public int addUser(User u) {
		int result = 0;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into users(name, role, age, state) values(?, ?, ?, ?)");
			preparedStatement.setString(1, u.getName());
			preparedStatement.setString(2, u.getRole());
			preparedStatement.setShort(3, u.getAge());
			preparedStatement.setString(4, u.getState());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public User getUser(String name) {
		User u = null;
		try {
			PreparedStatement ps = connection
					.prepareStatement("select * from users where name = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				u = new User();
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				u.setRole(rs.getString("role"));
				u.setAge(rs.getShort("age"));
				u.setState(rs.getString("state"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	public User getUser(int pk) {
		User u = null;
		try {
			PreparedStatement ps = connection
					.prepareStatement("select * from users where id = ?");
			ps.setInt(1, pk);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				u = new User();
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				u.setRole(rs.getString("role"));
				u.setAge(rs.getShort("age"));
				u.setState(rs.getString("state"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	
	

}
