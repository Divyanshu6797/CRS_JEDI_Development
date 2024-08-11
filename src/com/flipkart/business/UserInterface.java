package com.flipkart.business;

public interface UserInterface {
	void signup(int userId, String password, String role);
	void login(int userId, String password);

}
