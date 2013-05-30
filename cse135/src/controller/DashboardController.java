package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SalesByProduct;
import model.SalesByState;

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
		String season = request.getParameter("quarter");
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
		
		List<SalesByProduct> products = dao.getProducts("", "", colsOffset);
		request.setAttribute("products", products);
		
		if (rows != null && rows.equals("s")) {
			System.out.println("Generating states");
			List<SalesByState> sales = dao.getSalesByState(null, rowsOffset);
			request.setAttribute("states", sales);
			for (SalesByProduct product : products) {
				for (SalesByState sale : sales) {
					List<SalesByProduct> cell = dao.getSalesByProduct(product.getCustomer(), product.getState(), season, colsOffset);
					System.out.println(product.getCustomer() + product.getState());
					for (SalesByProduct sbp: cell) {
						System.out.println(sbp.getSales());
					}
					break;
				}
			}
		} else {
			// Generate customers
			System.out.println("Generating customers");
			request.setAttribute("customers", dao.getSalesByCustomer(null, rowsOffset));
		}

		
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
