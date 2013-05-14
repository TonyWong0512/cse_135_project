

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
		CategoryDao cdao = new CategoryDao();
		request.setAttribute("categories", cdao.getAllCategories());
		
		int category;
		try {
			category = Integer.parseInt(request.getParameter("category"));
		} catch (Exception e) {
			category = -1;
		}
		String name = request.getParameter("name");
		
		ProductDao pdao = new ProductDao();
		List<model.Product> products = null;

		if (category == -1) {
			if (name == null || name.trim() == "") {
				products = pdao.getAllProducts();
			} else {
				products = pdao.getProductByName(name);
			}
		} else {
			if (name == null || name.trim() == "") {
				products = pdao.getProduct(category);
			} else {
				products = pdao.getProduct(name, category);
			}
		}
		request.setAttribute("products", products);
		
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
