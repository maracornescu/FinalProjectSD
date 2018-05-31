package business.service;

import java.util.List;

import business.model.TravellerModel;

public interface ITravellerService {
	
	List<TravellerModel> getAllTravellers();
	
	TravellerModel getTravellerById(Long travellerId);
	
	TravellerModel saveTraveller(TravellerModel traveller);

    TravellerModel updateTraveller(Long travellerId, TravellerModel traveller);
    
    void deleteTravellerById(Long travellerId);

	TravellerModel login(String email, String password);
	
	List<TravellerModel> viewFilteredList(String keyword);

}
