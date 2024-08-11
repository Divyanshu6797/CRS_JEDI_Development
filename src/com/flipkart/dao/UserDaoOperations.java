package com.flipkart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.flipkart.client.AdminClient;
import com.flipkart.client.ProfessorClient;
import com.flipkart.client.StudentCRSMenu;

public class UserDaoOperations implements UserDaoInterface{

	@Override
	public void signup(int userId, String password, String role) {
	    try {
	        // Establishing connection
	        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:4000/CRS_JEDI", "root", "123456");
	        
	        
	        String query = "INSERT INTO user (userId, password, role) VALUES (?, ?, ?)";
	        PreparedStatement stmt = con.prepareStatement(query);
	        
	        
	        stmt.setInt(1, userId);         // Set userId
	        stmt.setString(2, password);    // Set password
	        stmt.setString(3, role);        // Set role
	        
	        
	        stmt.executeUpdate();
	        
	        con.close();
	        System.out.println("Successfully registered");

	    } catch (java.sql.SQLIntegrityConstraintViolationException e) {
	        System.out.println("User ID already exists. Please choose a different ID.");
	    } catch (Exception e) {
	        System.out.println(e);
	        System.out.println("Couldn't register");
	    }
	}

	@Override
	public void login(int userId, String password) {
	    try {
	        // Establishing connection
	        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:4000/CRS_JEDI", "root", "123456");
	        
	        
	        String query = "SELECT * FROM user WHERE userId = ? AND password = ?";
	        PreparedStatement stmt = con.prepareStatement(query);
	        
	       
	        stmt.setInt(1, userId);         
	        stmt.setString(2, password);    
	        
	        
	        ResultSet rs = stmt.executeQuery();
	        
	        
	        if (rs.next()) {
	            System.out.println("Login successful. Welcome, user with ID: " + userId);
	            
	            String role = rs.getString("role");
	            System.out.println("Your role is: " + role);
	            System.out.println(role.getClass().getName());
	            System.out.println("hfuf".getClass().getName());
	            
	            
	            if(role.equals("student")) {
	            	StudentCRSMenu studentMenu = new StudentCRSMenu();
	            	studentMenu.createStudentMenu(userId);
	            }
	            else if(role.equals("admin")) {
	            	AdminClient adminMenu = new AdminClient();
	            	adminMenu.createAdminMenu(userId);
	            	
	            }
	            else if(role.equals("professor")) {
	            	ProfessorClient professorMenu = new ProfessorClient();
	            	professorMenu.createProfessorMenu(userId);
	            	
	            	
	            }
	            else {
	            	System.out.println("role not defined");
	            }
	        } else {
	            System.out.println("Invalid userId or password. Please try again.");
	        }
	        
	       
	        rs.close();
	        stmt.close();
	        con.close();

	    } catch (Exception e) {
	        System.out.println(e);
	        System.out.println("Couldn't log in");
	    }
	}


}
