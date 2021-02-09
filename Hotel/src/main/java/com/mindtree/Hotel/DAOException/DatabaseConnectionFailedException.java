package com.mindtree.Hotel.DAOException;

import com.mindtree.HotelException.HotelException;

public class DatabaseConnectionFailedException extends HotelException
{

	public DatabaseConnectionFailedException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DatabaseConnectionFailedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DatabaseConnectionFailedException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DatabaseConnectionFailedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DatabaseConnectionFailedException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
