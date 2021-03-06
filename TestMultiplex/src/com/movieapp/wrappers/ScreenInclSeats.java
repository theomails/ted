package com.movieapp.wrappers;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
public class ScreenInclSeats {

	
	  private Long id;
	  private String screenName;
	  private int screenRows;
	  private int screenColumns;
	  private List<Long> seats;
	  
	  
		  
	public ScreenInclSeats()
	{
		super();
	}
	  
	public ScreenInclSeats(Long id,String screenName,int rows,int cols, List<Long> seats)
	{
		this.screenName=screenName;
		this.screenRows=rows;
		this.screenColumns=cols;
		this.id=id;
		this.seats = seats;
	}
	  
    public Long getId() {
		return id;
	}
	public void setId(Long screenId) {
		this.id = screenId;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public int getScreenRows() {
		return screenRows;
	}
	public void setScreenRows(int rows) {
		this.screenRows = rows;
	}
	public int getScreenColumns() {
		return screenColumns;
	}
	public void setScreenColumns(int cols) {
		this.screenColumns = cols;
	}

	public List<Long> getSeats() {
		return seats;
	}

	public void setSeats(List<Long> seats) {
		this.seats = seats;
	}
	

}
