package controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDao dao = new ProductDao();
		
		List<Product> product = dao.getProduct(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("products", product);
		
		RequestDispatcher view = request.getRequestDispatcher(SHOPPING_CART);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
