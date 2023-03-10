package com.teomangungoren.rest.webservices.restfulwebservices.exception;

import java.time.LocalDate;

public class ErrorDetails {

	public LocalDate timestamp;
	public String message;
	public String details;
	
	public ErrorDetails(LocalDate timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public LocalDate getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
	
	
}
