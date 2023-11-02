package com.model;

import com.example.enums.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserPasswordResetRequest {
	private String username;
	private String password;
	private String confirmPassword;
}
