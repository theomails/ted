package com.movieapp.wrappers;

import java.util.List;

import com.movieapp.beans.Movie;

public class MovieWrapper {
	private Movie movie;
	private List<Movie> movies;
	
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public List<Movie> getMovies() {
		return movies;
	}
	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
	@Override
	public String toString() {
		return "MovieWrapper [movie=" + movie + ", movies=" + movies + "]";
	}
	
	
}
