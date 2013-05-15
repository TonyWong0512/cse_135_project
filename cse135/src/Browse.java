

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDao;
import dao.ProductDao;

/**
 * Servlet implementation class Browse
 */
public class Browse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Browse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		loadProducts(request);
		
		getServletContext().getRequestDispatcher("/browse.jsp").forward(request, response);
	}

	public static void loadProducts(HttpServletRequest request) {
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
				products = pdao.getProductByCategory(category);
			} else {
				products = pdao.getProduct(name, category);
			}
		}
		request.setAttribute("products", products);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
