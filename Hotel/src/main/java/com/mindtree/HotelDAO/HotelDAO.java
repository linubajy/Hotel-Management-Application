package com.mindtree.HotelDAO;

import java.util.List;

import com.mindtree.Entity.Hotel;
import com.mindtree.Entity.Room;
import com.mindtree.Hotel.DAOException.HotelDAOException;
import com.mindtree.Hotel.DAOException.HotelIDPresentException;


public interface HotelDAO 
{
	boolean insertHotelToDb(Hotel hotel) throws HotelDAOException;
	boolean insertRoomToDb(Room room)throws HotelDAOException;
	List<Hotel> getAllHotelsInCity(String city) throws HotelDAOException;
	boolean checkHotelId(int hotelId) throws HotelIDPresentException;

}
