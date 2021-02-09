package com.mindtree.Entity;

public class Room 
{
	private int roomNumber;
	private String type;
	private double cost;
	
	private Hotel hotel;

	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Room(int roomNumber, String type, double cost, Hotel hotel) {
		super();
		this.roomNumber = roomNumber;
		this.type = type;
		this.cost = cost;
		this.hotel = hotel;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	
}
