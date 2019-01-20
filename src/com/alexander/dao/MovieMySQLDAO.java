package com.alexander.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alexander.beans.MovieDTO;
import com.alexander.interfaces.MovieDAO;
import com.alexander.utils.MySQLConnection;

public class MovieMySQLDAO implements MovieDAO {

	@Override
	public List<MovieDTO> getMovies() {
		List<MovieDTO> movies = new ArrayList<MovieDTO>();
		MovieDTO movie = null;
		Connection cn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		String sql = "CALL sp_list_movies()";

		try {
			cn = MySQLConnection.getConnection();
			cs = cn.prepareCall(sql);
			rs = cs.executeQuery();

			while (rs.next()) {
				movie = new MovieDTO();
				movie.setId(rs.getInt("id"));
				movie.setName(rs.getString("name"));
				movie.setDescription(rs.getString("description"));
				movie.setPicture(rs.getString("picture"));
				movie.setUrl(rs.getString("url"));
				movie.setRegisterDate(rs.getString("register_date"));
				movie.setUpdateDate(rs.getString("update_date"));

				movies.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLConnection.closeConnection(rs, cs, cn);
		}

		return movies;
	}

	@Override
	public MovieDTO getMovieById(int id) {
		MovieDTO movie = null;
		Connection cn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		String sql = "CALL sp_movie_by_id(?)";

		try {
			cn = MySQLConnection.getConnection();
			cs = cn.prepareCall(sql);
			cs.setInt(1, id);
			rs = cs.executeQuery();

			if (rs.next()) {
				movie = new MovieDTO();
				movie.setId(rs.getInt("id"));
				movie.setName(rs.getString("name"));
				movie.setDescription(rs.getString("description"));
				movie.setPicture(rs.getString("picture"));
				movie.setUrl(rs.getString("url"));
				movie.setRegisterDate(rs.getString("register_date"));
				movie.setUpdateDate(rs.getString("update_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLConnection.closeConnection(rs, cs, cn);
		}

		return movie;
	}

	@Override
	public int registerMovie(MovieDTO movie) {
		int affectedRows = -1;
		Connection cn = null;
		CallableStatement cs = null;
		String sql = "CALL sp_register_movie(?,?,?,?)";

		try {
			cn = MySQLConnection.getConnection();
			cs = cn.prepareCall(sql);
			cs.setString(1, movie.getName());
			cs.setString(2, movie.getDescription());
			cs.setString(3, movie.getPicture());
			cs.setString(4, movie.getUrl());

			affectedRows = cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLConnection.closeConnection(null, cs, cn);
		}

		return affectedRows;
	}

	@Override
	public int updateMovie(MovieDTO movie) {
		int affectedRows = -1;
		Connection cn = null;
		CallableStatement cs = null;
		String sql = "CALL sp_update_movie(?,?,?,?,?)";

		try {
			cn = MySQLConnection.getConnection();
			cs = cn.prepareCall(sql);
			cs.setInt(1, movie.getId());
			cs.setString(2, movie.getName());
			cs.setString(3, movie.getDescription());
			cs.setString(4, movie.getPicture());
			cs.setString(5, movie.getUrl());

			affectedRows = cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLConnection.closeConnection(null, cs, cn);
		}

		return affectedRows;
	}

	@Override
	public int deleteMovie(int id) {
		int affectedRows = -1;
		Connection cn = null;
		CallableStatement cs = null;
		String sql = "CALL sp_delete_movie(?)";

		try {
			cn = MySQLConnection.getConnection();
			cs = cn.prepareCall(sql);
			cs.setInt(1, id);

			affectedRows = cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLConnection.closeConnection(null, cs, cn);
		}

		return affectedRows;
	}

}
