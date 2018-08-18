package com.example.demo.model;

public class BaseResponse {
 
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public BaseResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BaseResponse(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	String status;
	String message;
	
}
