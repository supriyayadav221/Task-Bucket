package com.taskBucket.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taskBucket.daos.UserDao;
import com.taskBucket.entities.User;
import com.taskBucket.helpers.AlertMessage;
import com.taskBucket.helpers.myConnection;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		HttpSession httpSession = request.getSession();

		System.out.print(email == null + " /////" + password);
		if (email == null || password == null) {
			AlertMessage alertMessage = new AlertMessage("All fields are required!", "error", "bg-danger");
			httpSession.setAttribute("alertMessage", alertMessage);
			response.sendRedirect("login.jsp");
			return;
		}

		UserDao userDao = new UserDao(myConnection.getConnection());
		User user = userDao.loginUser(email, password);
		if (user == null) {
			System.out.println("null");
			AlertMessage alertMessage = new AlertMessage("Invalid credentials", "error", "bg-danger");

			httpSession.setAttribute("alertMessage", alertMessage);
			response.sendRedirect("login.jsp");
		} else if (user.getRole().equals("admin")) {
			System.out.println("admin");

			httpSession.setAttribute("currentUser", user);
			response.sendRedirect("admin.jsp");
		} else if (user.getRole().equals("user")) {
			System.out.println("user");

			httpSession.setAttribute("currentUser", user);
			response.sendRedirect("home.jsp");
		}
	}

}
