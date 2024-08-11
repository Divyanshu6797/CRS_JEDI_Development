package com.flipkart.dao;

public interface ProfessorDaoInterface {
	void viewCourses(int professorId);
	void viewStudents(int professorId);
	void gradeStudent(int professorId, int studentId, int courseId);

}
