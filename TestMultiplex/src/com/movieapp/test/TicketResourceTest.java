package com.movieapp.test;

import java.util.List;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.client.WebTarget;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.movieapp.beans.Customer;
import com.movieapp.beans.MovieShow;
import com.movieapp.beans.Ticket;
import com.movieapp.client.CustomerResourceClient;
import com.movieapp.client.MovieShowResourceClient;
import com.movieapp.client.TicketResourceClient;

public class TicketResourceTest {

	private TicketResourceClient client = null;
	private CustomerResourceClient custclient = null;
	private MovieShowResourceClient msclient = null;
	
	@Before
	public void setUp() throws Exception {
		WebTarget service = TestHelper.getService();
		client = new TicketResourceClient(service);
		custclient = new CustomerResourceClient(service);
		msclient = new MovieShowResourceClient(service);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addTicket() {
		List<MovieShow> mshows = msclient.getAllMovieShows();
		List<Customer> customers = custclient.getAllCustomers();
		MovieShow mshow = mshows.get(0);
		Customer cust = customers.get(0);
				
		Ticket row = new Ticket(101l, mshow.getId(), cust.getId(), 100f);
		row = client.addTicket(row);
		System.out.println(row);
		Assert.assertNotEquals(101l, row.getId());
		Assert.assertEquals(100f, row.getTotalCost(), 0.0001f);
	}
	
	@Test
	public void getTickets() {
		List<Ticket> rows = client.getAllTickets();
		Assert.assertNotNull(rows);
	}

	@Test
	public void getTicket() {
		List<Ticket> rows = client.getAllTickets();
		Assert.assertNotNull(rows);
		if(rows.size()==0) return;
		
		Ticket rowOri = rows.get(0);
		Ticket rowFound = client.getTicketById(rowOri.getId());
		
		Assert.assertEquals(rowOri.getId(), rowFound.getId());
		Assert.assertEquals(rowOri.getMovieShowID(), rowFound.getMovieShowID());
		Assert.assertEquals(rowOri.getCustomerID(), rowFound.getCustomerID());
		Assert.assertEquals(rowOri.getTotalCost(), rowFound.getTotalCost());
	}
	
	@Test
	public void updateTicket() {
		List<Ticket> rows = client.getAllTickets();
		Assert.assertNotNull(rows);
		if(rows.size()==0) return;
		
		Ticket rowOri = rows.get(0);
		rowOri.setTotalCost(110f);

		rowOri = client.updateTicket(rowOri);
		
		//Returned value is updated value
		Assert.assertNotEquals(101l, rowOri.getId());
		Assert.assertEquals(110f, rowOri.getTotalCost(), 0.001f);
		

		Ticket rowFound = client.getTicketById(rowOri.getId());
		
		//Got value is updated value
		Assert.assertEquals(rowOri.getId(), rowFound.getId());
		Assert.assertEquals(110f, rowFound.getTotalCost(), 0.001f);

	}
	
	
	@Test
	public void deleteTicket() {
		List<Ticket> rows = client.getAllTickets();
		Assert.assertNotNull(rows);
		if(rows.size()==0) return;
		
		Ticket rowOri = rows.get(0);
		System.out.println(rowOri);
		client.deleteTicket(rowOri.getId());
		Ticket rowFound = null;
		boolean rightExceptionClass = false;
		try{
			rowFound = client.getTicketById(rowOri.getId());
		}catch(InternalServerErrorException e){
			rightExceptionClass = true;
		}
		System.out.println(rowFound);
		
		//Got value is updated value
		Assert.assertNull(rowFound);
		Assert.assertTrue(rightExceptionClass);
	}
}
