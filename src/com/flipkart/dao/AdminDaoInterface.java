package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;

public interface AdminDaoInterface {
	void approveStudent(int studentId);
	void addCourse(Course course);
	
	void addProfessor(Professor professor);
	void viewAllProfessors();
	void viewAllStudents();
	void viewAllCourses();
	

}
