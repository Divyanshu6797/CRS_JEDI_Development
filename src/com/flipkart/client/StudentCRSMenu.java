package com.flipkart.client;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.flipkart.business.StudentOperations;

public class StudentCRSMenu {

    public void createStudentMenu(int studentId) {
        boolean logginFlag = true;
        Scanner sc = new Scanner(System.in);

        while (logginFlag) {
            System.out.println("----------Welcome To Student Menu StudentID : ----------");
            System.out.println("1. Add Course");
            System.out.println("2. Drop Course");
            System.out.println("3. View Course");
            System.out.println("4. View Registered Course");
            System.out.println("5. View Grade Card");
            System.out.println("6. Check If Approved");
            System.out.println("7. Make Payment");
            System.out.println("8. LogOut");
            System.out.println("9. Initiate Registration");
            
            System.out.println("---------------------------------------------");

            try {
                

                System.out.println("Enter operation number to perform:");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        addCourse(studentId);
                        
                        break;
                    case 2:
                        dropCourse(studentId);
                        break;
                    case 3:
                        viewCourse(studentId);
                        break;
                    case 4:
                        viewRegisteredCourses(studentId);
                        break;
                    case 5:
                        viewGradeCard(studentId);
                        break;
                    case 6:
                        checkIfApproved(studentId);
                        break;
                    case 7:
                        makePayment(studentId);
                        break;
                    case 8:
                    	
                        logginFlag = false;
                        break;
                    case 9:
                        startRegistration();
                        logginFlag = false;
                        break;
              
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // Clear the invalid input
            }
        }

        System.out.println("You are logged out.");
        sc.close();
    }

    public void startRegistration() {
        StudentOperations sOp = new StudentOperations();
        sOp.initiateRegistration();
    }

    public void checkIfApproved(int studentId) {
        // Implementation here
    }

    public void viewGradeCard(int studentId) {
        // Implementation here
    }

   

    public void viewCourse(int studentId) {
        StudentOperations sOp = new StudentOperations();
        sOp.viewCourses();
        
        
    }

    public void dropCourse(int studentId) {
    	
    	System.out.println("Choose course to drop");
    	StudentOperations sOp = new StudentOperations();
        sOp.viewRegisteredCourses(studentId);
        
        System.out.println("Enter courseId");
        Scanner sc = new Scanner(System.in);
        int courseId = sc.nextInt();
       
        sOp.dropCourse(studentId, courseId);
        
        
        
    }

    public void addCourse(int studentId) {
        Scanner sc = new Scanner(System.in);
        int courseId = sc.nextInt();
        
        StudentOperations sOp = new StudentOperations();
        sOp.addCourse(studentId, courseId);
        
    }

    public void makePayment(int studentId) {
        // Implementation here
    }
    public void viewRegisteredCourses(int studentId) {
    	
    	StudentOperations sOp = new StudentOperations();
        sOp.viewRegisteredCourses(studentId);
    	
    	
    }
}
