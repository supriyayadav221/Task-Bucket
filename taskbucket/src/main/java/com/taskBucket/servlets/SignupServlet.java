package com.taskBucket.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.UUID;

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
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignupServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("hey, resq come in servlet");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println(request);
		Connection con=myConnection.getConnection();
		System.out.println(con);

		UserDao userDao = new UserDao(con);
		System.out.println(userDao);
		String uniqueID = UUID.randomUUID().toString();
		HttpSession httpSession = request.getSession();

		System.out.println(name+""+email+""+password);
		User user = new User(uniqueID, name, email, password, "user");
		if(name==null || email==null || password==null)
		{AlertMessage alertMessage = new AlertMessage("All fields are required!", "error", "bg-danger");
			httpSession.setAttribute("alertMessage", alertMessage);
			response.sendRedirect("signup.jsp");
		}
		boolean saveduser = userDao.registerUser(user);
		if (saveduser == true) {
			AlertMessage alertMessage = new AlertMessage("User saved succesfully!", "success", "bg-success");
			httpSession.setAttribute("alertMessage", alertMessage);
			httpSession.setAttribute("currentUser", user);
			response.sendRedirect("home.jsp");
		} else {
			response.sendRedirect("home.jsp");
		}
	}
}
