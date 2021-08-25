package com.taskBucket.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taskBucket.daos.TodoDao;
import com.taskBucket.entities.User;
import com.taskBucket.helpers.AlertMessage;
import com.taskBucket.helpers.myConnection;

/**
 * Servlet implementation class UpdateTodoServlet
 */
@WebServlet("/UpdateTodoServlet")
public class UpdateTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateTodoServlet() {
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
		// TODO Auto-generated method stub
		doGet(request, response);
		HttpSession httpSession = request.getSession();
		String title = request.getParameter("title");
		String todo_id= request.getParameter("todo_id");
		User user = (User) httpSession.getAttribute("CurrentUser");
		TodoDao todoDao= new TodoDao(myConnection.getConnection());
		
		Boolean todoUpdated= todoDao.updateTodo( todo_id,  title);
		if(todoUpdated) {
			AlertMessage alertMessage= new AlertMessage("Todo Updated", "success" ,"bg-success");
			httpSession.setAttribute("alertMessage", alertMessage);
			response.sendRedirect("home.jsp");
			return;
		}else
		{
			AlertMessage alertMessage= new AlertMessage("Something went wrong", "error" ,"bg-danger");
			httpSession.setAttribute("alertMessage", alertMessage);
			response.sendRedirect("home.jsp");
			return;	
		}
		
	}

}
