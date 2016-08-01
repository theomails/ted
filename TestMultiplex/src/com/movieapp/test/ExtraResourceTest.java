package com.movieapp.test;

import java.util.List;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.client.WebTarget;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.movieapp.beans.Extra;
import com.movieapp.client.ExtraResourceClient;

public class ExtraResourceTest {

	private ExtraResourceClient client = null;
	
	public ExtraResourceTest() {
		WebTarget service = TestHelper.getService();
		client = new ExtraResourceClient(service);
	}
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addExtra() {
		String extraName = "Extra" + System.nanoTime();
		Extra row = new Extra(101l, extraName, 100f);
		row = client.addExtra(row);
		Assert.assertNotEquals(101l, row.getId().longValue());
		Assert.assertEquals(extraName, row.getName());
		Assert.assertEquals(100f, row.getCost(), 0.0001f);
	}
	
	@Test
	public void getExtras() {
		List<Extra> rows = client.getAllExtras();
		if(rows==null || rows.size()==0) {
			addExtra();
			rows = client.getAllExtras();
		}
		Assert.assertNotNull(rows);
	}

	@Test
	public void getExtra() {
		List<Extra> rows = client.getAllExtras();
		if(rows==null || rows.size()==0) {
			addExtra();
			rows = client.getAllExtras();
		}
		
		Extra rowOri = rows.get(0);
		Extra rowFound = client.getExtraById(rowOri.getId());
		
		Assert.assertEquals(rowOri.getId(), rowFound.getId());
		Assert.assertEquals(rowOri.getName(), rowFound.getName());
		Assert.assertEquals(rowOri.getCost(), rowFound.getCost(), 0.001f);
	}
	
	@Test
	public void updateExtra() {
		List<Extra> rows = client.getAllExtras();
		if(rows==null || rows.size()==0) {
			addExtra();
			rows = client.getAllExtras();
		}
		
		String extraName = "Extra" + System.nanoTime();
		Extra rowOri = rows.get(0);
		rowOri.setName(extraName);
		rowOri.setCost(110f);

		rowOri = client.updateExtra(rowOri);
		
		//Returned value is updated value
		Assert.assertNotEquals(101l, rowOri.getId().longValue());
		Assert.assertEquals(extraName, rowOri.getName());
		Assert.assertEquals(110f, rowOri.getCost(), 0.0001f);
		

		Extra rowFound = client.getExtraById(rowOri.getId());
		
		//Got value is updated value
		Assert.assertEquals(rowOri.getId(), rowFound.getId());
		Assert.assertEquals(extraName, rowFound.getName());
		Assert.assertEquals(110f, rowFound.getCost(), 0.0001f);
	}
	
	
	@Test
	public void deleteExtra() {
		List<Extra> rows = client.getAllExtras();
		if(rows==null || rows.size()==0) {
			addExtra();
			rows = client.getAllExtras();
		}
		
		Extra rowOri = rows.get(0);
		System.out.println(rowOri);
		client.deleteExtra(rowOri.getId());
		Extra rowFound = null;
		boolean rightExceptionClass = false;
		try{
			rowFound = client.getExtraById(rowOri.getId());
		}catch(InternalServerErrorException e){
			rightExceptionClass = true;
		}
		System.out.println(rowFound);
		
		//Got value is updated value
		Assert.assertNull(rowFound);
		Assert.assertTrue(rightExceptionClass);
	}
}
