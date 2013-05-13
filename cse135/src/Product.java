

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DbUtil;

/**
 * Servlet implementation class Product
 */
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Product() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("product.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String sku = request.getParameter("sku");
		Double price = Double.parseDouble(request.getParameter("price"));
		String category = request.getParameter("category");
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into products(name, sku, price, category) values (?, ?, ?, ?)");
			ps.setString(1, name);
			ps.setString(2, sku);
			ps.setBigDecimal(3, new BigDecimal(price));
			ps.setString(4, category);
			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
