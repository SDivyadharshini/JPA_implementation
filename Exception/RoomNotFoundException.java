package com.example.jdbc.project_JPA.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoomNotFoundException extends RuntimeException {

	public RoomNotFoundException(String message) {
		super(message);
		
	}

}
