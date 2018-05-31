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

import business.model.BusModel;
import business.service.IBusService;

@RestController
@RequestMapping("bus")
public class BusController {
	
	private final IBusService busService;
	
	 @Autowired
	 public BusController(IBusService busService) {
		 this.busService = busService;
	 }

	 @GetMapping("")
	 public List<BusModel> getAllBusses(@RequestParam(required = false) Long agencyId) {
		 try {
			 if(agencyId == null) {
				 return busService.getAllBusses();
			 }
			 else {
				 return busService.getAllByAgency(agencyId);
			 }
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	 }
	 
	@GetMapping("{id}")
	public BusModel getBusById(@PathVariable(value = "id") Long busId) {
		try {
			 return busService.getBusById(busId);
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	}
	
	
	@PostMapping("")
	public BusModel saveBus(@RequestBody BusModel bus) {
		try {
          return busService.saveBus(bus);
      } catch (Exception e) {
          e.printStackTrace();
          return null;
      }
	}
	
	@PutMapping("{id}")
	public BusModel updateBus(@PathVariable(value = "id") Long busId, BusModel bus) {
		try {
          return busService.updateBus(busId, bus);
      } catch (Exception e) {
          e.printStackTrace();
          return null;
      }
	}
	
	@DeleteMapping("{id}")
	public String deleteBusById(@PathVariable(value = "id") Long busId) {
		try {
			busService.deleteBusById(busId);
	        return "Bus with id = " + busId + " has been successfully deleted!";
	    } catch (Exception e) {
	    	return e.getMessage();
	    }
	}
}
