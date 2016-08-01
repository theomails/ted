package com.movieapp.test;

import java.util.List;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.client.WebTarget;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.movieapp.beans.Category;
import com.movieapp.beans.Seat;
import com.movieapp.client.CategoryResourceClient;
import com.movieapp.client.ScreenResourceClient;
import com.movieapp.client.SeatResourceClient;
import com.movieapp.wrappers.ScreenInclSeats;

public class SeatResourceTest {

	private SeatResourceClient client = null;
	private ScreenResourceClient scrclient = null;
	private CategoryResourceClient catclient = null;
	
	public SeatResourceTest() {
		WebTarget service = TestHelper.getService();
		client = new SeatResourceClient(service);
		scrclient = new ScreenResourceClient(service);
		catclient = new CategoryResourceClient(service);
	}
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addSeat() {
		List<ScreenInclSeats> screens = scrclient.getAllScreens();
		if(screens==null || screens.size()==0){
			ScreenResourceTest srt = new ScreenResourceTest();
			srt.addScreen();
			screens = scrclient.getAllScreens();
		}
		List<Category> categories = catclient.getAllCategories();
		if(categories==null || categories.size()==0){
			CategoryResourceTest crt = new CategoryResourceTest();
			crt.addCategory();
			categories = catclient.getAllCategories();
		}
		ScreenInclSeats scr = screens.get(0);
		Category cat = categories.get(0);
		
		Seat row = new Seat(101l, scr.getId(), cat.getId(), "1A", 1, 1, true);
		row = client.addSeat(row);
		System.out.println(row);
		Assert.assertNotEquals(101l, row.getId().longValue());
		Assert.assertEquals("1A", row.getName());
		Assert.assertEquals(1, row.getRowNumber());
		Assert.assertEquals(1, row.getColumnNumber());
	}
	
	@Test
	public void getSeats() {
		List<Seat> rows = client.getAllSeats();
		if(rows==null || rows.size()==0){
			addSeat();
			rows = client.getAllSeats();
		}
		Assert.assertNotNull(rows);
	}

	@Test
	public void getSeat() {
		List<Seat> rows = client.getAllSeats();
		if(rows==null || rows.size()==0){
			addSeat();
			rows = client.getAllSeats();
		}
		
		Seat rowOri = rows.get(0);
		Seat rowFound = client.getSeatById(rowOri.getId());
		
		Assert.assertEquals(rowOri.getId(), rowFound.getId());
		Assert.assertEquals(rowOri.getName(), rowFound.getName());
		Assert.assertEquals(rowOri.getRowNumber(), rowFound.getRowNumber());
		Assert.assertEquals(rowOri.getColumnNumber(), rowFound.getColumnNumber());
	}
	
	@Test
	public void updateSeat() {
		List<Seat> rows = client.getAllSeats();
		if(rows==null || rows.size()==0){
			addSeat();
			rows = client.getAllSeats();
		}
		
		Seat rowOri = rows.get(0);
		rowOri.setName("2B");
		rowOri.setRowNumber(2);
		rowOri.setColumnNumber(2);

		rowOri = client.updateSeat(rowOri);
		
		//Returned value is updated value
		Assert.assertNotEquals(101l, rowOri.getId().longValue());
		Assert.assertEquals("2B", rowOri.getName());
		Assert.assertEquals(2, rowOri.getRowNumber());
		Assert.assertEquals(3, rowOri.getColumnNumber());
		

		Seat rowFound = client.getSeatById(rowOri.getId());
		
		//Got value is updated value
		Assert.assertEquals(rowOri.getId(), rowFound.getId());
		Assert.assertEquals("2B", rowFound.getName());
		Assert.assertEquals(2, rowFound.getRowNumber());
		Assert.assertEquals(3, rowFound.getColumnNumber());

	}
	
	
	@Test
	public void deleteSeat() {
		List<Seat> rows = client.getAllSeats();
		if(rows==null || rows.size()==0){
			addSeat();
			rows = client.getAllSeats();
		}
		
		Seat rowOri = rows.get(0);
		System.out.println(rowOri);
		client.deleteSeat(rowOri.getId());
		Seat rowFound = null;
		boolean rightExceptionClass = false;
		try{
			rowFound = client.getSeatById(rowOri.getId());
		}catch(InternalServerErrorException e){
			rightExceptionClass = true;
		}
		System.out.println(rowFound);
		
		//Got value is updated value
		Assert.assertNull(rowFound);
		Assert.assertTrue(rightExceptionClass);
	}
}
