

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

import dao.UserDao;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		if (name != null) {
			UserDao dao = new UserDao();
			User u = dao.getUser(name);
			if (u != null) {
				HttpSession session = request.getSession();
				session.setAttribute("name", name);
				if (u.getRole().contains("owner")) {
					session.setAttribute("role", "owner");
					response.sendRedirect("product.jsp");
				} else {
					session.setAttribute("role", "customer");
					response.sendRedirect("browse.jsp");
				}
			} else {
				PrintWriter out = response.getWriter();
				out.print("invalid user");
			}
		}
	}

}
