package com.mindtree.Hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mindtree.Entity.Hotel;
import com.mindtree.Entity.Room;
import com.mindtree.Hotel.DAOException.HotelIDPresentException;
import com.mindtree.Hotel.Service.Impl.HotelServiceImpl;
import com.mindtree.HotelService.HotelService;
import com.mindtree.HotelServiceException.HotelServiceException;

public class HotelManagementApp {

	private static HotelService hotelService = new HotelServiceImpl(); // interface obj
	static Scanner sc = new Scanner(System.in);

	public static void displayMessage() {
		System.out.println("HOTEL MANAGEMENT \n**************************");
		System.out.println("1.CREATE HOTEL ");
		System.out.println("2.CREATE ROOM");
		System.out.println("3.DISPLAY HOTEL BASED ON CITY");
		System.out.println("4.EXIT");

	}

	public static void main(String[] args) {
		int flag = 0;
		int ch = 0;
		Hotel hotel = null;

		do {
			displayMessage();
			ch = sc.nextInt();
			switch (ch) {
			case 1:
				hotel = createHotels();
				if (hotel != null) {
					boolean isInserted = false;
					try {
						isInserted = hotelService.insertHotelToDb(hotel);
					} catch (HotelServiceException e) {
						System.out.println(e.getMessage());
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					if (isInserted == true) {
						System.out.println("Inserted Successfully");
					}
				} else
					continue;
				break;

			case 2:
				Room room = createRooms(hotel);
				if (room != null) {
					boolean isInserted = false;
					try {
						isInserted = hotelService.insertRoomToDb(room);
					} catch (HotelServiceException e) {
						System.out.println(e.getMessage());
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					if (isInserted == true) {
						System.out.println("Inserted Successfully");
					}
				} else
					continue;
				break;

			case 3:
				System.out.println("Enter the city name: ");
				String city = sc.next();
				try {
					List<Hotel> hotels = hotelService.getAllHotelsInCity(city);
					displayHotelsFromDB(hotels, city);
				} catch (HotelServiceException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;

			case 4:
				flag = 1;
				break;

			}

		} while (ch != 4);

	}

	private static Hotel createHotels() {
		boolean pre = true;
		HotelServiceImpl obj = new HotelServiceImpl();
		System.out.println("Enter the hotel id:");
		int hotelId = sc.nextInt();
		Hotel hotel = null;
		try {
			pre = obj.checkHotelId(hotelId);
			if (pre == false) {
				System.out.println("Enter the hotel name:");
				String hotelName = sc.next();
				System.out.println("Enter the hotel city:");
				String city = sc.next();

				hotel = new Hotel(hotelId, hotelName, city);
			}

		} catch (HotelIDPresentException e) {
			System.out.println(e.getMessage());
		} 
		return hotel;

	}

	private static Room createRooms(Hotel hotel) {

		System.out.println("Enter Room  Number: ");
		int roomNumber = sc.nextInt();
		System.out.println("Enter Room Type(Luxury/semiLuxury/deluxe): ");
		String roomType = sc.next();
		roomType = validateRoomType(roomType);
		System.out.println("Enter Room Cost");
		double roomCost = sc.nextDouble();
		hotel = createHotelForRoom(); // creating a hotel for the room-aggregation
		Room room = new Room(roomNumber, roomType, roomCost, hotel);

		return room;
	}

	private static Hotel createHotelForRoom() {
		HotelServiceImpl obj = new HotelServiceImpl();
		int index = 0;
		System.out.println("Enter Hotel  id: ");
		int id = sc.nextInt();
		System.out.println("Enter Hotel Name: ");
		String hotelName = sc.next();
		System.out.println("Enter Hotel City");
		String hotelCity = sc.next();

		Hotel hotels = new Hotel(id, hotelName, hotelCity);

		return hotels;

	}

	private static String validateRoomType(String roomType) {
		boolean isValid = true;
		while (isValid) {
			if (roomType.equalsIgnoreCase("Luxury") || roomType.equalsIgnoreCase("semiLuxury")
					|| roomType.equalsIgnoreCase("deluxe")) {
				isValid = false;
			} else {
				System.out.println("Enter the Room Type Propely(Luxury/semiLuxury/deluxe)");
				roomType = sc.next();
			}
		}

		return roomType;

	}

	//option 3
	public static void displayHotelsFromDB(List<Hotel> hotels, String city) {
		if (hotels != null) {
			System.out.println("Hotels in " + city + " are: ");
			for (Hotel x : hotels) {
				System.out.println(x.getHotelId() + "\t" + x.getHotelName() + "\t" + x.getCity());
			}
		}

	}

}
