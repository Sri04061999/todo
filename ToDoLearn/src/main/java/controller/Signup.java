package controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.User;

@WebServlet("/signup")
public class Signup extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		long mobile = Long.parseLong(req.getParameter("mobile"));
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		LocalDate dob = LocalDate.parse(req.getParameter("date"));
		String address = req.getParameter("address");

		UserDao dao = new UserDao();
		User user = dao.fetchByEmail(email);
		if (user == null) {
			user = new User();
			user.setName(name);
			user.setEmail(email);
			user.setMobile(mobile);
			user.setPassword(password);
			user.setGender(gender);
			user.setDob(dob);
			user.setAddress(address);

			dao.saveUser(user);

			res.getWriter().print("<h1 style='color:green'>Account Created Successfully</h1>");
			req.getRequestDispatcher("login.html").include(req, res);
		} else {
			res.getWriter().print("<h1 style='color:red'>Account Already exits</h1>");
			req.getRequestDispatcher("signup.html").include(req, res);
		}
	}
}
