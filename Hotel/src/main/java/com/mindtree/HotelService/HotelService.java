package com.mindtree.HotelService;

import java.util.List;

import com.mindtree.Entity.Hotel;
import com.mindtree.Entity.Room;
import com.mindtree.Hotel.DAOException.HotelDAOException;
import com.mindtree.Hotel.DAOException.HotelIDPresentException;
import com.mindtree.HotelServiceException.HotelServiceException;

public interface HotelService 
{

	boolean insertHotelToDb(Hotel hotel) throws HotelServiceException;
	boolean insertRoomToDb(Room room)throws HotelServiceException;
	
	List<Hotel> getAllHotelsInCity(String city) throws HotelServiceException;
	
	boolean checkHotelId(int hotelId) throws HotelIDPresentException;
	
	

}
