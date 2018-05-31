package business.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import business.model.StopsModel;
import repository.IStopsRepository;
import repository.dbmodel.StopsDto;

@Service
public class StopsService implements IStopsService{
	
	public final IStopsRepository stopsRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public StopsService(IStopsRepository stopsRepository) {
		this.stopsRepository = stopsRepository;
		modelMapper = new ModelMapper();
	}

	public List<StopsModel> getAllStops() {
		List<StopsModel> stopModel = new ArrayList<StopsModel>();
		List<StopsDto> stopDto = stopsRepository.findAll();
	
		for(StopsDto s: stopDto) {
			StopsModel stop = modelMapper.map(s, StopsModel.class);
			stopModel.add(stop);
		}
		
		return stopModel;
	}
	
	
	public StopsModel saveStop(StopsModel stop) {

		StopsDto stopDto = modelMapper.map(stop, StopsDto.class);
    	
        if (stopsRepository.findByStopName(stop.getStopName()) == null) {
        	stopsRepository.save(stopDto);
            return stop;
        }
        
        return null;
    }
	
    public void deleteStopById(Long stopId) {
    	stopsRepository.deleteById(stopId);   
    }
}
