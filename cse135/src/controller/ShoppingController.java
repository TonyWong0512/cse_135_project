package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Product;
import dao.ProductDao;

/**
 * Servlet implementation class ShoppingController
 */
public class ShoppingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String SHOPPING_CART = "/cart.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShoppingController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ProductDao dao = new ProductDao();
		HttpSession session = request.getSession();
		// Get old cart
		List<Product> cart = (List<Product>) session.getAttribute("shopping_cart");
		if (cart == null) {
			cart = new ArrayList<Product>();
		}
		String action = request.getParameter("action");
		// Add to the cart
		if (action != null && action.equals("add_to_cart")) {
			try {
				int id_product_buying = Integer.parseInt(request
						.getParameter("id"));
				// Get the product
				Product product_buying = dao.getProduct(id_product_buying).get(
						0);
				// Add it to the cart
				cart.add(product_buying);
				session.setAttribute("shopping_cart", cart);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		// Ask if want to add to cart
		else if (action != null && action.equals("add")) {
			try {
				int id_product_to_buy = Integer.parseInt(request
						.getParameter("id"));
				List<Product> product_to_buy = dao
						.getProduct(id_product_to_buy);
				request.setAttribute("product_to_buy", product_to_buy.get(0));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("cart", cart);

		RequestDispatcher view = request.getRequestDispatcher(SHOPPING_CART);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
