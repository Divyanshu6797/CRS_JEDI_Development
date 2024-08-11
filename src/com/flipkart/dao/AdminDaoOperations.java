package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;

public class AdminDaoOperations implements AdminDaoInterface {

    @Override
    public void approveStudent(int studentId) {
        try (
            Connection con = new DatabaseConnection().getConnection();
            PreparedStatement searchStmt = con.prepareStatement("SELECT studentId, batch, branch, isApproved FROM student WHERE studentId = ?");
            PreparedStatement updateStmt = con.prepareStatement("UPDATE student SET isApproved = 1 WHERE studentId = ?")
        ) {
            searchStmt.setInt(1, studentId);
            ResultSet rs = searchStmt.executeQuery();

            if (rs.next()) {
                updateStmt.setInt(1, studentId);
                updateStmt.executeUpdate();

                System.out.println("Student approved successfully.");
                System.out.println("studentId: " + rs.getInt("studentId"));
                System.out.println("batch: " + rs.getString("batch"));
                System.out.println("branch: " + rs.getString("branch"));
            } else {
                System.out.println("Student with ID " + studentId + " not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void addCourse(Course course) {
        try (
            Connection con = new DatabaseConnection().getConnection();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO course (courseId, courseName, instructorId, instructorName, filledSeats, credit) VALUES (?, ?, ?, ?, ?, ?)")
        ) {
            stmt.setInt(1, course.getCourseId());
            stmt.setString(2, course.getCourseName());
            stmt.setInt(3, course.getInstructorId());
            stmt.setString(4, course.getInstructorName());
            stmt.setInt(5, course.getFilledSeats());
            stmt.setInt(6, course.getCredit());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Couldn't add course.");
        }
    }

    @Override
    public void addProfessor(Professor professor) {
        try  {
        	Connection con = new DatabaseConnection().getConnection();
            PreparedStatement userStmt = con.prepareStatement("INSERT INTO user (userId, password, role) VALUES (?, ?, ?)");
            PreparedStatement professorStmt = con.prepareStatement("INSERT INTO professor (professorId, department) VALUES (?, ?)");
            // Insertion into user table
            int userId = professor.getProfessorId();
            String password = String.valueOf(userId); 
            userStmt.setInt(1, userId);
            userStmt.setString(2, password);
            userStmt.setString(3, "professor");

            int userRowsAffected = userStmt.executeUpdate();
            System.out.println("Rows affected in user table: " + userRowsAffected);

            
            professorStmt.setInt(1, userId);
            professorStmt.setString(2, professor.getDepartment());

            int professorRowsAffected = professorStmt.executeUpdate();
            System.out.println("Rows affected in professor table: " + professorRowsAffected);

            
            
            
            System.out.println("Professor added successfully.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Couldn't add professor.");
        }
    }


    @Override
    public void viewAllProfessors() {
        try (
            Connection con = new DatabaseConnection().getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT professorId, department FROM professor");
            ResultSet rs = stmt.executeQuery()
        ) {
            System.out.println("professorId | department");
            System.out.println("------------------------");
            while (rs.next()) {
                int professorId = rs.getInt("professorId");
                String department = rs.getString("department");

                System.out.println(professorId + " | " + department);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Couldn't retrieve professors.");
        }
    }

    @Override
    public void viewAllStudents() {
        try (
            Connection con = new DatabaseConnection().getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT studentId, batch, branch, isApproved FROM student");
            ResultSet rs = stmt.executeQuery()
        ) {
            System.out.println("studentId | batch | branch | isApproved");
            System.out.println("----------------------------------------");
            while (rs.next()) {
                int studentId = rs.getInt("studentId");
                String batch = rs.getString("batch");
                String branch = rs.getString("branch");
                boolean isApproved = rs.getBoolean("isApproved");

                System.out.println(studentId + " | " + batch + " | " + branch + " | " + isApproved);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Couldn't retrieve students.");
        }
    }

    @Override
    public void viewAllCourses() {
        try (
            Connection con = new DatabaseConnection().getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM course");
            ResultSet rs = stmt.executeQuery()
        ) {
            System.out.printf("%-10s %-20s %-20s %-12s %-12s %-6s\n",
                              "Course ID", "Course Name", "Instructor Name", "Instructor ID", "Filled Seats", "Credit");
            System.out.println("-----------------------------------------------------------------------------------------------");
            while (rs.next()) {
                int courseId = rs.getInt("courseId");
                String courseName = rs.getString("courseName");
                String instructorName = rs.getString("instructorName");
                int instructorId = rs.getInt("instructorId");
                int filledSeats = rs.getInt("filledSeats");
                int credit = rs.getInt("credit");

                System.out.printf("%-10d %-20s %-20s %-12d %-12d %-6d\n",
                                  courseId, courseName, instructorName, instructorId, filledSeats, credit);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Couldn't retrieve course data.");
        }
    }
}
