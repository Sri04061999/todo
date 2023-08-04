package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.Task;
import dto.User;

@WebServlet("/edit")
public class EditTask extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("user");
		if (user == null) {
			res.getWriter().print("<h1>session expired</h1>");
			req.getRequestDispatcher("login.html").include(req, res);
		} else {
			int id = Integer.parseInt(req.getParameter("id"));
			UserDao dao = new UserDao();
			Task task = dao.fetchTask(id);
			req.setAttribute("task", task);
			req.getRequestDispatcher("updatetask.jsp").forward(req, res);
		}
	}
}
