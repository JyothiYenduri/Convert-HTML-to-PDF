
package com.bitlab;


	import java.io.IOException;

	import java.sql.Connection;

	import java.sql.DriverManager;

	import java.sql.PreparedStatement;

	import java.sql.SQLException;

	import javax.servlet.ServletException;

	import javax.servlet.http.HttpServlet;

	import javax.servlet.http.HttpServletRequest;

	import javax.servlet.http.HttpServletResponse;

	 

	public class SaveDataServlet extends HttpServlet {

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	        String name = request.getParameter("name");

	        String value = request.getParameter("value");

	 

	        // Database connection parameters

	        String jdbcUrl = "jdbc:mysql://localhost:3306/man";

	        String jdbcUsername = "root";

	        String jdbcPassword = "root";

	 

	        Connection connection = null;

	        PreparedStatement preparedStatement = null;

	 

	        try {

	            // Establish database connection

	            connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);

	 

	            // Insert data into the database

	            String insertQuery = "INSERT INTO data_table (name, value) VALUES (?, ?)";

	            preparedStatement = connection.prepareStatement(insertQuery);

	            preparedStatement.setString(1, name);

	            preparedStatement.setString(2, value);

	            preparedStatement.executeUpdate();

	 

	            // Respond with a success message

	            response.getWriter().write("Data saved successfully");

	        } catch (SQLException e) {

	            e.printStackTrace();

	            // Handle database error and respond with an error message

	            response.getWriter().write("Error while saving data to the database");

	        } finally {

	            // Close resources

	            if (preparedStatement != null) {

	                try {

	                    preparedStatement.close();

	                } catch (SQLException e) {

	                    e.printStackTrace();

	                }

	            }

	            if (connection != null) {

	                try {

	                    connection.close();

	                } catch (SQLException e) {

	                    e.printStackTrace();

	                }

	            }

	        }

	    }

	}



