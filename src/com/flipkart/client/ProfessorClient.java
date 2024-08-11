package com.flipkart.client;

import java.util.Scanner;

import com.flipkart.business.ProfessorOperations;

public class ProfessorClient {
	
	public void createProfessorMenu(int professorId) {
		boolean flag = true;
		while(flag) {
			System.out.println("Welcome to Professor Activity");
			System.out.println("1. view courses");
			System.out.println("2. view students");
			System.out.println("3. Grade Student");
			System.out.println("4. Logout");
			
			Scanner sc = new Scanner(System.in);
			int professorActivity = sc.nextInt();
			
			
			switch(professorActivity) {
			case 1:
				viewCourses(professorId);
				
				break;
			case 2:
				viewStudents(professorId);
				break;
			case 3:
				gradeStudent(professorId);
				break;
			case 4:
				
				System.out.println("logged out");
				flag = false;
				break;
			default :
				System.out.println("invalid choice");
				flag=false;
				break;
		}
		}
	}
	
	void viewCourses(int professorId) {
		ProfessorOperations pOp = new ProfessorOperations();
		pOp.viewCourses(professorId);
		
		
	}
	void viewStudents(int professorId) {
		ProfessorOperations pOp = new ProfessorOperations();
		pOp.viewStudents(professorId);
	}
	void gradeStudent(int professorId) {
		Scanner sc= new Scanner(System.in);
		System.out.println("enter student id");
		int studentId = sc.nextInt();
		System.out.println("enter courseId");
		int courseId = sc.nextInt();
		
		ProfessorOperations pOp = new ProfessorOperations();
		pOp.gradeStudent(professorId, studentId, courseId);
		
		
		
	}
	
	

}
