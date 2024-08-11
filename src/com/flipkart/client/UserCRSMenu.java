package com.flipkart.client;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.flipkart.business.UserBusiness;

public class UserCRSMenu {
    
    public void createUserMenu() {
        boolean logginFlag = true;
        Scanner sc = new Scanner(System.in);

        while (logginFlag) {
            System.out.println("----------Welcome To User menu : ----------");
            System.out.println("1. Login ");
            System.out.println("2. Signup");
            

            try {
                System.out.println("Enter operation number to perform:");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        login();
                        logginFlag = false;
                        break;
                    case 2:
                        signup();
                        logginFlag = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter valid credentials.");
                sc.next(); // Clear the invalid input
            }
        }

        System.out.println("You are logged out.");
        sc.close();
    }
    
    public void login() {
    	Scanner sc = new Scanner(System.in);
        System.out.println("enter user id(integer)");
        int userId = sc.nextInt();
        
        System.out.println("enter password");
        String password = sc.next();
        
        UserBusiness uOp = new UserBusiness();
        uOp.login(userId, password);
        
        
    }

    public void signup() {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter new user id(integer)");
        int userId = sc.nextInt();
        
        System.out.println("enter new password");
        String password = sc.next();
        
        
        
        System.out.println("confirm password");
        String confirmPassword = sc.next();
        
        
        if(!password.equals(confirmPassword)) {
        	System.out.println("passwords don't match");
        	return;
        	
        }
        
        System.out.println("Enter role");
        String role = sc.next();
        
        UserBusiness uOp = new UserBusiness();
        uOp.signup(userId, confirmPassword, role);
        
        
        
        
    }
}
