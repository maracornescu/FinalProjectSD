package business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import business.Notification;
import business.model.TravellerModel;
import repository.ITravellerRepository;
import repository.dbmodel.TravellerDto;

@Service
public class TravellerService implements ITravellerService{
	
	public final ITravellerRepository trevellerRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public TravellerService(ITravellerRepository trevellerRepository) {
		this.trevellerRepository = trevellerRepository;
		modelMapper = new ModelMapper();
	}
	
	public List<TravellerModel> getAllTravellers() {
		List<TravellerModel> travellerModel = new ArrayList<TravellerModel>();
		List<TravellerDto> travellerDto = trevellerRepository.findAll();
	
		for(TravellerDto t: travellerDto) {
			TravellerModel traveller = modelMapper.map(t, TravellerModel.class);
			travellerModel.add(traveller);
		}
		
		return travellerModel;
	}
	
	public TravellerModel getTravellerById(Long travellerId) {
		Optional<TravellerDto> travellerDto = trevellerRepository.findById(travellerId);
		TravellerModel t = modelMapper.map(travellerDto.get(), TravellerModel.class);
		return t;
	}
	
	public List<TravellerModel> viewFilteredList(String keyword) {
		List<TravellerModel> travellerModel = new ArrayList<TravellerModel>();
		List<TravellerDto> travellerDto = trevellerRepository.findAll();
		
		for(TravellerDto l: travellerDto) {
			if(l.getFirstName().contains(keyword)) {
				TravellerModel traveller = modelMapper.map(l, TravellerModel.class);
				travellerModel.add(traveller);
			}
		}
		
		return travellerModel;
	}
	
	public TravellerModel saveTraveller(TravellerModel traveller) {

		TravellerDto travellerDto = modelMapper.map(traveller, TravellerDto.class);
    	
        if (trevellerRepository.findByEmail(traveller.getEmail()) == null) {
        	trevellerRepository.save(travellerDto);
        	
        	Notification.registrationMail(traveller.getEmail());
            return traveller;
        }
        
        return null;
    }

    public TravellerModel updateTraveller(Long travellerId, TravellerModel traveller) {
       
    	Optional<TravellerDto> updateTraveller = trevellerRepository.findById(travellerId);

        if (updateTraveller != null) {
        	if(traveller.getEmail() != null)
        		updateTraveller.get().setEmail(traveller.getEmail());
        	if(traveller.getFirstName() != null)	
        		updateTraveller.get().setFirstName(traveller.getFirstName());
        	if(traveller.getLastName() != null)
        		updateTraveller.get().setLastName(traveller.getLastName());
        	if(traveller.getPassword() != null)
        		updateTraveller.get().setPassword(traveller.getPassword());
        	
        	trevellerRepository.save(updateTraveller.get());
            
        	TravellerModel travellerModel = modelMapper.map(updateTraveller.get(), TravellerModel.class);
            
            return travellerModel;
            
        } else {
            return null;
        }
    }
    
    public void deleteTravellerById(Long travellerId) {
    	trevellerRepository.deleteById(travellerId);   
    }

	public TravellerModel login(String email, String password) {
		
		TravellerDto loginTraveller = trevellerRepository.findByEmail(email);
		
		if(loginTraveller != null) {
			if(loginTraveller.getPassword().equals(password)) {
				TravellerModel travellerModel = modelMapper.map(loginTraveller, TravellerModel.class);
				return travellerModel;
			}
		}
		
		return null;
	}

}
