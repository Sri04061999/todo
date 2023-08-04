package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dto.User;

@WebServlet("/backtohome")
public class BackToHome extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		User user = (User) req.getSession().getAttribute("user");
		if (user == null) {
			res.getWriter().print("<h1>session expired</h1>");
			req.getRequestDispatcher("login.html").include(req, res);
		} else {
			req.setAttribute("list", user.getTasks());
			req.getRequestDispatcher("home.jsp").include(req, res);
		}
	}

}
