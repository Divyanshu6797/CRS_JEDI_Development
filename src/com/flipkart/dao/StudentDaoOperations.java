package com.flipkart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDaoOperations implements StudentDaoInterface {

    @Override
    public void viewCourses() {
        try {
            // Establishing connection
        	DatabaseConnection dbConnect = new DatabaseConnection();
        	Connection con = dbConnect.getConnection();
            
           
            String query = "SELECT * FROM course";
            PreparedStatement stmt = con.prepareStatement(query);
            
            
            ResultSet rs = stmt.executeQuery();
            
            
            System.out.printf("%-10s %-20s %-20s %-12s %-12s %-6s\n",
                              "Course ID", "Course Name", "Instructor Name", "Instructor ID", "Filled Seats", "Credit");
            System.out.println("-----------------------------------------------------------------------------------------------");
            
            
            while (rs.next()) {
                int courseId = rs.getInt("courseid");
                String courseName = rs.getString("courseName");
                String instructorName = rs.getString("instructorName");
                int instructorId = rs.getInt("instructorId");
                int filledSeats = rs.getInt("filledSeats");
                int credit = rs.getInt("credit");
                
                
                System.out.printf("%-10d %-20s %-20s %-12d %-12d %-6d\n",
                                  courseId, courseName, instructorName, instructorId, filledSeats, credit);
            }

            
            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Couldn't retrieve course data");
        }
    }

    @Override
    public void viewRegisteredCourses(int studentId) {
        try {
        	// Establishing connection
        	DatabaseConnection dbConnect = new DatabaseConnection();
        	Connection con = dbConnect.getConnection();
            
           
            String query = "SELECT * FROM registeredcourse WHERE studentId = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            
            System.out.println("Working...");
            
           
            stmt.setInt(1, studentId);
            
          
            ResultSet rs = stmt.executeQuery();
            
           
            System.out.printf("%-12s %-20s %-5s %-6s\n", "Course ID", "Course Name", "Grade", "Credit");
            System.out.println("----------------------------------------------------");
            
            
            while (rs.next()) {
                int courseId = rs.getInt("courseid"); // Adjusted to match your column names
                String courseName = rs.getString("registeredCoursename");
                String grade = rs.getString("grade");
                int credit = rs.getInt("credit");
                
                // Print the row data with formatted output
                System.out.printf("%-12d %-20s %-5s %-6d\n", courseId, courseName, grade, credit);
            }
            
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Couldn't retrieve registered course data");
        }
    }

	@Override
	 public void addCourse(int studentId, int courseId) {
        DatabaseConnection dbConnect = new DatabaseConnection();
        Connection con = dbConnect.getConnection();

        try {
            
            String checkCourseQuery = "SELECT * FROM course WHERE courseid = ?";
            PreparedStatement checkCourseStmt = con.prepareStatement(checkCourseQuery);
            checkCourseStmt.setInt(1, courseId);
            ResultSet rs = checkCourseStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Course doesn't exist");
            } else {
                
                String courseName = rs.getString("courseName");
                int credit = rs.getInt("credit");
                int filledSeats = rs.getInt("filledSeats");

               
                String updateSeatsQuery = "UPDATE course SET filledSeats = ? WHERE courseid = ?";
                PreparedStatement updateSeatsStmt = con.prepareStatement(updateSeatsQuery);
                updateSeatsStmt.setInt(1, filledSeats + 1);
                updateSeatsStmt.setInt(2, courseId);
                updateSeatsStmt.executeUpdate();

                
                String addCourseQuery = "INSERT INTO registeredcourse (courseId, registeredCourseName, studentId, credit) VALUES (?, ?, ?, ?)";
                PreparedStatement addCourseStmt = con.prepareStatement(addCourseQuery);
                addCourseStmt.setInt(1, courseId);
                addCourseStmt.setString(2, courseName);
                addCourseStmt.setInt(3, studentId);
                addCourseStmt.setInt(4, credit);
                addCourseStmt.executeUpdate();

                System.out.println("Course successfully added to the registered courses.");
            }

            
            rs.close();
            checkCourseStmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Couldn't add the course.");
        }
    }


		
		
		
	 public void dropCourse(int studentId, int courseId) {
	        DatabaseConnection dbConnect = new DatabaseConnection();
	        Connection con = dbConnect.getConnection();

	        try {
	           
	            String checkRegistrationQuery = "SELECT * FROM registeredcourse WHERE studentId = ? AND courseId = ?";
	            PreparedStatement checkRegistrationStmt = con.prepareStatement(checkRegistrationQuery);
	            checkRegistrationStmt.setInt(1, studentId);
	            checkRegistrationStmt.setInt(2, courseId);
	            ResultSet rs = checkRegistrationStmt.executeQuery();

	            if (!rs.next()) {
	                System.out.println("The course is not registered by the student.");
	            } else {
	                
	                String courseName = rs.getString("registeredCourseName");
	                int credit = rs.getInt("credit");

	       
	                String deleteCourseQuery = "DELETE FROM registeredcourse WHERE studentId = ? AND courseId = ?";
	                PreparedStatement deleteCourseStmt = con.prepareStatement(deleteCourseQuery);
	                deleteCourseStmt.setInt(1, studentId);
	                deleteCourseStmt.setInt(2, courseId);
	                deleteCourseStmt.executeUpdate();

	               
	                String updateSeatsQuery = "UPDATE course SET filledSeats = filledSeats - 1 WHERE courseid = ?";
	                PreparedStatement updateSeatsStmt = con.prepareStatement(updateSeatsQuery);
	                updateSeatsStmt.setInt(1, courseId);
	                updateSeatsStmt.executeUpdate();

	               
	                
	                System.out.printf("Course removed. Details : Course ID: %d, Course Name: %s, Credit: %d\n", courseId, courseName, credit);
	            }

	            
	            rs.close();
	            checkRegistrationStmt.close();
	            con.close();

	        } catch (Exception e) {
	            System.out.println(e);
	            System.out.println("Couldn't drop the course.");
	        }
	    }
	
}
