package business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import business.model.BusModel;
import repository.IBusRepository;
import repository.dbmodel.BusAgencyDto;
import repository.dbmodel.BusDto;

@Service
public class BusService implements IBusService {
	
	public final IBusRepository busRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public BusService(IBusRepository busRepository) {
		this.busRepository = busRepository;
		modelMapper = new ModelMapper();
	}
	
	public List<BusModel> getAllBusses() {
		List<BusModel> busModel = new ArrayList<BusModel>();
		List<BusDto> busDto = busRepository.findAll();
	
		for(BusDto b: busDto) {
			BusModel bus = modelMapper.map(b, BusModel.class);
			busModel.add(bus);
		}
		
		return busModel;
	}
	
	public List<BusModel> getAllByAgency(Long agencyId) {
		List<BusModel> busModel = new ArrayList<BusModel>();
		
		BusAgencyDto busAgency = new BusAgencyDto();
		busAgency.setBusAgencyId(agencyId);
		List<BusDto> busDto = busRepository.findByBusAgency(busAgency);
	
		for(BusDto b: busDto) {
			BusModel bus = modelMapper.map(b, BusModel.class);
			busModel.add(bus);
		}
		
		return busModel;
	}
	
	public BusModel getBusById(Long busId) {
		Optional<BusDto> busDto = busRepository.findById(busId);
		BusModel b = modelMapper.map(busDto.get(), BusModel.class);
		return b;
	}
	
	public BusModel saveBus(BusModel bus) {

		BusDto busDto = modelMapper.map(bus, BusDto.class);
    	BusAgencyDto busAgency = new BusAgencyDto();
    	busAgency.setBusAgencyId(bus.getBusAgencyId());
		
        if (busRepository.findByFromCityAndToCityAndBusAgencyAndDate(bus.getFromCity(), bus.getToCity(), busAgency, bus.getDate()) == null) {
        	busRepository.save(busDto);
            return bus;
        }
        
        return null;
    }

    public BusModel updateBus(Long busId, BusModel bus) {
       
    	Optional<BusDto> updateBus = busRepository.findById(busId);

        if (updateBus != null) {
        	if(bus.getFromCity() != null)
        		updateBus.get().setFromCity(bus.getFromCity());
        	if(bus.getToCity() != null)	
        		updateBus.get().setToCity(bus.getToCity());
        	if(bus.getNumberOfSeats() != 0)	
        		updateBus.get().setNumberOfSeats(bus.getNumberOfSeats());
        	if(bus.getDate() != null)
        		updateBus.get().setDate(bus.getDate());
        	
        	busRepository.save(updateBus.get());
            
        	BusModel busModel = modelMapper.map(updateBus.get(), BusModel.class);
            
            return busModel;
            
        } else {
            return null;
        }
    }
    
    public void deleteBusById(Long busId) {
    	busRepository.deleteById(busId);   
    }
}
