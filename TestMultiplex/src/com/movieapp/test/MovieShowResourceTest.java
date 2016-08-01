package com.movieapp.test;

import java.util.List;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.client.WebTarget;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.movieapp.beans.Movie;
import com.movieapp.beans.MovieShow;
import com.movieapp.beans.Show;
import com.movieapp.client.MovieResourceClient;
import com.movieapp.client.MovieShowResourceClient;
import com.movieapp.client.ScreenResourceClient;
import com.movieapp.client.ShowResourceClient;
import com.movieapp.wrappers.ScreenInclSeats;

public class MovieShowResourceTest {

	private MovieShowResourceClient client = null;
	private ScreenResourceClient scrclient = null;
	private MovieResourceClient movclient = null;
	private ShowResourceClient showclient = null;
	
	public MovieShowResourceTest() {
		WebTarget service = TestHelper.getService();
		client = new MovieShowResourceClient(service);
		scrclient = new ScreenResourceClient(service);
		movclient = new MovieResourceClient(service);
		showclient = new ShowResourceClient(service);
	}
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addMovieShow() {
		List<ScreenInclSeats> screens = scrclient.getAllScreens();
		if(screens==null || screens.size()==0){
			ScreenResourceTest srt = new ScreenResourceTest();
			srt.addScreen();
			screens = scrclient.getAllScreens();
		}
		List<Movie> movies = movclient.getAllMovies();
		if(movies==null || movies.size()==0){
			MovieResourceTest mrt = new MovieResourceTest();
			mrt.addMovie();
			movies = movclient.getAllMovies();
		}
		List<Show> shows = showclient.getAllShows();
		if(shows==null || shows.size()==0){
			ShowResourceTest srt = new ShowResourceTest();
			srt.addShow();
			shows = showclient.getAllShows();
		}
		ScreenInclSeats scr = screens.get(0);
		Movie mov = movies.get(0);
		Show show = shows.get(0);
		
		MovieShow row = new MovieShow(101l, scr.getId(), mov.getId(), show.getId(), "01-01-2001");
		row = client.addMovieShow(row);
		System.out.println(row);
		Assert.assertNotEquals(101l, row.getId().longValue());
		Assert.assertEquals("01-01-2001", row.getMovieDate());
		Assert.assertEquals(scr.getId(), row.getScreenID());
		Assert.assertEquals(mov.getId(), row.getMovieID());
		Assert.assertEquals(show.getId(), row.getShowID());
	}
	
	@Test
	public void getMovieShows() {
		List<MovieShow> rows = client.getAllMovieShows();
		if(rows==null || rows.size()==0){
			addMovieShow();
			rows = client.getAllMovieShows();
		}
		Assert.assertNotNull(rows);
	}

	@Test
	public void getMovieShow() {
		List<MovieShow> rows = client.getAllMovieShows();
		if(rows==null || rows.size()==0){
			addMovieShow();
			rows = client.getAllMovieShows();
		}
		
		MovieShow rowOri = rows.get(0);
		MovieShow rowFound = client.getMovieShowById(rowOri.getId());
		
		Assert.assertEquals(rowOri.getId(), rowFound.getId());
		Assert.assertEquals(rowOri.getMovieDate(), rowFound.getMovieDate());
		Assert.assertEquals(rowOri.getScreenID(), rowFound.getScreenID());
		Assert.assertEquals(rowOri.getMovieID(), rowFound.getMovieID());
		Assert.assertEquals(rowOri.getShowID(), rowFound.getShowID());
	}
	
	@Test
	public void updateMovieShow() {
		List<MovieShow> rows = client.getAllMovieShows();
		if(rows==null || rows.size()==0){
			addMovieShow();
			rows = client.getAllMovieShows();
		}
		
		MovieShow rowOri = rows.get(0);
		rowOri.setMovieDate("01-01-2011");

		rowOri = client.updateMovieShow(rowOri);
		
		//Returned value is updated value
		Assert.assertNotEquals(101l, rowOri.getId().longValue());
		Assert.assertEquals("01-01-2001", rowOri.getMovieDate());
		

		MovieShow rowFound = client.getMovieShowById(rowOri.getId());
		
		//Got value is updated value
		Assert.assertEquals(rowOri.getId(), rowFound.getId());
		Assert.assertEquals("01-01-2001", rowFound.getMovieDate());

	}
	
	
	@Test
	public void deleteMovieShow() {
		List<MovieShow> rows = client.getAllMovieShows();
		if(rows==null || rows.size()==0){
			addMovieShow();
			rows = client.getAllMovieShows();
		}
		
		MovieShow rowOri = rows.get(0);
		System.out.println(rowOri);
		client.deleteMovieShow(rowOri.getId());
		MovieShow rowFound = null;
		boolean rightExceptionClass = false;
		try{
			rowFound = client.getMovieShowById(rowOri.getId());
		}catch(InternalServerErrorException e){
			rightExceptionClass = true;
		}
		System.out.println(rowFound);
		
		//Got value is updated value
		Assert.assertNull(rowFound);
		Assert.assertTrue(rightExceptionClass);
	}
}
