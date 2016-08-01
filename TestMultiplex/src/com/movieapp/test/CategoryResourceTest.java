package com.movieapp.test;

import java.util.List;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.client.WebTarget;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.movieapp.beans.Category;
import com.movieapp.client.CategoryResourceClient;

public class CategoryResourceTest {

	private CategoryResourceClient client = null;
	
	public CategoryResourceTest() {
		WebTarget service = TestHelper.getService();
		client = new CategoryResourceClient(service);
	}
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addCategory() {
		String catName = "Catg" + System.nanoTime();
		Category c = new Category(101l, catName, 120.0f);
		c = client.addCategory(c);
		Assert.assertNotEquals(101l, c.getId().longValue());
		Assert.assertEquals(catName, c.getCategoryName());
		Assert.assertEquals(120.0f, c.getFare().floatValue(), 0.0001f);
	}
	
	@Test
	public void getCategories() {
		List<Category> categories = client.getAllCategories();
		if(categories==null || categories.size()==0) {
			addCategory();
			categories = client.getAllCategories();
		}
		Assert.assertNotNull(categories);
	}

	@Test
	public void getCategory() {
		List<Category> categories = client.getAllCategories();
		if(categories==null || categories.size()==0) {
			addCategory();
			categories = client.getAllCategories();
		}
		
		Category cori = categories.get(0);
		Category cfound = client.getCategoryById(cori.getId());
		
		Assert.assertEquals(cori.getId(), cfound.getId());
		Assert.assertEquals(cori.getCategoryName(), cfound.getCategoryName());
		Assert.assertEquals(cori.getFare(), cfound.getFare());
	}
	
	@Test
	public void updateCategory() {
		List<Category> categories = client.getAllCategories();
		if(categories==null || categories.size()==0) {
			addCategory();
			categories = client.getAllCategories();
		}
		
		String catName = "Catg" + System.nanoTime();
		Category cori = categories.get(0);
		cori.setCategoryName(catName);
		cori.setFare(130f);

		cori = client.updateCategory(cori);
		
		//Returned value is updated value
		Assert.assertNotEquals(101l, cori.getId().longValue());
		Assert.assertEquals(catName, cori.getCategoryName());
		Assert.assertEquals(130.0f, cori.getFare().floatValue(), 0.0001f);
		

		Category cfound = client.getCategoryById(cori.getId());
		
		//Got value is updated value
		Assert.assertEquals(cori.getId(), cfound.getId());
		Assert.assertEquals(catName, cfound.getCategoryName());
		Assert.assertEquals(130.0f, cfound.getFare().floatValue(), 0.0001f);
	}
	
	
	@Test
	public void deleteCategory() {
		List<Category> categories = client.getAllCategories();
		if(categories==null || categories.size()==0) {
			addCategory();
			categories = client.getAllCategories();
		}
		
		Category cori = categories.get(0);
		System.out.println(cori);
		client.deleteCategory(cori.getId());
		
		Category cfound = null;
		try{
			cfound = client.getCategoryById(cori.getId());
		}catch(InternalServerErrorException e){}
		System.out.println(cfound);
		
		//Got value is updated value
		Assert.assertNull(cfound);
	}
}
