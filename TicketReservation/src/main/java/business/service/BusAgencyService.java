package business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import business.Notification;
import business.model.BusAgencyModel;
import repository.IBusAgencyRepository;
import repository.dbmodel.BusAgencyDto;

@Service
public class BusAgencyService implements IBusAgencyService {

	public final IBusAgencyRepository busAgencyRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public BusAgencyService(IBusAgencyRepository busAgencyRepository) {
		this.busAgencyRepository = busAgencyRepository;
		modelMapper = new ModelMapper();
	}
	
	public List<BusAgencyModel> getAllAgencies() {
		List<BusAgencyModel> agencyModel = new ArrayList<BusAgencyModel>();
		List<BusAgencyDto> agencyDto = busAgencyRepository.findAll();
	
		for(BusAgencyDto a: agencyDto) {
			BusAgencyModel agency = modelMapper.map(a, BusAgencyModel.class);
			agencyModel.add(agency);
		}
		
		return agencyModel;
	}
	
	public BusAgencyModel getAgencyById(Long agencyId) {
		Optional<BusAgencyDto> agencyDto = busAgencyRepository.findById(agencyId);
		BusAgencyModel a = modelMapper.map(agencyDto.get(), BusAgencyModel.class);
		return a;
	}
	
	public BusAgencyModel saveAgency(BusAgencyModel agency) {

		BusAgencyDto agencyDto = modelMapper.map(agency, BusAgencyDto.class);
    	
        if (busAgencyRepository.findByEmail(agency.getEmail()) == null) {
        	busAgencyRepository.save(agencyDto);
        	
        	Notification.registrationMail(agency.getEmail());
            return agency;
        }
        
        return null;
    }

    public BusAgencyModel updateAgency(Long agencyId, BusAgencyModel agency) {
       
    	Optional<BusAgencyDto> updateAgency = busAgencyRepository.findById(agencyId);

        if (updateAgency != null) {
        	if(agency.getName() != null)
        		updateAgency.get().setName(agency.getName());
        	if(agency.getEmail() != null)	
        		updateAgency.get().setEmail(agency.getEmail());
        	if(agency.getPassword() != null)
        		updateAgency.get().setPassword(agency.getPassword());
        	
        	busAgencyRepository.save(updateAgency.get());
            
        	BusAgencyModel agencyModel = modelMapper.map(updateAgency.get(), BusAgencyModel.class);
            
            return agencyModel;
            
        } else {
            return null;
        }
    }
    
    public void deleteAgencyById(Long agencyId) {
    	busAgencyRepository.deleteById(agencyId);   
    }

	public BusAgencyModel login(String email, String password) {
		
		BusAgencyDto loginAgency = busAgencyRepository.findByEmail(email);
		
		if(loginAgency != null) {
			if(loginAgency.getPassword().equals(password)) {
				BusAgencyModel agencyModel = modelMapper.map(loginAgency, BusAgencyModel.class);
				return agencyModel;
			}
		}
		
		return null;
	}	

}
