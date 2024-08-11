package com.flipkart.business;

import com.flipkart.dao.StudentDaoOperations;

public class StudentOperations implements StudentInterface {

	@Override
	public void initiateRegistration() {
		System.out.println("dbed");
		
	}

	@Override
	public void viewCourses() {
		StudentDaoOperations studentDao = new StudentDaoOperations();
		studentDao.viewCourses();
		
		
		
	}

	@Override
	public void addCourse(int studentId, int courseId) {
		StudentDaoOperations studentDao = new StudentDaoOperations();
		studentDao.addCourse(studentId, courseId);
		
		
		
	}

	@Override
	public void viewRegisteredCourses(int studentId) {
		StudentDaoOperations studentDao = new StudentDaoOperations();
		studentDao.viewRegisteredCourses(studentId);
		
	}

	@Override
	public void dropCourse(int studentId, int courseId) {
		StudentDaoOperations studentDao = new StudentDaoOperations();
		studentDao.dropCourse(studentId, courseId);
		
		
	}
	

}
