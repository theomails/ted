package com.movieapp.client;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.movieapp.beans.Customer;
import com.movieapp.wrappers.CustomerWrapper;

public class CustomerResourceClient {
	
	private WebTarget service;
	
	public CustomerResourceClient(WebTarget service){
		this.service = service;
	}
	
	public Customer addCustomer(Customer customer){
		CustomerWrapper payload = new CustomerWrapper();
		payload.setCustomer(customer);
		
		CustomerWrapper resBundle = service.path("rest").path("customers").request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(payload, MediaType.APPLICATION_JSON), CustomerWrapper.class);
		Customer res = resBundle.getCustomer();
		return res;
	}
	public void deleteCustomer(Long customerId){
		service.path("rest").path("customers").path(customerId+"").request(MediaType.TEXT_PLAIN)
				.delete();
	}
	public List<Customer> getAllCustomers(){
		CustomerWrapper resBundle = service.path("rest").path("customers").request(MediaType.APPLICATION_JSON)
				.get(CustomerWrapper.class);
		List<Customer> res = resBundle.getCustomers();
		return res;
	}
	public Customer getCustomerById(Long customerId){
		CustomerWrapper resBundle = service.path("rest").path("customers").path(customerId+"").request(MediaType.APPLICATION_JSON)
				.get(CustomerWrapper.class);
		Customer res = resBundle.getCustomer();
		return res;
	}
	public Customer updateCustomer(Customer customer){
		CustomerWrapper payload = new CustomerWrapper();
		payload.setCustomer(customer);
		
		CustomerWrapper resBundle = service.path("rest").path("customers").path(customer.getId()+"").request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(payload, MediaType.APPLICATION_JSON), CustomerWrapper.class);
		Customer res = resBundle.getCustomer();
		return res;
	}
	
	/*
	public static void main(String[] args) {


		// create one todo
		Todo todo = new Todo("3", "Blabla");
		Response response = service.path("rest").path("todos").path(todo.getId()).request(MediaType.APPLICATION_XML)
				.put(Entity.entity(todo, MediaType.APPLICATION_XML), Response.class);

		// Return code should be 201 == created resource
		System.out.println(response.getStatus());

		// Get the Todos
		System.out.println(service.path("rest").path("todos").request().accept(MediaType.TEXT_XML).get(String.class));

		// // Get JSON for application
		// System.out.println(service.path("rest").path("todos").request().accept(MediaType.APPLICATION_JSON).get(String.class));

		// Get XML for application
		System.out.println(
				service.path("rest").path("todos").request().accept(MediaType.APPLICATION_XML).get(String.class));

		// Get Todo with id 1
		Response checkDelete = service.path("rest").path("todos/1").request().accept(MediaType.APPLICATION_XML).get();

		// Delete Todo with id 1
		service.path("rest").path("todos/1").request().delete();

		// Get get all Todos id 1 should be deleted
		System.out.println(
				service.path("rest").path("todos").request().accept(MediaType.APPLICATION_XML).get(String.class));

		// Create a Todo
		Form form = new Form();
		form.param("id", "4");
		form.param("summary", "Demonstration of the client lib for forms");
		response = service.path("rest").path("todos").request()
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Response.class);
		System.out.println("Form response " + response.getStatus());

		// Get all the todos, id 4 should have been created
		System.out.println(
				service.path("rest").path("todos").request().accept(MediaType.APPLICATION_XML).get(String.class));

	}
	 */
}
