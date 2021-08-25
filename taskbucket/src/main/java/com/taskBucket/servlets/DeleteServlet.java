package com.taskBucket.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taskBucket.daos.TodoDao;
import com.taskBucket.helpers.AlertMessage;
import com.taskBucket.helpers.myConnection;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id= request.getParameter("q");
		System.out.println(id);
		TodoDao todoDao= new TodoDao(myConnection.getConnection());
		Boolean deletedTodo= todoDao.deleteTodo(id);
		HttpSession httpSession= request.getSession();
		if(deletedTodo) {
			AlertMessage alertMessage= new AlertMessage("Todo deleted Successfully!", "success", "bg-success");
			httpSession.setAttribute("alertMessage", alertMessage);
			response.sendRedirect("home.jsp");
			return;
		}else {
			AlertMessage alertMessage= new AlertMessage("Something went wrong!!", "error", "bg-danger");
			httpSession.setAttribute("alertMessage", alertMessage);
			response.sendRedirect("home.jsp");
			return;
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}

}
