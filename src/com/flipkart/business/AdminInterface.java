package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

public interface AdminInterface {
	void addCourse(Course course);
	void addProfessor(Professor professor);
	
	void approveStudent(int studentId);
	
	void viewAllProfessors();
	void viewAllStudents();
	void viewAllCourses();
	
	

}
