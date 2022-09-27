package com.example.jdbc.project_JPA;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.example.jdbc.project_JPA.Bean.HotelRoom;
import com.example.jdbc.project_JPA.service.RoomService;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class ProjectSpringJdbcApplication extends SpringBootServletInitializer {
//	private static DAO<HotelRoom> dao;
//    public  ProjectSpringJdbcApplication(DAO<HotelRoom> dao)
//    {
//        this.dao=dao;
//    }
    @Autowired
    private static RoomService roomservice;
    public  ProjectSpringJdbcApplication(RoomService roomservice)
    {
    	this.roomservice=roomservice;
    }
   
    public static void main(String[] args) {
        SpringApplication.run(ProjectSpringJdbcApplication.class, args);

        System.out.println("\n Room details -------\n");
        List<HotelRoom> room=roomservice.allRooms();
        room.forEach(System.out::println);
        
//        System.out.println("\n Fetching specific Room details -------\n");
//        HotelRoom room_n=dao.getByNo("111");
//        System.out.println(room_n);
//        
//        System.out.println("\n   Creating Room ----\n");
//        HotelRoom ob=new HotelRoom("130","AC","Double",1500,"Not Booked");
//        System.out.println(dao.saveroom(ob));*/
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
    {
        return builder.sources(ProjectSpringJdbcApplication.class);
    }

	

}
