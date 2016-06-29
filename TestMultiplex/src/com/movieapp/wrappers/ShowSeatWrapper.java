package com.movieapp.wrappers;

import java.util.List;
import java.util.Map;

import com.movieapp.beans.ShowSeat;

public class ShowSeatWrapper {
	private ShowSeat showseat;
	private List<ShowSeat> showseats;
	
	//Others
	private List<Map<String, String>> errors;
	
	public ShowSeat getShowseat() {
		return showseat;
	}
	public void setShowseat(ShowSeat showseat) {
		this.showseat = showseat;
	}
	public List<ShowSeat> getShowseats() {
		return showseats;
	}
	public void setShowseats(List<ShowSeat> showseats) {
		this.showseats = showseats;
	}
		
	
	public List<Map<String, String>> getErrors() {
		return errors;
	}
	public void setErrors(List<Map<String, String>> errors) {
		this.errors = errors;
	}
	@Override
	public String toString() {
		return "ShowSeatWrapper [showseat=" + showseat + ", showseats=" + showseats + "]";
	}
	
	
}
