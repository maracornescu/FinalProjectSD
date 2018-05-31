package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import business.model.ReservationModel;
import business.service.ITicketService;

@RestController
@RequestMapping("travellerRoute")
public class PossibleRoutesController {
	
	private final ITicketService ticketService;
	
	 @Autowired
	 public PossibleRoutesController(ITicketService ticketService) {
		 this.ticketService = ticketService;
	 }

	 @GetMapping("")
	 public List<ReservationModel> getAllPossibleRoutes(String fromCity, String toCity, String date, int numberOfSeats, 
			 @RequestParam(required = false) Long agencyId) {
		 try {
			 if(agencyId == null) {
				 return ticketService.findAllPossibleRoutes(fromCity, toCity, date, numberOfSeats); 
			 }
			 else {
				 return ticketService.findAllPossibleRoutesForAgency(fromCity, toCity, date, numberOfSeats, agencyId);
			 }
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	 }
	 
}
