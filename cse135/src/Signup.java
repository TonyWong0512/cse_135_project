

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;

import model.User;

import util.DbUtil;

/**
 * Servlet implementation class Signup
 */
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("signup.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String role = request.getParameter("role");
		short age;
		try {
			age = Short.parseShort(request.getParameter("age"));
		} catch (NumberFormatException e) {
			age = -1;
		}
		String state = request.getParameter("state");
		System.out.println(role);
		User u = new User(name, role, age, state);
		System.out.println(u.getRole());
		UserDao dao = new UserDao();
		HttpSession session = request.getSession();
		session.setAttribute("name", name);
		dao.addUser(u);
		if (role.contains("owner")) {
			session.setAttribute("role", "owner");
			response.sendRedirect("product");
		} else {
			session.setAttribute("role", "customer");
			response.sendRedirect("browse");
		}

	}

}
