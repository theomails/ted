package com.movieapp.test;

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

}
