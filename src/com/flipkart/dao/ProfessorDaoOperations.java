package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ProfessorDaoOperations implements ProfessorDaoInterface{

	@Override
	public void viewCourses(int professorId) {
	    try  {
Connection con = new DatabaseConnection().getConnection();
	        
	        // Preparing SQL query to fetch courses where professorId = instructorId
	        PreparedStatement stmt = con.prepareStatement("SELECT courseid, courseName, credit, filledSeats FROM course WHERE instructorId = ?");
	        
	        stmt.setInt(1, professorId);

	        
	        ResultSet rs = stmt.executeQuery();

	      
	        System.out.println("Course ID | Course Name         | Credit | Filled Seats");
	        System.out.println("------------------------------------------------------");
	        while (rs.next()) {
	            int courseId = rs.getInt("courseid");
	            String courseName = rs.getString("courseName");
	            int credit = rs.getInt("credit");
	            int filledSeats = rs.getInt("filledSeats");

	            System.out.printf("%-9d | %-20s | %-6d | %-12d\n",
	                              courseId, courseName, credit, filledSeats);
	        }

	        

	    } catch (Exception e) {
	        System.out.println("Error: " + e.getMessage());
	        System.out.println("Couldn't retrieve course data.");
	    }
	}


	@Override
	public void viewStudents(int professorId) {
	    try  {
Connection con = new DatabaseConnection().getConnection();
	        
	        
	        PreparedStatement stmt = con.prepareStatement(
	            "SELECT courseId, studentId, registeredCoursename, grade, credit " +
	            "FROM registeredcourse WHERE instructorId = ?");
	        
	        stmt.setInt(1, professorId);

	       
	        ResultSet rs = stmt.executeQuery();

	        
	        System.out.println("Course ID | Student ID | Course Name         | Grade | Credit");
	        System.out.println("-------------------------------------------------------------");
	        while (rs.next()) {
	            int courseId = rs.getInt("courseId");
	            int studentId = rs.getInt("studentId");
	            String courseName = rs.getString("registeredCoursename");
	            String grade = rs.getString("grade");
	            int credit = rs.getInt("credit");

	            System.out.printf("%-9d | %-10d | %-20s | %-5s | %-6d\n",
	                              courseId, studentId, courseName, grade, credit);
	        }

	       

	    } catch (Exception e) {
	        System.out.println("Error: " + e.getMessage());
	        System.out.println("Couldn't retrieve student data.");
	    }
	}


	@Override
	 public void gradeStudent(int professorId, int studentId, int courseId) {
        // Initialize Scanner to take input
        Scanner scanner = new Scanner(System.in);

        // Prompt the professor to input the grade
        System.out.print("Enter the grade for student with ID " + studentId + " in course with ID " + courseId + ": ");
        String grade = scanner.nextLine();

        try  {
Connection con = new DatabaseConnection().getConnection();

            
            PreparedStatement stmt = con.prepareStatement(
                "UPDATE registeredcourse SET grade = ? " +
                "WHERE studentId = ? AND courseId = ? AND instructorId = ?");
            stmt.setString(1, grade);           // Set the grade
            stmt.setInt(2, studentId);          // Set the student ID
            stmt.setInt(3, courseId);           // Set the course ID
            stmt.setInt(4, professorId);        // Set the professor's instructor ID

           
            int rowsAffected = stmt.executeUpdate();

            
            if (rowsAffected > 0) {
                System.out.println("Grade updated successfully for student ID " + studentId + " in course ID " + courseId + ".");
            } else {
                System.out.println("Failed to update grade. Please check the student ID, course ID, and professor ID.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Couldn't update the grade.");
        }
    }
	
	

}
