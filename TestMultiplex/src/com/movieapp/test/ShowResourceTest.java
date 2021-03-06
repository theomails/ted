package com.movieapp.test;

import java.util.List;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.client.WebTarget;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.movieapp.beans.Show;
import com.movieapp.client.ShowResourceClient;

public class ShowResourceTest {

	private ShowResourceClient client = null;
	
	public ShowResourceTest() {
		WebTarget service = TestHelper.getService();
		client = new ShowResourceClient(service);
	}
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addShow() {
		String showName = "Show" + System.nanoTime();
		Show row = new Show(101l, showName, "0700", "0800");
		row = client.addShow(row);
		System.out.println(row);
		Assert.assertNotEquals(101l, row.getId().longValue());
		Assert.assertEquals(showName, row.getShowName());
		Assert.assertEquals("0700", row.getStartTime());
		Assert.assertEquals("0800", row.getEndTime());
	}
	
	@Test
	public void getShows() {
		List<Show> rows = client.getAllShows();
		if(rows==null || rows.size()==0){
			addShow();
			rows = client.getAllShows();
		}
		Assert.assertNotNull(rows);
	}

	@Test
	public void getShow() {
		List<Show> rows = client.getAllShows();
		if(rows==null || rows.size()==0){
			addShow();
			rows = client.getAllShows();
		}
		
		Show rowOri = rows.get(0);
		Show rowFound = client.getShowById(rowOri.getId());
		
		Assert.assertEquals(rowOri.getId(), rowFound.getId());
		Assert.assertEquals(rowOri.getShowName(), rowFound.getShowName());
		Assert.assertEquals(rowOri.getStartTime(), rowFound.getStartTime());
		Assert.assertEquals(rowOri.getEndTime(), rowFound.getEndTime());
	}
	
	@Test
	public void updateShow() {
		List<Show> rows = client.getAllShows();
		if(rows==null || rows.size()==0){
			addShow();
			rows = client.getAllShows();
		}
		
		String showName = "Show" + System.nanoTime();
		Show rowOri = rows.get(0);
		rowOri.setShowName(showName);
		rowOri.setStartTime("0701");
		rowOri.setEndTime("0801");

		rowOri = client.updateShow(rowOri);
		
		//Returned value is updated value
		Assert.assertNotEquals(101l, rowOri.getId().longValue());
		Assert.assertEquals(showName, rowOri.getShowName());
		Assert.assertEquals("0701", rowOri.getStartTime());
		Assert.assertEquals("0801", rowOri.getEndTime());
		

		Show rowFound = client.getShowById(rowOri.getId());
		
		//Got value is updated value
		Assert.assertEquals(rowOri.getId(), rowFound.getId());
		Assert.assertEquals(showName, rowFound.getShowName());
		Assert.assertEquals("0701", rowFound.getStartTime());
		Assert.assertEquals("0801", rowFound.getEndTime());

	}
	
	
	@Test
	public void deleteShow() {
		List<Show> rows = client.getAllShows();
		if(rows==null || rows.size()==0){
			addShow();
			rows = client.getAllShows();
		}
		
		Show rowOri = rows.get(0);
		System.out.println(rowOri);
		client.deleteShow(rowOri.getId());
		Show rowFound = null;
		boolean rightExceptionClass = false;
		try{
			rowFound = client.getShowById(rowOri.getId());
		}catch(InternalServerErrorException e){
			rightExceptionClass = true;
		}
		System.out.println(rowFound);
		
		//Got value is updated value
		Assert.assertNull(rowFound);
		Assert.assertTrue(rightExceptionClass);
	}
}
