package com.movieapp.wrappers;

import java.util.List;
import java.util.Map;

import com.movieapp.beans.Ticket;

public class TicketWrapper {
	private Ticket ticket;
	private List<Ticket> tickets;
	
	//Others
	private List<Map<String, String>> errors;
	
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	public List<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
		
	
	public List<Map<String, String>> getErrors() {
		return errors;
	}
	public void setErrors(List<Map<String, String>> errors) {
		this.errors = errors;
	}
	@Override
	public String toString() {
		return "TicketWrapper [ticket=" + ticket + ", tickets=" + tickets + "]";
	}
	
	
}
