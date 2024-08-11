package com.flipkart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;

public class AdminDaoOperations {
	
	public static void main(String args[]) {
		try{
			System.out.print(false);
//			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:4000/CRS_JEDI","root","123456");
			System.out.println(con);
			
			PreparedStatement stmt=con.prepareStatement("insert into student values(1001,'ss')");
			stmt.executeUpdate();
			
			
			
			con.close();
			System.out.println("working");

		}
		catch(Exception e) {
		System.out.println(e);
		System.out.println("not working");
		
		}
		
	}
	
	void approveStudent(int studentId) {
		
	}
	

	


}
