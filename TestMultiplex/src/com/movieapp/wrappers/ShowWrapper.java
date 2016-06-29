package com.movieapp.wrappers;

import java.util.List;
import java.util.Map;

import com.movieapp.beans.Show;

public class ShowWrapper {
	private Show show;
	private List<Show> shows;
	
	//Others
	private List<Map<String, String>> errors;
	
	public Show getShow() {
		return show;
	}
	public void setShow(Show show) {
		this.show = show;
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
		return "ShowWrapper [show=" + show + ", shows=" + shows + "]";
	}
	
	
}
