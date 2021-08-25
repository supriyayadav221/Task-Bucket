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

import com.taskBucket.daos.TodoDao;
import com.taskBucket.entities.Todo;
import com.taskBucket.entities.User;
import com.taskBucket.helpers.AlertMessage;
import com.taskBucket.helpers.myConnection;

/**
 * Servlet implementation class SaveTodo
 */
@WebServlet("/SaveTodo")
public class SaveTodo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaveTodo() {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String title = request.getParameter("title");
		HttpSession httpSession = request.getSession();
		User user = (User) httpSession.getAttribute("currentUser");
		Todo todo = new Todo(UUID.randomUUID().toString(), title, user.getId());
		TodoDao todoDao = new TodoDao(myConnection.getConnection());
		Boolean saveTodo = todoDao.saveTodo(todo);
		if (saveTodo) {
			AlertMessage alertMessage = new AlertMessage("Todo added successfully!", "success", "bg-success");
			httpSession.setAttribute("alertMessage", alertMessage);
			response.sendRedirect("home.jsp");
		} else {
			AlertMessage alertMessage = new AlertMessage("Something went wrong!", "error", "bg-danger");
			httpSession.setAttribute("alertMessage", alertMessage);
			response.sendRedirect("home.jsp");
		}
	}

}
