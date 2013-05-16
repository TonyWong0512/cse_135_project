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
	static public boolean isOwner(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
			String role = (String)request.getSession().getAttribute("role");
			if (role.contains("owner")) {
				return true;
			}
			return false;
		}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (!isOwner(request, response)) {
			response.sendRedirect("browse");
		} else {
			String forward = LIST_CATEGORIES;
			request.setAttribute("categories", dao.getAllCategories());
	
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String forward = LIST_CATEGORIES;
		if (!isOwner(request, response)) {
			response.sendRedirect("browse");
		} else {
			// Create the new object that will be added
			Category category = new Category();
			category.setName(request.getParameter("name"));
			category.setDescription(request.getParameter("description"));
			
			String action = request.getParameter("action");
			int dao_ret;
	        if (action != null && action.equals("insert")) {
				if (!category.getName().equals("")
						&& !category.getDescription().equals("")) {
					dao_ret = dao.addCategory(category);
					if (dao_ret > 0) {
						request.setAttribute("message",
								"The category was successfully added");
					} else {
						request.setAttribute("message",
								"There was a problem adding the category");
					}
				}
	        } else if (action != null && action.equals("update")) {
	        	category.setId(Integer.parseInt(request.getParameter("id")));
	        	if (!category.getName().equals("")) {
	    			dao_ret = dao.updateCategory(category);
	    			if (dao_ret > 0) {
	    				request.setAttribute("message",
	    						"The category was successfully updated");
	    			} else {
	    				request.setAttribute("message",
	    						"There was a problem updating the category");
	    			}
	    		}
	        } else if (action != null && action.equals("delete")) {
	        	// Create the object that will be updated
	    		int categoryId = Integer.parseInt(request.getParameter("id"));

	    		dao_ret = dao.deleteCategory(categoryId);
	    		if (dao_ret > 0) {
	    			request.setAttribute("message",
	    					"The category was successfully deleted");
	    		} else {
	    			request.setAttribute("message",
	    					"There was a problem deleting the category");
	    		}
	        }
			RequestDispatcher view = request.getRequestDispatcher(forward);

			request.removeAttribute("categories");
			request.setAttribute("categories", dao.getAllCategories());

			view.forward(request, response);
		}
	}
}
