package com.example.jdbc.project_JPA;

import static com.example.jdbc.project_JPA.constants.ERROR_Message.ROOM_ALREADY_EXISTS;
import static com.example.jdbc.project_JPA.constants.ERROR_Message.ROOM_NOT_FOUND;
import static com.example.jdbc.project_JPA.constants.Response_DELETE_Msg.*;
import static com.example.jdbc.project_JPA.constants.URI_Request.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.jdbc.project_JPA.ProjectSpringJdbcApplication;
import com.example.jdbc.project_JPA.Bean.HotelRoom;
import com.example.jdbc.project_JPA.Exception.ExceptionResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



@SpringBootTest(classes = ProjectSpringJdbcApplication.class)
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@TestMethodOrder(OrderAnnotation.class)

public class Room_Controller_Test {
	
protected MockMvc mockMvc;
private static HotelRoom globalRoom;
protected void setUp() {
	mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
 }
	
	@Autowired
	   WebApplicationContext webApplicationContext;
	
	@Test
	@Order(1)
	public void retrieveAllRooms() throws Exception{
		
		setUp();
		String uri = ALL_ROOMS;
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		int status = result.getResponse().getStatus();
		assertEquals(200, status);

		ObjectMapper mapper = new ObjectMapper();
		List<HotelRoom> actual = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<HotelRoom>>() {});
		
		System.out.println("******** list from response ******** \n" + actual);
		assertTrue(actual.size() > 0);
	

	}
	
	
	@Test
	@Order(2)
    public void createRoom() throws Exception {
		
		setUp();
       String uri = CREATE_ROOM;
       HotelRoom room=new HotelRoom();
       room.setRoomno("140");
       room.setRoomtype("AC");
       room.setBed("single");
       room.setPrice(800);
       room.setStatus("Not Booked");
       ObjectMapper mapper = new ObjectMapper();
       String input=mapper.writeValueAsString(room);
       System.out.println("************INPUT"+input);
       RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(input);
       MvcResult result = mockMvc.perform(requestBuilder).andReturn();
       System.out.println("RESULT:" + result.getResponse().getContentAsString());
       HotelRoom actual = mapper.readValue(result.getResponse().getContentAsString(), HotelRoom.class);
       System.out.println("ACTUAL" + actual);
       globalRoom = actual;
       System.out.println("GLOBAL ROOM ----------------------------> " + globalRoom);
       System.out.println("room" +room);
       int status = result.getResponse().getStatus();
       System.out.println("Status" +status);
       assertEquals(201, status);
       String content = result.getResponse().getContentAsString();
       System.out.println("content" +content);
       assertEquals(actual.getRoomno(),room.getRoomno());
       
    }
	
	@Test
	@Order(3)
	public void roomAlreadyExists() throws Exception {
		
		setUp();
	       String uri = CREATE_ROOM;
           ObjectMapper mapper = new ObjectMapper();
	       String input=mapper.writeValueAsString(globalRoom);
	       RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(input);
	       MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	       int status = result.getResponse().getStatus();
	       assertEquals(400, status);
	       ExceptionResponse actual = mapper.readValue(result.getResponse().getContentAsString(), ExceptionResponse.class);
	       assertNotNull(actual);
	       String expected = globalRoom.getRoomno() + " " + ROOM_ALREADY_EXISTS;
	       assertEquals(actual.getMessage(), expected);
	}

	
	
	@Test
	@Order(4)
    public void retrieveByRoomNo() throws Exception {
		
		setUp();
    String uri = HOTEL_ROOMS + globalRoom.getRoomno();
    RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE);
    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    int status = result.getResponse().getStatus();
    assertEquals(200, status);
    ObjectMapper mapper = new ObjectMapper();
    HotelRoom actual = mapper.readValue(result.getResponse().getContentAsString(), HotelRoom.class);
    String content = result.getResponse().getContentAsString();
    System.out.println("Content***************" +content);
    System.out.println("actual.getRoomNo()" +actual.getRoomno());
    assertEquals(actual.getRoomno(),globalRoom.getRoomno());
	}
	
	
	@Test
	@Order(5)
	public void updateRoom() throws Exception {
	   globalRoom.setRoomtype("Non AC");
	   globalRoom.setPrice(200);
	   setUp();
	   String uri = ALL_ROOMS;
       ObjectMapper mapper = new ObjectMapper();
       String input=mapper.writeValueAsString(globalRoom);
       RequestBuilder requestBuilder = MockMvcRequestBuilders.put(uri).accept(MediaType.APPLICATION_JSON_VALUE).content(input).contentType(MediaType.APPLICATION_JSON_VALUE);
	   MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	   int status = result.getResponse().getStatus();
	   assertEquals(200, status);
	   HotelRoom actual = mapper.readValue(result.getResponse().getContentAsString(), HotelRoom.class);
	   assertEquals(actual.getRoomtype(),globalRoom.getRoomtype());
	   assertEquals(actual.getPrice(),globalRoom.getPrice());
	}
	
	
	@Test
	@Order(6)
    public void deleteRoom() throws Exception {
		setUp();
       String uri = HOTEL_ROOMS + globalRoom.getRoomno();
       ObjectMapper mapper = new ObjectMapper();
       String input=mapper.writeValueAsString(globalRoom);
       MvcResult mvcResult =   mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
       int status = mvcResult.getResponse().getStatus();
       assertEquals(200, status);
       HotelRoom actual = mapper.readValue(mvcResult.getResponse().getContentAsString(), HotelRoom.class);
       assertEquals(actual.getRoomno(), globalRoom.getRoomno());
    }
	
	
	@Test
	@Order(7)
	public void updateNotExistingRoom() throws Exception {
		
		setUp();
	       String uri = ALL_ROOMS;
	       globalRoom.setRoomtype("Non AC");
		   globalRoom.setPrice(200);
           ObjectMapper mapper = new ObjectMapper();
	       String input=mapper.writeValueAsString(globalRoom);
	       RequestBuilder requestBuilder = MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(input);
	       MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	       int status = result.getResponse().getStatus();
	       assertEquals(404, status);
	       ExceptionResponse actual = mapper.readValue(result.getResponse().getContentAsString(), ExceptionResponse.class);
	       assertNotNull(actual);
	       String expected = globalRoom.getRoomno() + " " + ROOM_NOT_FOUND;
	       assertEquals(actual.getMessage(), expected);
		
	}
	
	
	@Test
	@Order(8)
    public void retrieveNotExistingRoom() throws Exception {
		
		setUp();
    String uri = HOTEL_ROOMS + globalRoom.getRoomno();
    RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE);
    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    int status = result.getResponse().getStatus();
    assertEquals(404, status);
    ObjectMapper mapper = new ObjectMapper();
    ExceptionResponse actual = mapper.readValue(result.getResponse().getContentAsString(), ExceptionResponse.class);
    assertNotNull(actual);
    String expected = globalRoom.getRoomno() + " " + ROOM_NOT_FOUND;
    assertEquals(actual.getMessage(), expected);
	}
	
	@Test
	@Order(9)
    public void deleteNotExistingRoom() throws Exception {
		
		setUp();
       String uri = HOTEL_ROOMS + globalRoom.getRoomno();
       RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(uri).accept(MediaType.APPLICATION_JSON_VALUE);
       MvcResult result = mockMvc.perform(requestBuilder).andReturn();
       int status = result.getResponse().getStatus();
       assertEquals(404, status);
       ObjectMapper mapper = new ObjectMapper();
       ExceptionResponse actual = mapper.readValue(result.getResponse().getContentAsString(), ExceptionResponse.class);
       assertNotNull(actual);
       String expected = globalRoom.getRoomno() + " " +  ROOM_NOT_FOUND;
       assertEquals(actual.getMessage(), expected);
      
    }
	
}
