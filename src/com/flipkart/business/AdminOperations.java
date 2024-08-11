package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.AdminDaoOperations;

public class AdminOperations implements AdminInterface {
	


	public void addProfessor(Professor professor) {
		AdminDaoOperations adminDao = new AdminDaoOperations();
		adminDao.addProfessor(professor);
		
	}

	

	
	public void approveStudent(int studentId) {
		AdminDaoOperations adminDao = new AdminDaoOperations();
		adminDao.approveStudent(studentId);
		
	}

	
	@Override
	public void viewAllProfessors() {
		AdminDaoOperations adminDao = new AdminDaoOperations();
		adminDao.viewAllProfessors();
		
	}

	@Override
	public void viewAllStudents() {
		AdminDaoOperations adminDao = new AdminDaoOperations();
		adminDao.viewAllStudents();
		
	}

	@Override
	public void viewAllCourses() {
		AdminDaoOperations adminDao = new AdminDaoOperations();
		adminDao.viewAllCourses();
		
	}

	@Override
	public void addCourse(Course course) {
		AdminDaoOperations adminDao = new AdminDaoOperations();
		adminDao.addCourse(course);
		
	}

}
