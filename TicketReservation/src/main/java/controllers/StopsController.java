package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import business.model.StopsModel;
import business.service.IStopsService;

@RestController
@RequestMapping("station")
public class StopsController {
	
	private final IStopsService stopsService;
	
	 @Autowired
	 public StopsController(IStopsService stopsService) {
		 this.stopsService = stopsService;
	 }

	 @GetMapping("")
	 public List<StopsModel> getAllStops() {
		 try {
			 return stopsService.getAllStops();
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	 }
	
	@PostMapping("")
	public StopsModel saveStop(@RequestBody StopsModel stop) {
		try {
         return stopsService.saveStop(stop);
     } catch (Exception e) {
         e.printStackTrace();
         return null;
     }
	}
	
	
	@DeleteMapping("{id}")
	public String deleteStopById(@PathVariable(value = "id") Long stopId) {
		try {
			stopsService.deleteStopById(stopId);
	        return "Stop with id = " + stopId + " has been successfully deleted!";
	    } catch (Exception e) {
	    	return e.getMessage();
	    }
	}

}
