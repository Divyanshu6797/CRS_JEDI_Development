package com.flipkart.business;

public interface ProfessorInterface {
	void viewCourses(int professorId);
	void viewStudents(int professorId);
	void gradeStudent(int professorId, int studentId, int courseId);
	

}
