package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/checkaddtasksession")
public class Checkaddtasksession extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		if (req.getSession().getAttribute("user") == null) {
			res.getWriter().print("<h1>session expired</h1>");
			req.getRequestDispatcher("login.html").include(req, res);
		} else {
			req.getRequestDispatcher("addtask.html").forward(req, res);
		}
	}

}
