package com.movieapp.test;

import java.util.List;

import javax.ws.rs.client.WebTarget;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.movieapp.beans.Category;
import com.movieapp.client.CategoryResourceClient;

public class CategoryResourceTest {

	private CategoryResourceClient client = null;
	
	@Before
	public void setUp() throws Exception {
		WebTarget service = TestHelper.getService();
		client = new CategoryResourceClient(service);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addCategory() {
		Category c = new Category(101l, "Cat1", 120.0f);
		c = client.addCategory(c);
		Assert.assertNotEquals(101l, c.getId().longValue());
		Assert.assertEquals("Cat1", c.getCategoryName());
		Assert.assertEquals(120.0f, c.getFare().floatValue(), 0.0001f);
	}
	
	@Test
	public void getCategories() {
		List<Category> categories = client.getAllCategories();
		Assert.assertNotNull(categories);
	}

	@Test
	public void getCategory() {
		List<Category> categories = client.getAllCategories();
		Assert.assertNotNull(categories);
		if(categories.size()==0) return;
		
		Category cori = categories.get(0);
		Category cfound = client.getCategoryById(cori.getId());
		
		Assert.assertEquals(cori.getId(), cfound.getId());
		Assert.assertEquals(cori.getCategoryName(), cfound.getCategoryName());
		Assert.assertEquals(cori.getFare(), cfound.getFare());
	}
	
	@Test
	public void updateCategory() {
		List<Category> categories = client.getAllCategories();
		Assert.assertNotNull(categories);
		if(categories.size()==0) return;
		
		Category cori = categories.get(0);
		cori.setCategoryName("Cat1a");
		cori.setFare(130f);

		cori = client.updateCategory(cori);
		
		//Returned value is updated value
		Assert.assertNotEquals(101l, cori.getId().longValue());
		Assert.assertEquals("Cat1a", cori.getCategoryName());
		Assert.assertEquals(130.0f, cori.getFare().floatValue(), 0.0001f);
		

		Category cfound = client.getCategoryById(cori.getId());
		
		//Got value is updated value
		Assert.assertEquals(cori.getId(), cfound.getId());
		Assert.assertEquals("Cat1a", cfound.getCategoryName());
		Assert.assertEquals(130.0f, cfound.getFare().floatValue(), 0.0001f);
	}
	
	
	@Test
	public void deleteCategory() {
		List<Category> categories = client.getAllCategories();
		Assert.assertNotNull(categories);
		if(categories.size()==0) return;
		
		Category cori = categories.get(0);
		System.out.println(cori);
		client.deleteCategory(cori.getId());
		
		Category cfound = client.getCategoryById(cori.getId());
		System.out.println(cfound);
		
		//Got value is updated value
		Assert.assertNull(cfound);
	}
}
