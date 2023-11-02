package com.model;

import java.util.List;

public class ErrorResponse {
	private String Message;
	private List<String> details;
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public List<String> getDetails() {
		return details;
	}
	public void setDetails(List<String> details) {
		this.details = details;
	}
	public ErrorResponse(String message, List<String> details) {
		super();
		Message = message;
		this.details = details;
	}
	
}
