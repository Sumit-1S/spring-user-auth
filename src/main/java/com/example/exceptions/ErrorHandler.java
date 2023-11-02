package com.example.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.model.ErrorResponse;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(ServletRequestBindingException.class)
	public ResponseEntity<ErrorResponse> handleHeaderError(Exception execption,WebRequest request){
		List<String> details = new ArrayList<>();
		details.add(execption.getLocalizedMessage());
		ErrorResponse response = new ErrorResponse("OOPS... Header Error", details);
		return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception execption,WebRequest request){
		List<String> details = new ArrayList<>();
		details.add(execption.getLocalizedMessage());
		ErrorResponse response = new ErrorResponse("OOPS... Server Error", details);
		return new ResponseEntity(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
