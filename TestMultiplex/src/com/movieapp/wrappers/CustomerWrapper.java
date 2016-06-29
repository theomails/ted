package com.movieapp.wrappers;

import java.util.List;

import com.movieapp.beans.Customer;

public class CustomerWrapper {
	private Customer customer;
	private List<Customer> customers;
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	@Override
	public String toString() {
		return "CustomerWrapper [customer=" + customer + ", customers=" + customers + "]";
	}
	
	
}
