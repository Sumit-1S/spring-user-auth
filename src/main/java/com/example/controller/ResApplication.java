package com.example.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.service.UserService;
import com.example.utils.EncryptPassword;
import com.model.UserRegisterRequest;
import com.model.UserLoginRequest;
import com.model.UserPasswordResetRequest;


@Controller
@RequestMapping("/loginService")
public class ResApplication {
	
	@Autowired
	private UserService service;
	
	EncryptPassword encoder = new EncryptPassword();
	
	@PostMapping("/register")
	@ResponseBody
	public String registerValidate(@ModelAttribute UserRegisterRequest user) throws Exception{
		if(user.getPassword().equals(user.getConfirmPassword())) {
			return service.addUser(new User(user.getUsername(),encoder.encryptPassword(user.getPassword()),user.getUserType()));
		}
		return "Passwords Don't Match!!!";
	}
	
	@PostMapping("/forgetpassword")
	@ResponseBody
	@Transactional
	public String forgetPassword(@ModelAttribute UserPasswordResetRequest user) throws Exception{
		user.setPassword(encoder.encryptPassword(user.getPassword()));
		user.setConfirmPassword(encoder.encryptPassword(user.getConfirmPassword()));
		return service.updatePassword(user);	
	}
	
	@PostMapping("/login")
	@ResponseBody
	public Boolean login(@ModelAttribute UserLoginRequest user) throws Exception{
		user.setPassword(encoder.encryptPassword(user.getPassword()));
		System.out.println(user.getPassword());
		return service.verifyUser(user);	
	}
	

}
