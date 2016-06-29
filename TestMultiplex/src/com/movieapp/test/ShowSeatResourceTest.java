package com.movieapp.test;

import java.util.List;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.client.WebTarget;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.movieapp.beans.MovieShow;
import com.movieapp.beans.Seat;
import com.movieapp.beans.ShowSeat;
import com.movieapp.beans.Ticket;
import com.movieapp.client.MovieShowResourceClient;
import com.movieapp.client.SeatResourceClient;
import com.movieapp.client.ShowSeatResourceClient;
import com.movieapp.client.TicketResourceClient;

public class ShowSeatResourceTest {

	private ShowSeatResourceClient client = null;
	private SeatResourceClient seatclient = null;
	private MovieShowResourceClient msclient = null;
	private TicketResourceClient ticketclient = null;
	
	@Before
	public void setUp() throws Exception {
		WebTarget service = TestHelper.getService();
		client = new ShowSeatResourceClient(service);
		seatclient = new SeatResourceClient(service);
		msclient = new MovieShowResourceClient(service);
		ticketclient = new TicketResourceClient(service);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addShowSeat() {
		List<MovieShow> mshows = msclient.getAllMovieShows();
		List<Seat> seats = seatclient.getAllSeats();
		List<Ticket> tickets = ticketclient.getAllTickets();
		MovieShow mshow = mshows.get(0);
		Seat seat = seats.get(0);
		Ticket ticket = tickets.get(0);
				
		ShowSeat row = new ShowSeat(101l, ticket.getId(), seat.getId(), mshow.getId(), false);
		row = client.addShowSeat(row);
		System.out.println(row);
		Assert.assertNotEquals(101l, row.getId().longValue());
		Assert.assertEquals(ticket.getId(), row.getTicketID().longValue());
	}
	
	@Test
	public void getShowSeats() {
		List<ShowSeat> rows = client.getAllShowSeats();
		Assert.assertNotNull(rows);
	}

	@Test
	public void getShowSeat() {
		List<ShowSeat> rows = client.getAllShowSeats();
		Assert.assertNotNull(rows);
		if(rows.size()==0) return;
		
		ShowSeat rowOri = rows.get(0);
		ShowSeat rowFound = client.getShowSeatById(rowOri.getId());
		
		Assert.assertEquals(rowOri.getId(), rowFound.getId());
		Assert.assertEquals(rowOri.getMovieShowID(), rowFound.getMovieShowID());
		Assert.assertEquals(rowOri.getSeatID(), rowFound.getSeatID());
		Assert.assertEquals(rowOri.getTicketID(), rowFound.getTicketID());
	}
	
	
	@Test
	public void deleteShowSeat() {
		List<ShowSeat> rows = client.getAllShowSeats();
		Assert.assertNotNull(rows);
		if(rows.size()==0) return;
		
		ShowSeat rowOri = rows.get(0);
		System.out.println(rowOri);
		client.deleteShowSeat(rowOri.getId());
		ShowSeat rowFound = null;
		boolean rightExceptionClass = false;
		try{
			rowFound = client.getShowSeatById(rowOri.getId());
		}catch(InternalServerErrorException e){
			rightExceptionClass = true;
		}
		System.out.println(rowFound);
		
		//Got value is updated value
		Assert.assertNull(rowFound);
		Assert.assertTrue(rightExceptionClass);
	}
}
