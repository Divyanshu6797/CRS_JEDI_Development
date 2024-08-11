package com.flipkart.business;

import com.flipkart.dao.UserDaoOperations;

public class UserBusiness implements UserInterface{

	@Override
	public void signup(int userId, String password, String role) {
		UserDaoOperations userDao= new UserDaoOperations();
		
		userDao.signup(userId, password, role);
		
		
		
	}

	@Override
	public void login(int userId, String password) {
       UserDaoOperations userDao= new UserDaoOperations();
		
		userDao.login(userId, password);
		
	}

}
