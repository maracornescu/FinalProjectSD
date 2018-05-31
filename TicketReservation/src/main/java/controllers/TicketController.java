package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import business.model.BusModel;
import business.model.BusRouteModel;
import business.model.ReservationModel;
import business.model.SellTicketModel;
import business.model.TicketModel;
import business.service.ITicketService;

@RestController
@RequestMapping("ticket")
public class TicketController {
	
	private final ITicketService ticketService;
	
	 @Autowired
	 public TicketController(ITicketService ticketService) {
		 this.ticketService = ticketService;
	 }
	 
	 /*
	 @GetMapping("")
	 public List<SellTicketModel> getAllTickets(Long travellerId) {			
		 try {
			 return ticketService.getAllTicketsByTraveller(travellerId);
	    } catch (Exception e) {
	    	e.printStackTrace();
	         return null;
	    }
	 }*/
	 
	 
	 @GetMapping("")
	 public List<SellTicketModel> getAllTickets(@RequestParam(required = false) Long busAgency, @RequestParam(required = false) Long busId, 
			 @RequestParam(required = false) Long travellerId) {
			 try {
				 if(busId == null && travellerId == null) {
					 return ticketService.getAllTickets(busAgency);
				 }
				 else if(busId == null) {
					 return ticketService.getAllTicketsByTraveller(travellerId);
				 }
				 else if (travellerId == null) {
					 return ticketService.getAllTicketsByBus(busId);
				 }
				 else {
					 SellTicketModel m = ticketService.getAllTicketsByBusAndTraveller(busId, travellerId);
					 List<SellTicketModel> s = new ArrayList<SellTicketModel>();
					 s.add(m);
					 return s;
				 }
		     } catch (Exception e) {
		         e.printStackTrace();
		         return null;
		     }
	}
	 
		@DeleteMapping("{id}")
		public String deleteTicketById(@PathVariable(value = "id") Long ticketId) {
			try {
				ticketService.deleteTicketById(ticketId);
		        return "Ticket with id = " + ticketId + " has been successfully deleted!";
		    } catch (Exception e) {
		    	return e.getMessage();
		    }
		}
		/*
		@PostMapping("")
		public TicketModel saveTicket(@RequestBody TicketModel ticket) {
			try {
	          return ticketService.saveTicket(ticket);
	      } catch (Exception e) {
	          e.printStackTrace();
	          return null;
	      }
		}*/
		
		@PostMapping("")
		public TicketModel saveTicket(@RequestBody SellTicketModel sellTicket) {
			try {
	          return ticketService.saveTicket(sellTicket);
	      } catch (Exception e) {
	          e.printStackTrace();
	          return null;
	      }
		}
	  /*
	 @GetMapping("route")
	 public List<ReservationModel> getAllPossibleRoutes(String fromCity, String toCity, String date, int numberOfSeats) {
		 try {
			 return ticketService.findAllPossibleRoutes(fromCity, toCity, date, numberOfSeats);
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	 }
	 
	 
	 @PutMapping("")
	 public BusModel sellTicket(String fromCity, String toCity, String busAgency, String date, int numberOfTickets) {
		 try {
			 return ticketService.sellTicket(fromCity, toCity, busAgency, date, numberOfTickets);
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	 }*/
}
