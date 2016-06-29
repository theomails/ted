package com.movieapp.wrappers;

import java.util.List;
import java.util.Map;

import com.movieapp.beans.Category;
import com.movieapp.beans.Seat;

public class ScreenWrapper {
	private ScreenInclSeats screen;
	private List<ScreenInclSeats> screens;
	
	//Others
	private List<Seat> seats;
	private List<Category> categories;
	
	private List<Map<String, String>> errors;
	
	public ScreenInclSeats getScreen() {
		return screen;
	}
	public void setScreen(ScreenInclSeats screen) {
		this.screen = screen;
	}
	public List<ScreenInclSeats> getScreens() {
		return screens;
	}
	public void setScreens(List<ScreenInclSeats> screens) {
		this.screens = screens;
	}
	
	
	
	public List<Seat> getSeats() {
		return seats;
	}
	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}
	
	
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	
	
	public List<Map<String, String>> getErrors() {
		return errors;
	}
	public void setErrors(List<Map<String, String>> errors) {
		this.errors = errors;
	}
	@Override
	public String toString() {
		return "ScreenWrapper [screen=" + screen + ", screens=" + screens + ", seats=" + seats + "]";
	}
	
	
}
