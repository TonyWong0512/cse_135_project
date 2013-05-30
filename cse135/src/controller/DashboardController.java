package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SalesByCustomer;
import model.SalesByProduct;
import model.SalesByState;

import dao.CategoryDao;
import dao.SalesDao;

/**
 * Servlet implementation class DashboardController
 */
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final static String[] STATES = { "AL", "AK", "AZ", "AR", "CA",
			"CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS",
			"KY", "LA", "ME", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT",
			"NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR",
			"PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV",
			"WI", "WY" };

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashboardController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		SalesDao dao = new SalesDao();
		String rows = request.getParameter("rows");
		String roff = request.getParameter("roff");
		System.out.println("Rows offset " + roff);
		int rowsOffset = 0;
		if (roff != null) {
			rowsOffset = Integer.parseInt(roff);
		}
		String coff = request.getParameter("coff");
		System.out.println("Columns offset " + coff);
		int colsOffset = 0;
		if (coff != null) {
			colsOffset = Integer.parseInt(coff);
		}

		String quarter = request.getParameter("quarter");
		quarter = (quarter == null) ? "" : quarter.trim();
		request.setAttribute("quarter", quarter);

		String age = request.getParameter("age");
		age = (age == null) ? "" : age.trim();
		request.setAttribute("age", age);

		String category = request.getParameter("category");
		category = (category == null) ? "" : category.trim();
		request.setAttribute("category", category);

		String state = request.getParameter("state");
		state = (state == null) ? "" : state.trim();
		request.setAttribute("state", state);

		rows = (rows == null) ? "" : rows.trim();
		request.setAttribute("rows", rows);

		request.setAttribute("roff", rowsOffset);
		request.setAttribute("coff", colsOffset);

		request.setAttribute("statesList", STATES);

		List<SalesByProduct> products = dao.getProducts("", "", colsOffset);
		request.setAttribute("products", products);

		if (rows != null && rows.equals("s")) {
			System.out.println("Generating states");
			List<SalesByState> salesByState = dao.getSalesByState(null,
					rowsOffset);
			request.setAttribute("states", salesByState);
			// for (SalesByProduct product : products) {
			// for (SalesByState sale : sales) {
			// List<SalesByProduct> cell = dao.getSalesByProduct(
			// product.getCustomer(), product.getState(), quarter,
			// colsOffset);
			// System.out.println(product.getCustomer()
			// + product.getState());
			// for (SalesByProduct sbp : cell) {
			// System.out.println(sbp.getSales());
			// }
			// break;
			// }
			// }
			HashMap<String, HashMap<Integer, Integer>> hmStates = new HashMap<String, HashMap<Integer, Integer>>();

			for (SalesByState s : salesByState) {
				// This hashmap contains all the sales for each product.
				// There's one of this per customer
				HashMap<Integer, Integer> hmProducts = new HashMap<Integer, Integer>();
				for (SalesByProduct p : products) {
					int cell = dao.getSalesByCustomerAndState(s.getState(),
							p.getProduct());
					hmProducts.put(p.getProduct().getId(), cell);
				}
				hmStates.put(s.getState(), hmProducts);
				System.out.println(hmStates);
			}

			request.setAttribute("salesByStates", hmStates);
		} else {
			// Generate customers
			System.out.println("Generating customers");
			List<SalesByCustomer> salesByCustomer = dao.getSalesByCustomer(
					null, rowsOffset);
			request.setAttribute("customers", salesByCustomer);
			HashMap<Integer, HashMap<Integer, SalesByProduct>> hmCustomers = new HashMap<Integer, HashMap<Integer, SalesByProduct>>();

			for (SalesByCustomer c : salesByCustomer) {
				// This hashmap contains all the sales for each product.
				// There's one of this per customer
				HashMap<Integer, SalesByProduct> hmProducts = new HashMap<Integer, SalesByProduct>();
				for (SalesByProduct p : products) {
					SalesByProduct cell = dao.getSalesByCustomerAndProduct(
							c.getCustomer(), p.getProduct());
					hmProducts.put(p.getProduct().getId(), cell);
				}
				hmCustomers.put(c.getCustomer().getId(), hmProducts);
				System.out.println(hmCustomers);
			}

			request.setAttribute("salesByCustomer", hmCustomers);
		}

		request.setAttribute("products", dao.getProducts("", "", colsOffset));
		CategoryDao cdao = new CategoryDao();
		request.setAttribute("categories", cdao.getAllCategories());

		RequestDispatcher view = request.getRequestDispatcher("/dashboard.jsp");
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
