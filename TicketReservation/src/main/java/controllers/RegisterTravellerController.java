package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import business.model.TravellerModel;
import business.service.ITravellerService;

@RestController
@RequestMapping("registerTraveller")
public class RegisterTravellerController {
	
	private final ITravellerService travellerService;
	
	 @Autowired
	 public RegisterTravellerController(ITravellerService travellerService) {
		 this.travellerService = travellerService;
	 }
	 
	 @PostMapping("")
	 public TravellerModel registerTraveller(@RequestBody TravellerModel traveller) {
		 try {
			 return travellerService.saveTraveller(traveller);
			 } catch (Exception e) {
				 e.printStackTrace();
	             return null;
			 }
	 } 

}
