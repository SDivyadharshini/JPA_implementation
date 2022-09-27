package com.example.jdbc.project_JPA.constants;

import org.springframework.stereotype.Component;

@Component
public final class ERROR_Message {
	
	private ERROR_Message()
	{
	}
	
	public static final String ROOM_ALREADY_EXISTS = "Room Number Already Exists";
	
	public static final String ROOM_NOT_FOUND = "Room not Found";

}
