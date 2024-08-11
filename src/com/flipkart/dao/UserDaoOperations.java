package com.flipkart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.flipkart.client.AdminClient;
import com.flipkart.client.ProfessorClient;
import com.flipkart.client.StudentCRSMenu;

public class UserDaoOperations implements UserDaoInterface{

	@Override
	public void signup(int userId, String password, String role) {
        Connection con = null;
        try {
            // Establishing connection
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:4000/CRS_JEDI", "root", "123456");

            
            String userQuery = "INSERT INTO user (userId, password, role) VALUES (?, ?, ?)";
            PreparedStatement userStmt = con.prepareStatement(userQuery);
            userStmt.setInt(1, userId);         // Set userId
            userStmt.setString(2, password);    // Set password
            userStmt.setString(3, role);        // Set role
            userStmt.executeUpdate();

            
            if (role.equals("student")) {
            	Scanner sc = new Scanner(System.in);
            	System.out.println("What's your batch");
            	int batch = sc.nextInt();
            	System.out.println("What's your branch");
            	String branch = sc.next();
            	
                String studentQuery = "INSERT INTO student (studentId, batch, branch, isApproved) VALUES (?, ?, ?, ?)";
                PreparedStatement studentStmt = con.prepareStatement(studentQuery);
                studentStmt.setInt(1, userId);  
                studentStmt.setInt(2,batch);  
                studentStmt.setString(3,branch);  
                studentStmt.setInt(4, 0);       
                studentStmt.executeUpdate();
            }

            
            if (role.equals("professor")) {
            	Scanner sc = new Scanner(System.in);
            	System.out.println("What's your department");
            	String department = sc.next();
                String professorQuery = "INSERT INTO professor (professorId, department) VALUES (?, ?)";
                PreparedStatement professorStmt = con.prepareStatement(professorQuery);
                professorStmt.setInt(1, userId);  
                professorStmt.setString(2,department);  
                professorStmt.executeUpdate();
            }

            con.close();
            System.out.println("Successfully registered");

        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
            System.out.println("User ID already exists. Please choose a different ID.");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Couldn't register");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println("Error closing the connection: " + e.getMessage());
            }
        }
    }

	@Override
	 public void login(int userId, String password) {
        try (
            // Establishing connection
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:4000/CRS_JEDI", "root", "123456");
            
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM user WHERE userId = ? AND password = ?")
        ) {
            stmt.setInt(1, userId);
            stmt.setString(2, password);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Login successful. Welcome, user with ID: " + userId);
                    
                    String role = rs.getString("role");
                    System.out.println("Your role is: " + role);

                    if (role.equals("student")) {
                        
                        boolean isApproved = false;

                        try (
                            PreparedStatement stmt1 = con.prepareStatement("SELECT isApproved FROM student WHERE studentId = ?")
                        ) {
                            stmt1.setInt(1, userId);

                            try (ResultSet rs1 = stmt1.executeQuery()) {
                                if (rs1.next()) {
                                    isApproved = rs1.getBoolean("isApproved");
                                }
                            }
                        } catch (SQLException e) {
                            System.out.println("Error fetching student approval status: " + e.getMessage());
                        }

                        if (isApproved) {
                            System.out.println("Student is approved by admin");
                            StudentCRSMenu studentMenu = new StudentCRSMenu();
                            studentMenu.createStudentMenu(userId);
                        } else {
                            System.out.println("Student is not approved by admin");
                        }
                    } else if (role.equals("admin")) {
                        AdminClient adminMenu = new AdminClient();
                        adminMenu.createAdminMenu(userId);
                    } else if (role.equals("professor")) {
                        ProfessorClient professorMenu = new ProfessorClient();
                        professorMenu.createProfessorMenu(userId);
                    } else {
                        System.out.println("Role not defined");
                    }
                } else {
                    System.out.println("Invalid userId or password. Please try again.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error during login: " + e.getMessage());
            System.out.println("Couldn't log in");
        }
    }

}
