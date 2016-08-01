package com.movieapp.test;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

public class TestHelper {
	public static WebTarget getService(){
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		client.register(JacksonJaxbJsonProvider.class);
		WebTarget service = client.target(getBaseURI());
		return service;
	}
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/MultiplexBooking/").build(); 
		//return UriBuilder.fromUri("http://localhost:8080/PayrollProject/PayrollProject/").build();
	}

}
