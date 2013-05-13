

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Category;
import dao.CategoryDao;
import dao.ProductDao;
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
		/*
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("select name from categories");
			ResultSet rs = ps.executeQuery();
			ps.executeUpdate();
			
			List<String> result = new ArrayList<String>();
			while (rs.next()) {
				result.add(rs.getString("name"));
			}
			request.setAttribute("categories", result);
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		getServletContext().getRequestDispatcher("product.jsp").forward(request, response);
		*/
		CategoryDao cdao = new CategoryDao();
		request.setAttribute("categories", cdao.getAllCategories());
		ProductDao pdao = new ProductDao();
		request.setAttribute("products", pdao.getAllProducts());
		getServletContext().getRequestDispatcher("/product.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String sku = request.getParameter("sku");
		Double price = Double.parseDouble(request.getParameter("price"));
		int category = Integer.parseInt(request.getParameter("category"));
		ProductDao dao = new ProductDao();
		model.Product p = new model.Product(name, sku, price, category);
		dao.addProduct(p);
		request.setAttribute("product", p);
		getServletContext().getRequestDispatcher("/productconfirm.jsp").forward(request, response);
	}

}
