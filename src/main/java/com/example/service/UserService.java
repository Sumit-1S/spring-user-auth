package com.example.service;

import java.util.ArrayList;

import com.example.entity.User;
import com.model.UserLoginRequest;
import com.model.UserPasswordResetRequest;


public interface UserService {
	public String addUser(User user)throws Exception ;
	public ArrayList<User> getAllUser();
	public String updatePassword(UserPasswordResetRequest user);
	public Boolean verifyUser(UserLoginRequest user);
}
