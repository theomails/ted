package com.movieapp.test;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.client.WebTarget;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.movieapp.beans.Seat;
import com.movieapp.client.ScreenResourceClient;
import com.movieapp.wrappers.ScreenInclSeats;

public class ScreenResourceTest {

	private ScreenResourceClient client = null;
	
	@Before
	public void setUp() throws Exception {
		WebTarget service = TestHelper.getService();
		client = new ScreenResourceClient(service);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addScreen() {
		String screenName = String.valueOf(System.currentTimeMillis());
		ScreenInclSeats row = new ScreenInclSeats(101l, screenName, 5, 7, new ArrayList<Seat>());
		row = client.addScreen(row);
		System.out.println(row);
		Assert.assertNotEquals(101l, row.getId().longValue());
		Assert.assertEquals(screenName, row.getScreenName());
		Assert.assertEquals(5, row.getScreenRows());
		Assert.assertEquals(7, row.getScreenColumns());
	}
	
	@Test
	public void getScreens() {
		List<ScreenInclSeats> rows = client.getAllScreens();
		Assert.assertNotNull(rows);
	}

	@Test
	public void getScreen() {
		List<ScreenInclSeats> rows = client.getAllScreens();
		Assert.assertNotNull(rows);
		if(rows.size()==0) return;
		
		ScreenInclSeats rowOri = rows.get(0);
		ScreenInclSeats rowFound = client.getScreenById(rowOri.getId());
		
		Assert.assertEquals(rowOri.getId(), rowFound.getId());
		Assert.assertEquals(rowOri.getScreenName(), rowFound.getScreenName());
		Assert.assertEquals(rowOri.getScreenRows(), rowFound.getScreenRows());
		Assert.assertEquals(rowOri.getScreenColumns(), rowFound.getScreenColumns());
	}
	
	@Test
	public void updateScreen() {
		List<ScreenInclSeats> rows = client.getAllScreens();
		Assert.assertNotNull(rows);
		if(rows.size()==0) return;
		
		ScreenInclSeats rowOri = rows.get(0);
		rowOri.setScreenName("Screen1a");
		rowOri.setScreenRows(6);
		rowOri.setScreenColumns(8);

		rowOri = client.updateScreen(rowOri);
		
		//Returned value is updated value
		Assert.assertNotEquals(101l, rowOri.getId().longValue());
		Assert.assertEquals("Screen1a", rowOri.getScreenName());
		Assert.assertEquals(6, rowOri.getScreenRows());
		Assert.assertEquals(8, rowOri.getScreenColumns());
		

		ScreenInclSeats rowFound = client.getScreenById(rowOri.getId());
		
		//Got value is updated value
		Assert.assertEquals(rowOri.getId(), rowFound.getId());
		Assert.assertEquals("Screen1a", rowFound.getScreenName());
		Assert.assertEquals(6, rowFound.getScreenRows());
		Assert.assertEquals(8, rowFound.getScreenColumns());
	}
	
	
	@Test
	public void deleteScreen() {
		List<ScreenInclSeats> rows = client.getAllScreens();
		Assert.assertNotNull(rows);
		if(rows.size()==0) return;
		
		ScreenInclSeats rowOri = rows.get(0);
		System.out.println(rowOri);
		client.deleteScreen(rowOri.getId());
		ScreenInclSeats rowFound = null;
		boolean rightExceptionClass = false;
		try{
			rowFound = client.getScreenById(rowOri.getId());
		}catch(InternalServerErrorException e){
			rightExceptionClass = true;
		}
		System.out.println(rowFound);
		
		//Got value is updated value
		Assert.assertNull(rowFound);
		Assert.assertTrue(rightExceptionClass);
	}
}
