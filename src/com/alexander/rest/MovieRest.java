package com.alexander.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.alexander.beans.MovieDTO;
import com.alexander.services.MovieService;

@Path("/movies")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class MovieRest {
	MovieService service = new MovieService();

	@GET
	public Response getMovies() {
		List<MovieDTO> movies = service.getMovies();

		return Response.status(Response.Status.OK).entity(movies).build();
	}

	@GET
	@Path("/{id}")
	public Response getMovieById(@PathParam("id") int id) {
		MovieDTO movie = service.getMovieById(id);

		if (movie == null) {
			return Response.status(Response.Status.NOT_FOUND).entity(String.format("La película con id %s no existe", id))
					.build();
		} else {
			return Response.status(Response.Status.OK).entity(movie).build();	
		}
	}

	@POST
	public Response registerMovie(MovieDTO movie) {
		int affectedRows = service.registerMovie(movie);

		if (affectedRows == -1) {
			return Response.status(Response.Status.BAD_REQUEST).entity("No se pudo crear la película").build();
		} else {
			return Response.status(Response.Status.CREATED).entity(affectedRows).build();
		}
	}

	@PUT
	@Path("/{id}")
	public Response updateMovie(@PathParam("id") int id, MovieDTO movie) {
		movie.setId(id);
		int affectedRows = service.updateMovie(movie);

		if (affectedRows == -1) {
			return Response.status(Response.Status.BAD_REQUEST).entity(String.format("No se pudo actualizar la película con id %s", movie.getId())).build();
		} else {
			return Response.status(Response.Status.OK).entity(affectedRows).build();
		}
	}

	@DELETE
	@Path("/{id}")
	public Response deleteMovie(@PathParam("id") int id) {
		int affectedRows = service.deleteMovie(id);

		if(affectedRows == -1) {
			return Response.status(Response.Status.BAD_REQUEST).entity(String.format("No se pudo eliminar la película con id %s", id)).build();	
		} else {
			return Response.status(Response.Status.OK).entity(affectedRows).build();
		}
	}

}
