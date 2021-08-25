package com.taskBucket.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.taskBucket.entities.Todo;

public class TodoDao {

	private Connection con;

	public TodoDao(Connection con) {
		this.con = con;
	}

	public Boolean saveTodo(Todo todo) {
		boolean todoSaved = false;
		try {
			String query = "insert into todo (todo_id, title, user_id) values (?,?,?)";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, todo.getTodo_id());
			preparedStatement.setString(2, todo.getTitle());
			preparedStatement.setString(3, todo.getUserId());
			preparedStatement.executeUpdate();
			todoSaved = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return todoSaved;
	}

	public ArrayList<Todo> getUserTodos(String user_ID) {
		System.out.println(user_ID);
		try {
			String query = "select * from todo where user_id=?";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, user_ID);
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<Todo> userTodos = new ArrayList<>();

			while (resultSet.next()) {
				Todo todo = new Todo(resultSet.getString("todo_id"), resultSet.getString("title"),
						resultSet.getString("user_id"));
				userTodos.add(todo);
			}
			return userTodos;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public Boolean updateTodo(String todo_id, String title) {
		boolean todoUpdated = false;

		try {
			String query = "update todo set title=? where todo_id=?";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, todo_id);
			preparedStatement.executeUpdate();
			todoUpdated = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return todoUpdated;
	}

	public Boolean deleteTodo(String todo_id) {
		boolean todoDeleted = false;
		try {
			String query = "delete from todo where todo_id=?";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, todo_id);
			preparedStatement.executeUpdate();
			todoDeleted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return todoDeleted;
	}

}
