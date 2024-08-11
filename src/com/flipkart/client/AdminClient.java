package com.flipkart.client;

import java.util.Scanner;

public class AdminClient {
	Scanner in = new Scanner(System.in);
	
	public void createAdminMenu(int adminId) {
		boolean flag = true;
		while(flag) {
			System.out.println("Welcome to Admin Activity");
			System.out.println("1.Authenticate Student");
			System.out.println("2.Add course in Course List");
			System.out.println("3.Add Professor");
			System.out.println("4.Remove Professor");
			
			System.out.println("5.Logout");
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
				removeProfessor();
				break;
			case 5:
				System.out.println("Logging Out!!");
				adminActivity = false;
				break;
			default :
				System.out.println("Enter a valid choice");
				flag=false;
				break;
		}
		}
	}
	private void authenticateStudentRegistration() {
		System.out.println("Student Authentication Complete");
	}
	
	private void addCourseInCourseList() {
		System.out.println("Courses Updated");
	}
	
	private void addProfessor() {
		System.out.println("Professor added");
	}
	
	private void removeProfessor() {
		System.out.println("Professor removed");
	}
	
	

}
