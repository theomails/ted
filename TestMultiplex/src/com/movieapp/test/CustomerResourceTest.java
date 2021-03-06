package com.movieapp.test;

import java.util.List;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.client.WebTarget;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.movieapp.beans.Customer;
import com.movieapp.client.CustomerResourceClient;

public class CustomerResourceTest {

	private CustomerResourceClient client = null;
	
	public CustomerResourceTest() {
		WebTarget service = TestHelper.getService();
		client = new CustomerResourceClient(service);
	}
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addCustomer() {
		String custName = "Cust" + System.nanoTime();
		Customer row = new Customer(101l, custName, "cust1@gmail.com", "9009009001");
		row = client.addCustomer(row);
		Assert.assertNotEquals(101l, row.getId().longValue());
		Assert.assertEquals(custName, row.getName());
		Assert.assertEquals("cust1@gmail.com", row.getEmail());
		Assert.assertEquals("9009009001", row.getPhoneNumber());
	}
	
	@Test
	public void getCustomers() {
		List<Customer> rows = client.getAllCustomers();
		if(rows==null || rows.size()==0) {
			addCustomer();
			rows = client.getAllCustomers();
		}
		Assert.assertNotNull(rows);
	}

	@Test
	public void getCustomer() {
		List<Customer> rows = client.getAllCustomers();
		if(rows==null || rows.size()==0) {
			addCustomer();
			rows = client.getAllCustomers();
		}
		
		Customer rowOri = rows.get(0);
		Customer rowFound = client.getCustomerById(rowOri.getId());
		
		Assert.assertEquals(rowOri.getId(), rowFound.getId());
		Assert.assertEquals(rowOri.getName(), rowFound.getName());
		Assert.assertEquals(rowOri.getEmail(), rowFound.getEmail());
		Assert.assertEquals(rowOri.getPhoneNumber(), rowFound.getPhoneNumber());
	}
	
	@Test
	public void updateCustomer() {
		List<Customer> rows = client.getAllCustomers();
		if(rows==null || rows.size()==0) {
			addCustomer();
			rows = client.getAllCustomers();
		}
		
		String custName = "Cust" + System.nanoTime();
		Customer rowOri = rows.get(0);
		rowOri.setName(custName);
		rowOri.setEmail("cust1a@gmail.com");
		rowOri.setPhoneNumber("9009009901");

		rowOri = client.updateCustomer(rowOri);
		
		//Returned value is updated value
		Assert.assertNotEquals(101l, rowOri.getId().longValue());
		Assert.assertEquals(custName, rowOri.getName());
		Assert.assertEquals("cust1a@gmail.com", rowOri.getEmail());
		Assert.assertEquals("9009009901", rowOri.getPhoneNumber());
		

		Customer rowFound = client.getCustomerById(rowOri.getId());
		
		//Got value is updated value
		Assert.assertEquals(rowOri.getId(), rowFound.getId());
		Assert.assertEquals(custName, rowFound.getName());
		Assert.assertEquals("cust1a@gmail.com", rowFound.getEmail());
		Assert.assertEquals("9009009901", rowFound.getPhoneNumber());
	}
	
	
	@Test
	public void deleteCustomer() {
		List<Customer> rows = client.getAllCustomers();
		if(rows==null || rows.size()==0) {
			addCustomer();
			rows = client.getAllCustomers();
		}
		
		Customer rowOri = rows.get(0);
		System.out.println(rowOri);
		client.deleteCustomer(rowOri.getId());
		Customer rowFound = null;
		boolean rightExceptionClass = false;
		try{
			rowFound = client.getCustomerById(rowOri.getId());
		}catch(InternalServerErrorException e){
			rightExceptionClass = true;
		}
		System.out.println(rowFound);
		
		//Got value is updated value
		Assert.assertNull(rowFound);
		Assert.assertTrue(rightExceptionClass);
	}
}
