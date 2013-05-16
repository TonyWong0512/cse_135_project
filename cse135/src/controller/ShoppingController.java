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

import model.Order;
import model.Product;
import dao.ProductDao;

/**
 * Servlet implementation class ShoppingController
 */

public class ShoppingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String SHOPPING_CART = "/cart.jsp";
	private static String CONFIRM_PAYMENT = "/confirm_payment.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShoppingController() {
		super();
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
		List<Order> cart = ((ArrayList<Order>) session
				.getAttribute("shopping_cart"));

		String action = request.getParameter("action");
		// Add to the cart
		if (action != null && action.equals("add")) {
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
		String forward = SHOPPING_CART;
		ProductDao dao = new ProductDao();
		HttpSession session = request.getSession();
		// Get old cart
		List<Order> cart = ((ArrayList<Order>) session
				.getAttribute("shopping_cart"));

		if (cart == null) {
			cart = new ArrayList<Order>();
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
				// Create the order
				Order order = new Order(product_buying,
						Integer.parseInt(request.getParameter("quantity")));
				// Add it to the cart
				cart.add(order);
				session.setAttribute("shopping_cart", cart);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		if (action != null && action.equals("confirm_payment")) {
			forward = CONFIRM_PAYMENT;
			// Store the cart in the DB
		}

		request.setAttribute("cart", cart);

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

}
