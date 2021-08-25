package com.taskBucket.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.taskBucket.entities.User;

import java.awt.List;
import java.sql.Connection;

public class UserDao {

	private Connection con;

	public UserDao(Connection con) {
		this.con = con;
	}

	public User loginUser(String email, String password) {
		System.out.println("req came");
		try {
			String query = "select * from users where email=? and user_password =?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, password);
			ResultSet resultSet = pst.executeQuery();
			User user= null;
			while (resultSet.next()) {
				 user = new User(resultSet.getString("id"), resultSet.getString("name"), resultSet.getString("email"),
						resultSet.getString("role"));
			}
			return user;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Boolean registerUser(User user) {
		boolean userSaved = false;
		try {
			String query = "insert into users(id, name, email, user_password, role) values (?,?,?,?,?)";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, user.getId());
			preparedStatement.setString(2, user.getName());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setString(5, user.getRole());
			preparedStatement.executeUpdate();
			userSaved = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userSaved;
	}

	public java.util.List<User> getAllUsers() {
		try {

			java.util.List<User> userLists = new ArrayList<>();
			String query = "select * from users";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				User user = new User(resultSet.getString("id"), resultSet.getString("name"),
						resultSet.getString("email"), resultSet.getString("role"));
				userLists.add(user);
			}
			return userLists;

		} catch (Exception e) {
			// TODO: handle exception\
			e.printStackTrace();
		}
		return null;
	}
}
