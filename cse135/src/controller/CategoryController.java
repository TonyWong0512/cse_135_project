package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Category;

import dao.CategoryDao;

/**
 * Servlet implementation class Category
 */
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDao dao;
	private static String LIST_CATEGORIES = "/categories.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoryController() {
		super();
		dao = new CategoryDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String forward = "";

		forward = LIST_CATEGORIES;
		request.setAttribute("categories", dao.getAllCategories());

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doDelete(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPut(req, resp);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String forward = "";

		forward = LIST_CATEGORIES;
		
		// Create the new object that will be added
		Category category = new Category();
		category.setName(request.getParameter("name"));
		category.setDescription(request.getParameter("description"));
		int dao_ret;
		if (!category.getName().equals("") && !category.getDescription().equals("")) {
			dao_ret = dao.addCategory(category);
			if (dao_ret>0){
				request.setAttribute("message", "The category was successfully added");
			}else{
				request.setAttribute("message", "There was a problem adding the category");
			}
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		
		request.removeAttribute("categories");
		request.setAttribute("categories", dao.getAllCategories());
		
		view.forward(request, response);
	}
}
