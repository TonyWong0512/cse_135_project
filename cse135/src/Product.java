

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		if (!Browse.isOwner(request, response)) {
			response.sendRedirect("browse");
		} else {
			String name = request.getParameter("name");
			String category = request.getParameter("category");
			name = (name == null) ? "" : name.trim();
			category = (category == null) ? "" : category.trim();
			request.setAttribute("category", category);
			request.setAttribute("name", name);
			
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
		if (!Browse.isOwner(request, response)) {
			response.sendRedirect("browse");
		} else {
			Browse.isOwner(request, response);
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
