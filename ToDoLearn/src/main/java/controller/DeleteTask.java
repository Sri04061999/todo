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

@WebServlet("/delete")
public class DeleteTask extends HttpServlet {
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
			System.out.println(task);
			user.getTasks().remove(task);
			dao.update(user);
			dao.removeTask(task);
			User user2 = dao.fetchByEmail(user.getEmail());
			req.getSession().setAttribute("user", user2);
			res.getWriter().print("<h1>Task Deleted</h1>");
			req.setAttribute("list", user2.getTasks());
			req.getRequestDispatcher("home.jsp").include(req, res);
		}
	}
}
