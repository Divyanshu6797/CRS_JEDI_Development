package com.flipkart.client;

import java.util.Scanner;

public class ProfessorClient {
	
	public void createProfessorMenu(int professorId) {
		boolean flag = true;
		while(flag) {
			System.out.println("Welcome to Professor Activity");
			System.out.println("1. Add courses to teach");
			System.out.println("2. view students");
			System.out.println("3. Grade Student");
			System.out.println("4. Logout");
			
			Scanner sc = new Scanner(System.in);
			int professorActivity = sc.nextInt();
			
			
			switch(professorActivity) {
			case 1:
				addCoursesToTeach();
				flag = false;
				break;
			case 2:
				viewStudents();
				flag = false;
				break;
			case 3:
				gradeStudent();
				flag = false;
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
	
	void addCoursesToTeach() {
		System.out.println("courses added");
		
	}
	void viewStudents() {
		System.out.println("students list");
	}
	void gradeStudent() {
		System.out.println("student graded");
		
		
	}
	
	

}
