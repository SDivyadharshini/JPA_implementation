package com.example.jdbc.project_JPA.Bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "room")
public class HotelRoom {
	
	@Id
	private String roomno;
	private String roomtype;
	private String bed;
	private int price;
	private String status;
	
	public HotelRoom()
	{
		
	}

	public HotelRoom(String roomno, String roomtype, String bed, int price, String status) {
		super();
		this.roomno = roomno;
		this.roomtype = roomtype;
		this.bed = bed;
		this.price = price;
		this.status = status;
	}

	public String getRoomno() {
		return roomno;
	}

	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}

	public String getRoomtype() {
		return roomtype;
	}

	public void setRoomtype(String roomtype) {
		this.roomtype = roomtype;
	}

	public String getBed() {
		return bed;
	}

	public void setBed(String bed) {
		this.bed = bed;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return String.format("HotelRoom [roomno=%s, roomtype=%s, bed=%s, price=%s, status=%s]", roomno, roomtype, bed,
				price, status);
	}

	
	
	
	

}
