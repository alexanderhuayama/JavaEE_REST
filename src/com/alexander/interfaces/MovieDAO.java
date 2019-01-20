package com.alexander.interfaces;

import java.util.List;

import com.alexander.beans.MovieDTO;

public interface MovieDAO {
	public List<MovieDTO> getMovies();

	public MovieDTO getMovieById(int id);

	public int registerMovie(MovieDTO movie);

	public int updateMovie(MovieDTO movie);

	public int deleteMovie(int id);

}
