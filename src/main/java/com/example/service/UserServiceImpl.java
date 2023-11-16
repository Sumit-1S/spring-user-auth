package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DAO.UserDAO;
import com.example.entity.User;
import com.model.UserLoginRequest;
import com.model.UserLoginResponse;
import com.model.UserPasswordResetRequest;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public String addUser(User user) throws Exception {
		ArrayList<User> userLs = this.getAllUser();
		for(User u: userLs) {
			if(u.getUsername().equals(user.getUsername()))
				return "OOOPs... User with similar Username Exists";
		}
		userDAO.save(user);
		return "User Added";
	}

	@Override
	public ArrayList<User> getAllUser() {
		return (ArrayList<User>)userDAO.findAll();
	}

	@Override
	public String updatePassword(UserPasswordResetRequest user) {
		Optional<User> oldUser = userDAO.findById(user.getUsername());
		if(oldUser.isPresent())
		{
			User u = oldUser.get();
			userDAO.updatePassword(u.getUsername(),user.getPassword());
			return "Update Successfull";
		}
		return "User not available/registered";
	}

	@Override
	public UserLoginResponse verifyUser(UserLoginRequest userRequest) {
		Optional<User> user = userDAO.findById(userRequest.getUsername());
		if(user.isPresent() && user.get().getPassword().equals(userRequest.getPassword()))
		{
			return new UserLoginResponse(user.get().getUsername(),user.get().getUserType());
		}
		return new UserLoginResponse();
	}
	
	
}
