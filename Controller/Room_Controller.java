package com.example.jdbc.project_JPA.Controller;

import static com.example.jdbc.project_JPA.constants.URI_Request.*;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jdbc.project_JPA.Bean.HotelRoom;
import com.example.jdbc.project_JPA.Repository.RoomRepository;
import com.example.jdbc.project_JPA.service.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class Room_Controller {
	
	@Autowired
	private RoomRepository roomrepo;
	
	@Autowired
	private RoomService roomService;
	
//	public Room_Controller(RoomDAO connectDAO)
//	{
//		this.connectDAO=connectDAO;
//	}
//	@GetMapping(ALL_ROOMS)
//	public List<HotelRoom> list()
//	{
//		return connectDAO.allRooms();
//	}
	@GetMapping(ALL_ROOMS)
	public List<HotelRoom> list()
    {
		return roomService.allRooms();
	}
	
	@GetMapping(PARTICULAR_ROOM)
    public HotelRoom getByNo(@PathVariable String roomNo)
    {
		
     return roomService.FetchOneRoom(roomNo);
    }
    
	
	@PostMapping(CREATE_ROOM)
    public ResponseEntity addRoom(@RequestBody HotelRoom room)
    {
		
		//System.out.println("In post------- " + room);
		HotelRoom newRoom = roomService.createNewRoom(room);
		
		//System.out.println("In post... created new room... " + newRoom);
      return  new ResponseEntity<>(newRoom,HttpStatus.CREATED);
		
        
    }
	
	@PutMapping(UPDATE_ROOM)
    public ResponseEntity updateRoom(@RequestBody HotelRoom room)
    {
		System.out.println("Enter PUT Mapping");
        HotelRoom upRoom = roomService.UpdateRoom(room);
        
        return new ResponseEntity<>(upRoom,HttpStatus.OK);
    }
	
	@DeleteMapping(DELETE_ROOM)
    public HotelRoom deleteRoom(@PathVariable String roomNo){
		HotelRoom delRoom = roomService.DeleteRoom(roomNo);
		
		return delRoom;
    }

}
