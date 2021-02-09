package com.mindtree.Hotel.Service.Impl;

import java.util.List;

import com.mindtree.Entity.Hotel;
import com.mindtree.Entity.Room;
import com.mindtree.Hotel.DAO.Impl.HotelDAOImpl;
import com.mindtree.Hotel.DAOException.HotelDAOException;
import com.mindtree.Hotel.DAOException.HotelIDPresentException;
import com.mindtree.HotelDAO.HotelDAO;
import com.mindtree.HotelService.HotelService;
import com.mindtree.HotelServiceException.HotelServiceException;

public class HotelServiceImpl implements HotelService
{

	HotelDAO dao=new HotelDAOImpl();
	
	
	public boolean insertHotelToDb(Hotel hotel) throws HotelServiceException
	{
		try
		{
			return dao.insertHotelToDb(hotel);
		}catch(HotelDAOException e)
		{
			throw new HotelServiceException("Something is wrong with the Database");
		}
		
	}

	public boolean insertRoomToDb(Room room) throws HotelServiceException {
		
		try
		{
			return dao.insertRoomToDb(room);
		}catch(HotelDAOException e)
		{
			throw new HotelServiceException("Something is wrong with the Database");
		}
	}

	
	public List<Hotel> getAllHotelsInCity(String city) throws HotelServiceException
	{
		try
		{
			return dao.getAllHotelsInCity(city);
		}catch(HotelDAOException e)
		{
			throw new HotelServiceException("Something is wrong with the Database");
		}
	}

	public boolean checkHotelId(int hotelId) throws HotelIDPresentException
	{
		try 
		{
			return dao.checkHotelId(hotelId);
		}
		catch(HotelIDPresentException e)
		{
			throw new HotelIDPresentException("Id Already Present in the Database");
		}
	}


	
	
	


}
