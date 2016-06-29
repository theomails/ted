package com.movieapp.test;

import java.util.List;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.client.WebTarget;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.movieapp.beans.Movie;
import com.movieapp.client.MovieResourceClient;

public class MovieResourceTest {

	private MovieResourceClient client = null;
	
	@Before
	public void setUp() throws Exception {
		WebTarget service = TestHelper.getService();
		client = new MovieResourceClient(service);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addMovie() {
		Movie row = new Movie(101l, "Movie1", "Genere1", "Category1", "Certificate1", "Language1", "Duration1", "Description1", "ImageUrl1", "01-01-2001");
		row = client.addMovie(row);
		Assert.assertNotEquals(101l, row.getId().longValue());
		Assert.assertEquals("Movie1", row.getMovieName());
		Assert.assertEquals("Genere1", row.getGenre());
		Assert.assertEquals("Category1", row.getCategory());
		Assert.assertEquals("Certificate1", row.getCertificate());
		Assert.assertEquals("Language1", row.getLanguage());
		Assert.assertEquals("Duration1", row.getDuration());
		Assert.assertEquals("Description1", row.getDescription());
		Assert.assertEquals("ImageUrl1", row.getImageURL());
		Assert.assertEquals("01-01-2001", row.getReleasedDate());
	}
	
	@Test
	public void getMovies() {
		List<Movie> rows = client.getAllMovies();
		Assert.assertNotNull(rows);
	}

	@Test
	public void getMovie() {
		List<Movie> rows = client.getAllMovies();
		Assert.assertNotNull(rows);
		if(rows.size()==0) return;
		
		Movie rowOri = rows.get(0);
		Movie rowFound = client.getMovieById(rowOri.getId());
		
		Assert.assertEquals(rowOri.getId(), rowFound.getId());
		Assert.assertEquals(rowOri.getMovieName(), rowFound.getMovieName());
		Assert.assertEquals(rowOri.getGenre(), rowFound.getGenre());
		Assert.assertEquals(rowOri.getCategory(), rowFound.getCategory());
		Assert.assertEquals(rowOri.getCertificate(), rowFound.getCertificate());
		Assert.assertEquals(rowOri.getLanguage(), rowFound.getLanguage());
		Assert.assertEquals(rowOri.getDuration(), rowFound.getDuration());
		Assert.assertEquals(rowOri.getDescription(), rowFound.getDescription());
		Assert.assertEquals(rowOri.getImageURL(), rowFound.getImageURL());
		Assert.assertEquals(rowOri.getReleasedDate(), rowFound.getReleasedDate());
	}
	
	@Test
	public void updateMovie() {
		List<Movie> rows = client.getAllMovies();
		Assert.assertNotNull(rows);
		if(rows.size()==0) return;
		
		Movie rowOri = rows.get(0);
		rowOri.setMovieName("Movie1a");
		rowOri.setGenre("Genere1a");
		rowOri.setCategory("Category1a");
		rowOri.setCertificate("Certificate1a");
		rowOri.setLanguage("Language1a");
		rowOri.setDuration("Duration1a");
		rowOri.setDescription("Description1a");
		rowOri.setImageURL("ImageUrl1a");
		rowOri.setReleasedDate("01-01-2011");

		rowOri = client.updateMovie(rowOri);
		
		//Returned value is updated value
		Assert.assertNotEquals(101l, rowOri.getId().longValue());
		Assert.assertEquals("Movie1a", rowOri.getMovieName());
		Assert.assertEquals("Genere1a", rowOri.getGenre());
		Assert.assertEquals("Category1a", rowOri.getCategory());
		Assert.assertEquals("Certificate1a", rowOri.getCertificate());
		Assert.assertEquals("Language1a", rowOri.getLanguage());
		Assert.assertEquals("Duration1a", rowOri.getDuration());
		Assert.assertEquals("Description1a", rowOri.getDescription());
		Assert.assertEquals("ImageUrl1a", rowOri.getImageURL());
		Assert.assertEquals("01-01-2011", rowOri.getReleasedDate());
		

		Movie rowFound = client.getMovieById(rowOri.getId());
		
		//Got value is updated value
		Assert.assertEquals(rowOri.getId(), rowFound.getId());
		Assert.assertEquals("Movie1a", rowFound.getMovieName());
		Assert.assertEquals("Genere1a", rowFound.getGenre());
		Assert.assertEquals("Category1a", rowFound.getCategory());
		Assert.assertEquals("Certificate1a", rowFound.getCertificate());
		Assert.assertEquals("Language1a", rowFound.getLanguage());
		Assert.assertEquals("Duration1a", rowFound.getDuration());
		Assert.assertEquals("Description1a", rowFound.getDescription());
		Assert.assertEquals("ImageUrl1a", rowFound.getImageURL());
		Assert.assertEquals("01-01-2011", rowFound.getReleasedDate());
	}
	
	
	@Test
	public void deleteMovie() {
		List<Movie> rows = client.getAllMovies();
		Assert.assertNotNull(rows);
		if(rows.size()==0) return;
		
		Movie rowOri = rows.get(0);
		System.out.println(rowOri);
		client.deleteMovie(rowOri.getId());
		Movie rowFound = null;
		boolean rightExceptionClass = false;
		try{
			rowFound = client.getMovieById(rowOri.getId());
		}catch(InternalServerErrorException e){
			rightExceptionClass = true;
		}
		System.out.println(rowFound);
		
		//Got value is updated value
		Assert.assertNull(rowFound);
		Assert.assertTrue(rightExceptionClass);
	}
}
