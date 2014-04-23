package com.facilitymanagement.dal;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {
	
	private static final String USERNAME = "root";
	private static final String PASSWORD = "broncos98";
	private static final String CONN_STRING = "jdbc:mysql://localhost/facility";
	@SuppressWarnings("unused")
	public static Connection getConnection() {
		 
		System.out.println("DBHelper: -------- MySQL " + "JDBC Connection  ------------");
 
		try {
			
			//loads the driver class into memory
			Class.forName("com.mysql.jdbc.Driver");
			//Class.forName("org.postgresql.Driver");
 
		} catch (ClassNotFoundException e) {
 
			System.out.println("DBHelper: Check Where  your MySQL JDBC Driver exist and " + "Include in your library path!");
			e.printStackTrace();
			return null;
 
		}
 
		System.out.println("DBHelper: MySQL JDBC Driver Registered!");
		
		//creating an instance of the Connection interface --
		//choose a java.sql
		Connection connection = null;
 
		try {
 
			//connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/BookStore", "student", "Comp442");
			connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("SELECT VERSION()"); //INTERFACE THAT CONTAINS THE DATA 
																	//RETURNED BY THE DATABASE

	            if (rs.next()) {
	                System.out.println("DBHelper: The Database Version is " + rs.getString(1));
	            }
 
		} catch (SQLException e) {
 
			System.out.println("DBHelper: Connection Failed! Check output console");
			e.printStackTrace();
			return null;
 
		}
 
		if (connection != null) {
			System.out.println("DBHelper: You have a database connection!");
		} else {
			System.out.println("DBHelper: Failed to make connection!");
		}
		
		return connection;
	}
	
	public static void main(String[] args)
	{
		try {
			Statement st = DBHelper.getConnection().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
