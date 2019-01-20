package com.alexander.dao;

import com.alexander.interfaces.MovieDAO;

public class MySQLDAOFactory extends DAOFactory {

	@Override
	public MovieDAO getMovieDAO() {
		return new MovieMySQLDAO();
	}

}
