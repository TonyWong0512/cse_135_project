package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import util.DbUtil;

public class CategoryDao {
	private Connection connection;
	
	public CategoryDao() {
        connection = DbUtil.getConnection();
    }
	
 	public int addCategory(Category category) {
 		int ret = 0;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO categories (name, description) VALUES (?, ?)");
            // Parameters start with 1
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDescription());
            ret = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
	
	public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<Category>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM categories");
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                categories.add(category);
            }
            rs.close();
			// Close the Statement
			statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }
}
