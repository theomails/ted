package com.movieapp.wrappers;

import java.util.List;
import java.util.Map;

import com.movieapp.beans.Movie;
import com.movieapp.beans.MovieShow;
import com.movieapp.beans.Screen;
import com.movieapp.beans.Show;

public class MovieShowWrapper {
	private MovieShow movieshow;
	private List<MovieShow> movieshows;
	
	//Others
	private List<Screen> screens;
	private List<Movie> movies;
	private List<Show> shows;
	
	private List<Map<String, String>> errors;
	
	public MovieShow getMovieshow() {
		return movieshow;
	}
	public void setMovieshow(MovieShow movieshow) {
		this.movieshow = movieshow;
	}
	public List<MovieShow> getMovieshows() {
		return movieshows;
	}
	public void setMovieshows(List<MovieShow> movieshows) {
		this.movieshows = movieshows;
	}
		
	
	public List<Screen> getScreens() {
		return screens;
	}
	public void setScreens(List<Screen> screens) {
		this.screens = screens;
	}
	public List<Movie> getMovies() {
		return movies;
	}
	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
	public List<Show> getShows() {
		return shows;
	}
	public void setShows(List<Show> shows) {
		this.shows = shows;
	}
	
	public List<Map<String, String>> getErrors() {
		return errors;
	}
	public void setErrors(List<Map<String, String>> errors) {
		this.errors = errors;
	}
	
	@Override
	public String toString() {
		return "MovieShowWrapper [movieshow=" + movieshow + ", movieshows=" + movieshows + "]";
	}
	
	
}
