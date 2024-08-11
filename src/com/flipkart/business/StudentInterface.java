package com.flipkart.business;

public interface StudentInterface {
	void initiateRegistration();
	void viewCourses();
	void addCourse(int studentId, int courseId);
	void viewRegisteredCourses(int studentId);
	void dropCourse(int studentId, int courseId);
	

}
