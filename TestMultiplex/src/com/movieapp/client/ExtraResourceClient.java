package com.movieapp.client;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.movieapp.beans.Extra;
import com.movieapp.wrappers.ExtraWrapper;

public class ExtraResourceClient {
	
	private WebTarget service;
	
	public ExtraResourceClient(WebTarget service){
		this.service = service;
	}
	
	public Extra addExtra(Extra row){
		ExtraWrapper payload = new ExtraWrapper();
		payload.setExtra(row);
		
		ExtraWrapper resBundle = service.path("rest").path("extras").request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(payload, MediaType.APPLICATION_JSON), ExtraWrapper.class);
		Extra res = resBundle.getExtra();
		return res;
	}
	public void deleteExtra(Long rowId){
		service.path("rest").path("extras").path(rowId+"").request(MediaType.TEXT_PLAIN)
				.delete();
	}
	public List<Extra> getAllExtras(){
		ExtraWrapper resBundle = service.path("rest").path("extras").request(MediaType.APPLICATION_JSON)
				.get(ExtraWrapper.class);
		List<Extra> res = resBundle.getExtras();
		return res;
	}
	public Extra getExtraById(Long rowId){
		ExtraWrapper resBundle = service.path("rest").path("extras").path(rowId+"").request(MediaType.APPLICATION_JSON)
				.get(ExtraWrapper.class);
		Extra res = resBundle.getExtra();
		return res;
	}
	public Extra updateExtra(Extra row){
		ExtraWrapper payload = new ExtraWrapper();
		payload.setExtra(row);
		
		ExtraWrapper resBundle = service.path("rest").path("extras").path(row.getId()+"").request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(payload, MediaType.APPLICATION_JSON), ExtraWrapper.class);
		Extra res = resBundle.getExtra();
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
