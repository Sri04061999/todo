package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UserDao;
import dto.User;

@WebServlet("/login")
public class Login extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		UserDao dao = new UserDao();
		User user = dao.fetchByEmail(email);
		if (user == null) {
			res.getWriter().print("<h1 style='color:red'>Inavlid Email</h1>");
			req.getRequestDispatcher("login.html").include(req, res);
		} else {
			if (user.getPassword().equals(password)) {
				req.getSession().setAttribute("user", user);
//				req.getSession().setMaxInactiveInterval(10);
				res.getWriter().print("<h1 style='color:green'>Welcome back to ToDo Home</h1>");
				req.setAttribute("list", user.getTasks());
				req.getRequestDispatcher("home.jsp").include(req, res);
			} else {
				res.getWriter().print("<h1 style='color:red'>Incorrect Password</h1>");
				req.getRequestDispatcher("login.html").include(req, res);
			}
		}
	}
}
