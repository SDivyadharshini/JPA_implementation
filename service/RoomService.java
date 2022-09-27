package com.example.jdbc.project_JPA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.jdbc.project_JPA.Bean.HotelRoom;

import com.example.jdbc.project_JPA.Exception.RoomAlreadyExistsException;
import com.example.jdbc.project_JPA.Exception.RoomNotFoundException;
import com.example.jdbc.project_JPA.Repository.RoomRepository;

import static com.example.jdbc.project_JPA.constants.ERROR_Message.*;
import static com.example.jdbc.project_JPA.constants.Response_DELETE_Msg.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class RoomService {
	
	@Autowired
	private RoomRepository roomrepo;
	
	public HotelRoom createNewRoom(HotelRoom room) {
		HotelRoom newRoom = null;
		HotelRoom existingroom= null;
		System.out.println("In service class ------- " + room);
		try {
		      existingroom=roomrepo.findById(room.getRoomno()).get();
		}catch(NoSuchElementException nsee) {
			System.out.println("In service class - catch block  ------- ");
			newRoom = roomrepo.save(room);
		}
		
		if(null != existingroom) {
			System.out.println("In service class ------- existingroom ---- " + existingroom);
			 throw new RoomAlreadyExistsException( room.getRoomno() + " " + ROOM_ALREADY_EXISTS);
		}

		return newRoom;
	}
	
	public HotelRoom FetchOneRoom (String roomno)
	{
		HotelRoom OneRoom;
		try
		{
			OneRoom =roomrepo.findById(roomno).get();
		}
		catch(NoSuchElementException nsee) 
		{
			throw new RoomNotFoundException(roomno + " " + ROOM_NOT_FOUND);
		}
	
    if(OneRoom==null)
    {
         System.out.println(OneRoom);
    
	}
    return OneRoom;
	}

	public HotelRoom UpdateRoom (HotelRoom room)
	{
		HotelRoom ModifyRoom = null;
		HotelRoom existingroom = null;
		String roomno = room.getRoomno();
		try
		{
		 existingroom = roomrepo.findById(room.getRoomno()).get();
		}
		catch(NoSuchElementException nsee)
		{
			throw new RoomNotFoundException(roomno + " " + ROOM_NOT_FOUND);
		}
		
		if(existingroom != null)
		{
			 ModifyRoom = roomrepo.save(room);
		}
		
		return ModifyRoom;
	}
	
	public HotelRoom DeleteRoom (String roomNo)
	{
		
		HotelRoom existingroom = null;
		try
		{
		  existingroom = roomrepo.findById(roomNo).get();
		}
		catch(NoSuchElementException nsee)
		{
			throw new RoomNotFoundException(roomNo + " " + ROOM_NOT_FOUND);
		}
		
		if(existingroom != null)
		{
			roomrepo.deleteById(roomNo);
		}
	
		return existingroom;
	}

	public List<HotelRoom> allRooms() 
	{
		List<HotelRoom>roomList=roomrepo.findAll();
		return roomList;
	}
}
