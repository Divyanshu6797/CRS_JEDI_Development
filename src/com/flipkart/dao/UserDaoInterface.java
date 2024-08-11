package com.flipkart.dao;

public interface UserDaoInterface {
	void signup(int userId, String password, String role);
	void login(int userId, String password);

}
