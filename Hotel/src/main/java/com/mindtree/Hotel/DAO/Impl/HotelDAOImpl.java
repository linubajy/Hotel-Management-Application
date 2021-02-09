package com.mindtree.Hotel.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mindtree.Entity.Hotel;
import com.mindtree.Entity.Room;
import com.mindtree.Hotel.DAOException.DatabaseConnectionFailedException;
import com.mindtree.Hotel.DAOException.HotelDAOException;
import com.mindtree.Hotel.DAOException.HotelIDPresentException;
import com.mindtree.HotelDAO.HotelDAO;
import com.mindtree.HotelException.HotelException;
import com.mindtree.Utility.JDBCConnection;


public class HotelDAOImpl implements HotelDAO 
{
	public static JDBCConnection connection = new JDBCConnection();
	
	public boolean insertHotelToDb(Hotel hotel) throws HotelDAOException 
	{
		boolean isInserted = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = connection.getConnection();
			
			String insertQuery = "Insert into Hotel values(?,?,?)";
			ps = con.prepareStatement(insertQuery);

			ps.setInt(1, hotel.getHotelId());
			ps.setString(2, hotel.getHotelName());
			ps.setString(3, hotel.getCity());
		

			ps.executeUpdate();
			isInserted = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			connection.closeResources(con);
			connection.closeResources(ps);
		}
		
		return isInserted;
		
	}

	public boolean insertRoomToDb(Room room) throws HotelDAOException 
	{
		boolean isInserted = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = connection.getConnection();
			
			String insertQuery = "Insert into Room values(?,?,?,?)";
			ps = con.prepareStatement(insertQuery);

			ps.setInt(1, room.getRoomNumber());
			ps.setString(2, room.getType());
			ps.setDouble(3, room.getCost());
			ps.setInt(4, room.getHotel().getHotelId());
		

			ps.executeUpdate();
			isInserted = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			connection.closeResources(con);
			connection.closeResources(ps);
		}
		
		return isInserted;
	}

	public List<Hotel> hotelsInCity(String city) throws HotelDAOException 
	{
		List<Hotel> hotels = new ArrayList<Hotel>();
		String cityName = "";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try 
		{
			checkCityAbsence(city);
			try 
			{

				String query = "Select city from Hotel where city='" + city + "'";
				ps = con.prepareStatement(query);

				resultSet = ps.executeQuery();
				while (resultSet.next())
				{
					cityName = resultSet.getString(1);
					if (cityName.equals(city)) 
					{
						hotels=getAllHotelsInCity(city);

					}
				}
			}
			catch(SQLException e)
			{
				System.out.println(e.getMessage());
			}
		}catch(HotelException e)
		{
			System.out.println(e.getMessage());
			
		} finally {
			connection.closeResources(con);
			connection.closeResources(ps);
			connection.closeResources(resultSet);
		}
		return hotels;

	}

	public List<Hotel> getAllHotelsInCity(String city) throws HotelDAOException 
	{
		List<Hotel> hotels = new ArrayList<Hotel>();

		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		String query = "Select * from Hotel where city='" + city + "'";

		try 
		{
			checkCityAbsence(city);
			
			try
			{
		
				con = connection.getConnection();
				st = con.createStatement();
				rs = st.executeQuery(query);
				while (rs.next())	
				{
				Hotel hotel = new Hotel(rs.getInt(1), rs.getString(2), rs.getString(3));
				hotels.add(hotel);
				}
			}
			catch (HotelException e) 
			{
				throw new HotelDAOException();
			}	
		
		} 
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
			
		finally 
		{
			connection.closeResources(con);
			connection.closeResources(st);
			connection.closeResources(rs);
		}
		
		return hotels;
	}

	
	
	public void checkCityAbsence(String city) throws HotelDAOException {
		String cityName = "";
		boolean valid = false;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			con = connection.getConnection();
			String query = "Select city from Hotel Where City='" + city + "'";
			ps = con.prepareStatement(query);

			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				cityName = resultSet.getString(1);
				if (cityName.equals(city)) {
					valid = true;
					break;

				}
			}

			if (valid == false) {
				throw new HotelDAOException("No hotels in selected city!");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} finally {
			connection.closeResources(con);
			connection.closeResources(ps);
			connection.closeResources(resultSet);
		}

	}

	public boolean checkHotelId(int hotelId) throws HotelIDPresentException 
	{
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		boolean valid = false;
		try {

			con = connection.getConnection();
			String query = "Select hotelId from Hotel Where hotelId=" + hotelId;
			ps = con.prepareStatement(query);

			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
				if (count == hotelId) {
					valid = true;
					break;
				}
			}

			if (valid == true) {
				throw new HotelIDPresentException("Already Present");
			}

			if (ps != null) {
				ps.close();
			}

		} catch (Exception e) {
			throw new HotelIDPresentException("Already Present");
		} finally {
			connection.closeResources(con);
			connection.closeResources(ps);
		}
		return valid;
		
	}
	

}
