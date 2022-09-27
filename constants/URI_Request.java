package com.example.jdbc.project_JPA.constants;

import org.springframework.stereotype.Component;

@Component
public final class URI_Request {
	
	private URI_Request()
	{
		
	}
	
	public static final String ALL_ROOMS = "/roomDetails";
	
	public static final String HOTEL_ROOMS = "/roomDetails/";
	
	public static final String PARTICULAR_ROOM = "/roomDetails/{roomNo}";
	
	public static final String CREATE_ROOM = "/roomDetails/create";
	
	public static final String UPDATE_ROOM = "/roomDetails";
	
	public static final String DELETE_ROOM = "/roomDetails/{roomNo}";
	
	
	
	

}
