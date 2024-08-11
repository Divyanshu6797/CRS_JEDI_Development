package com.flipkart.client;

import java.util.Scanner;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.business.AdminOperations;

public class AdminClient {
	Scanner in = new Scanner(System.in);
	
	public void createAdminMenu(int adminId) {
		boolean flag = true;
		while(flag) {
			System.out.println("Welcome to Admin Activity");
			System.out.println("1.Authenticate Student");
			System.out.println("2.Add course in Course List");
			System.out.println("3.Add Professor");
			
			System.out.println("4.View all students");
			System.out.println("5.View all professors");
			System.out.println("6.View all courses");
			System.out.println("7. Logout");
			
			int adminActivity = in.nextInt();
			in.nextLine();
			
			switch(adminActivity) {
			case 1:
				authenticateStudentRegistration();
				break;
			case 2:
				addCourseInCourseList();
				break;
			case 3:
				addProfessor();
				break;
			
			case 4:
				viewAllStudents();
				
				break;
			case 5:
				viewAllProfessors();
				break;
			
			case 6:
				viewAllCourses();
				break;
			case 7:
				flag = false;
				break;
			default :
				System.out.println("Enter a valid choice");
				
				break;
		}
		}
	}
	private void viewAllProfessors() {
		AdminOperations adminOp = new AdminOperations();
		adminOp.viewAllProfessors();
		
	}
	private void viewAllStudents() {
		AdminOperations adminOp = new AdminOperations();
		adminOp.viewAllStudents();
		
	}
	private void viewAllCourses() {
		AdminOperations adminOp = new AdminOperations();
		adminOp.viewAllCourses();
		
	}
	private void authenticateStudentRegistration() {
		System.out.println("Enter student id/user id");
		Scanner sc = new Scanner(System.in);
		int studentId = sc.nextInt();
		AdminOperations adminOp = new AdminOperations();
		adminOp.approveStudent(studentId);
		
		
	}
	
	private void addCourseInCourseList() {
		int courseId;
	    String courseName;
	    int instructorId;
	    String instructorName;
	    int filledSeats;
	    int credit;
	    
	    Scanner scanner = new Scanner(System.in);
	    
	    System.out.print("Enter Course ID: ");
        courseId = scanner.nextInt();
        
       

        System.out.print("Enter Course Name: ");
        courseName = scanner.next();

        System.out.print("Enter Instructor ID: ");
        instructorId = scanner.nextInt();
        
        

        System.out.print("Enter Instructor Name: ");
        instructorName = scanner.next();

        System.out.print("Enter Filled Seats: ");
        filledSeats = scanner.nextInt();
        
        System.out.print("Enter Credit: ");
        credit = scanner.nextInt();
        
        Course course = new Course();
        
        course.setCourseId(courseId);
        course.setCourseName(courseName);
        course.setCredit(credit);
        course.setFilledSeats(filledSeats);
        course.setInstructorId(instructorId);
        course.setInstructorName(instructorName);
        
        
        AdminOperations adminOp = new AdminOperations();
		adminOp.addCourse(course);
        
        
		
	}
	
	private void addProfessor() {
		int professorId;
		String department;
		
		Scanner scanner = new Scanner(System.in);
	    
	    System.out.print("Enter professorId ID: ");
        professorId = scanner.nextInt();
        
       

        System.out.print("Enter Professor Department ");
        department = scanner.next();
        
        Professor professor = new Professor();
        professor.setDepartment(department);
        professor.setProfessorId(professorId);
        
        AdminOperations adminOp = new AdminOperations();
		adminOp.addProfessor(professor);
        
        

		
	}
	
	
	
	

}
