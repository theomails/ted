package com.movieapp.wrappers;

import java.util.List;
import java.util.Map;

import com.movieapp.beans.Category;
import com.movieapp.beans.Seat;

public class SeatWrapper {
	private Seat seat;
	private List<Seat> seats;
	
	//Others
	private List<Category> categories;
	
	private List<Map<String, String>> errors;
	
	public Seat getSeat() {
		return seat;
	}
	public void setSeat(Seat seat) {
		this.seat = seat;
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
		return "SeatWrapper [seat=" + seat + ", seats=" + seats + "]";
	}
	
	
}
