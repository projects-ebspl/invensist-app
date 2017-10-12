package com.invensist.models;

public class MessageModel {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public MessageModel withMessage(String message) {
		this.message = message;
		return this;
	}
}
