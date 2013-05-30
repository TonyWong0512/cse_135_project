package controller;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		System.out.println("Rows offset "+roff);
		int rowsOffset = 0;
		if(roff!=null){
			rowsOffset = Integer.parseInt(roff);
		}
		String coff = request.getParameter("coff");
		System.out.println("Columns offset "+coff);
		int colsOffset = 0;
		if(coff!=null){
			colsOffset = Integer.parseInt(coff);
		}
				
		if (rows != null && rows.equals("s")) {
			System.out.println("Generating states");			
			request.setAttribute("salesByStates", dao.getSalesByState(null, rowsOffset));
		} else {
			// Generate customers
			System.out.println("Generating customers");
			request.setAttribute("customers", dao.getSalesByCustomer(null, rowsOffset));
		}
		
		request.setAttribute("products", dao.getProducts("", "", colsOffset));
		CategoryDao cdao = new CategoryDao();
		request.setAttribute("categories", cdao.getAllCategories());
		
		
		String age = request.getParameter("age");
		age = (age == null) ? "" : age.trim();
		request.setAttribute("age", age);

		String category = request.getParameter("category");
		category = (category == null) ? "" : category.trim();
		request.setAttribute("category", category);

		String quarter = request.getParameter("quarter");
		quarter = (quarter == null) ? "" : quarter.trim();
		request.setAttribute("quarter", quarter);
		
		String state = request.getParameter("state");
		state = (state == null) ? "" : state.trim();
		request.setAttribute("state", state);
		
		rows = (rows == null) ? "" : rows.trim();
		request.setAttribute("rows", rows);
		
		request.setAttribute("roff", rowsOffset);
		request.setAttribute("coff", colsOffset);
		
		request.setAttribute("states", STATES);
		
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
