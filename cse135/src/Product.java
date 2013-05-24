

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

import dao.ProductDao;

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
		if (!User.isOwner((String) request.getSession().getAttribute("role"))) {
			response.sendRedirect("browse");
		} else {
			Browse.loadProducts(request);
			getServletContext().getRequestDispatcher("/product.jsp").forward(
					request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (!User.isOwner((String) request.getSession().getAttribute("role"))) {
			response.sendRedirect("browse");
		} else {
			User.isOwner((String) request.getSession().getAttribute("role"));
			String name = request.getParameter("name");
			String sku = request.getParameter("sku");
			Double price = Double.parseDouble(request.getParameter("price"));
			int category = Integer.parseInt(request.getParameter("category"));
			ProductDao dao = new ProductDao();
			model.Product p = new model.Product(name, sku, price, category);
			dao.addProduct(p);
			request.setAttribute("product", p);
			getServletContext().getRequestDispatcher("/productconfirm.jsp")
					.forward(request, response);

		}
	}
}
