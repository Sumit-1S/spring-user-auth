package com.example.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.service.UserService;
import com.model.UserRegisterRequest;
import com.model.UserLoginRequest;
import com.model.UserLoginResponse;
import com.model.UserPasswordResetRequest;


@Controller
@RequestMapping("/loginService")
@CrossOrigin("http://localhost:3002")
public class ResApplication {
	
	@Autowired
	private UserService service;
	
	
	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<String> registerValidate(@RequestBody UserRegisterRequest user) throws Exception{
		System.out.println(user);
		if(user.getPassword().equals(user.getConfirmPassword())) {
			return new ResponseEntity<>(service.addUser(new User(user.getUsername(),user.getPassword(),user.getUserType())),HttpStatus.OK);
		}
		return new ResponseEntity<>("Passwords Don't Match!!!",HttpStatus.OK);
	}
	
	@PutMapping("/forgetpassword")
	@ResponseBody
	@Transactional
	public String forgetPassword(@RequestBody UserPasswordResetRequest user) throws Exception{
		user.setPassword(user.getPassword());
		user.setConfirmPassword(user.getConfirmPassword());
		return service.updatePassword(user);	
	}
	
	@PostMapping("/login")
	@ResponseBody
	public UserLoginResponse login(@RequestBody UserLoginRequest user) throws Exception{
		return service.verifyUser(user);	
	}
	

}
