package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.Task;
import dto.User;

@WebServlet("/addtask")
public class AddTask extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		User user = (User) req.getSession().getAttribute("user");
		if (req.getSession().getAttribute("user") == null) {
			res.getWriter().print("<h1>session expired</h1>");
			req.getRequestDispatcher("login.html").include(req, res);
		} else {
			req.getSession().setMaxInactiveInterval(10);
			String taskName = req.getParameter("taskname");
			String description = req.getParameter("taskdescription");
			int noOfDays = Integer.parseInt(req.getParameter("noofdays"));

			Task task = new Task();
			task.setTaskName(taskName);
			task.setTask_description(description);
			task.setTask_date(LocalDate.now());
			task.setEnd_date(LocalDate.now().plusDays(noOfDays));

			UserDao dao = new UserDao();
			dao.addTask(task);

			List<Task> list = user.getTasks();
			if (list == null) {
				list = new ArrayList();
				list.add(task);
			} else {
				list.add(task);
			}
			user.setTasks(list);
			dao.update(user);

			res.getWriter().print("<h1 style='color:red'> Task Added </h1>");
			req.setAttribute("list", user.getTasks());
			req.getRequestDispatcher("home.jsp").include(req, res);
		}
	}

}
