package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import business.model.BusModel;
import business.model.SellTicketModel;
import business.service.ITicketService;

@RestController
@RequestMapping("reservation")
public class SellTicketController {
	
	private final ITicketService ticketService;
	
	 @Autowired
	 public SellTicketController(ITicketService ticketService) {
		 this.ticketService = ticketService;
	 }
	 
	 /*
	 @PutMapping("")
	 public BusModel sellTicket(String fromCity, String toCity, String busAgency, String date, int numberOfTickets) {
		 try {
			 return ticketService.sellTicket(fromCity, toCity, busAgency, date, numberOfTickets);
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	 }*/
	 
	 @PutMapping("")
	 public BusModel sellTicket(SellTicketModel sellTicket) {
		 try {
			 return ticketService.sellTicket(sellTicket);
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	 }
}
