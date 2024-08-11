package com.flipkart.dao;

public interface StudentDaoInterface {
	void viewCourses();
	void viewRegisteredCourses(int studentId);
	void addCourse(int srudentId, int courseId);
	void dropCourse(int studentId, int courseId);
	
	
	

}
