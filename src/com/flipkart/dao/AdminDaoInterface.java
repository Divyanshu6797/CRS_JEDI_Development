package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;

public interface AdminDaoInterface {
	void approveStudent(int studentId);
	void addourse(Course course);
	void removeCourse(Course course);
	void addProfessor(Professor professor);
	

}
