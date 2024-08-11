package com.flipkart.business;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

public interface AdminInterface {
	void addProfessor(Professor professor);
	void removeProfessor(Professor professor);
	void approveStudent(int studentId);
	void updateCourse();
	

}
