package controller;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SalesDao;

/**
 * Servlet implementation class DashboardController
 */
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
			request.setAttribute("states", dao.getSalesByState(null, rowsOffset));
		} else {
			// Generate customers
			System.out.println("Generating customers");
			request.setAttribute("customers", dao.getSalesByCustomer(null, rowsOffset));
		}
		
		request.setAttribute("products", dao.getProducts("", "", colsOffset));
		
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
