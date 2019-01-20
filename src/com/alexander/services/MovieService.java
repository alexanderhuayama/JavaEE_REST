package com.alexander.services;

import java.util.List;

import com.alexander.beans.MovieDTO;
import com.alexander.dao.DAOFactory;
import com.alexander.interfaces.MovieDAO;

public class MovieService {
	private DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
	private MovieDAO dao = factory.getMovieDAO();

	public List<MovieDTO> getMovies() {
		return dao.getMovies();
	}

	public MovieDTO getMovieById(int id) {
		return dao.getMovieById(id);
	}

	public int registerMovie(MovieDTO movie) {
		return dao.registerMovie(movie);
	}

	public int updateMovie(MovieDTO movie) {
		return dao.updateMovie(movie);
	}

	public int deleteMovie(int id) {
		return dao.deleteMovie(id);
	}
}
