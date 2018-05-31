package controllers;

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

import business.model.TravellerModel;
import business.service.ITravellerService;

@RestController
@RequestMapping("traveller")
public class TravellerController {
	
	private final ITravellerService travellerService;
	
	 @Autowired
	 public TravellerController(ITravellerService travellerService) {
		 this.travellerService = travellerService;
	 }

	 @GetMapping("")
	 public List<TravellerModel> getAllTravellers(@RequestParam(required = false) String keyword) {
		 try {
			 if(keyword == null) {
				 return travellerService.getAllTravellers();
			 }
			 else {
				 return travellerService.viewFilteredList(keyword);
			 }
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	 }
	 
	@GetMapping("{id}")
	public TravellerModel getTravellerById(@PathVariable(value = "id") Long travellerId) {
		try {
			 return travellerService.getTravellerById(travellerId);
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	}
	
	
	@PostMapping("")
	public TravellerModel saveTraveller(@RequestBody TravellerModel traveller) {
		try {
           return travellerService.saveTraveller(traveller);
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
	}
	
	@PutMapping("{id}")
	public TravellerModel updateTraveller(@PathVariable(value = "id") Long travellerId, TravellerModel traveller) {
		try {
           return travellerService.updateTraveller(travellerId, traveller);
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
	}
	
	@DeleteMapping("{id}")
	public String deleteTravellerById(@PathVariable(value = "id") Long travellerId) {
		try {
			travellerService.deleteTravellerById(travellerId);
	        return "Traveller with id = " + travellerId + " has been successfully deleted!";
	    } catch (Exception e) {
	    	return e.getMessage();
	    }
	}

}
