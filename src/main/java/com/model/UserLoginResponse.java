package com.model;

import javax.persistence.Id;

import com.example.enums.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserLoginResponse {
	private String username;
	private UserType userType;
}
