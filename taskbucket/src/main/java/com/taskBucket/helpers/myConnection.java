package com.taskBucket.helpers;

import java.sql.Connection;
import java.sql.DriverManager;

public class myConnection {

	private static Connection con = null;

	public static Connection getConnection() {
		System.out.println("inside myconnection");
		try {
			if (con == null) {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/taskbucket", "root", "");
				System.out.println("connection done");
			}
			System.out.println("Test: "+con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}