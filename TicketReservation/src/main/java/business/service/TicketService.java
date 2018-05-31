package business.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import business.Notification;
import business.model.BusAgencyModel;
import business.model.BusModel;
import business.model.ReservationModel;
import business.model.SellTicketModel;
import business.model.TicketModel;
import repository.IBusAgencyRepository;
import repository.IBusRepository;
import repository.IBusRouteRepository;
import repository.IStopsRepository;
import repository.ITicketRepository;
import repository.ITravellerRepository;
import repository.dbmodel.BusAgencyDto;
import repository.dbmodel.BusDto;
import repository.dbmodel.BusRouteDto;
import repository.dbmodel.StopsDto;
import repository.dbmodel.TicketDto;
import repository.dbmodel.TravellerDto;

@Service
public class TicketService implements ITicketService{
	
	public final ITicketRepository ticketRepository;
	public final IBusRepository busRepository;
	public final IBusRouteRepository routeRepository;
	public final IStopsRepository stopsRepository;
	public final ITravellerRepository travellerRepository;
	public final IBusAgencyRepository busAgencyRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public TicketService(ITicketRepository ticketRepository, IBusRepository busRepository, IBusRouteRepository routeRepository,
			IStopsRepository stopsRepository, ITravellerRepository travellerRepository, IBusAgencyRepository busAgencyRepository) {
		this.ticketRepository = ticketRepository;
		this.busRepository = busRepository;
		this.routeRepository = routeRepository;
		this.stopsRepository = stopsRepository;
		this.travellerRepository = travellerRepository;
		this.busAgencyRepository = busAgencyRepository;
		modelMapper = new ModelMapper();
	}
	
	
	public List<ReservationModel> findAllPossibleRoutes(String fromCity, String toCity, String date, int numberOfSeats) {
		
		List<BusDto> busses = busRepository.findByDate(date);
	
		StopsDto stop1 = stopsRepository.findByStopName(fromCity);
		StopsDto stop2 = stopsRepository.findByStopName(toCity);
		
		String currentDate = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
		
		List<ReservationModel> routes = new ArrayList<ReservationModel>(); 
		
		for(BusDto b: busses) {
			
			if((b.getNumberOfSeats() - numberOfSeats  > 0)  && (b.getDate().compareTo(currentDate) >= 0)) {
			
				BusRouteDto route1 = routeRepository.findByStopAndBus(stop1, b);
				BusRouteDto route2 = routeRepository.findByStopAndBus(stop2, b);
			
				if(route1 != null && route2 != null) {
					Double fare1 = route1.getFare();
					Double fare2 = route2.getFare();
					Double fare = fare2 - fare1;
					if(fare >= 0) {
						String departureCity =  b.getFromCity();
						String arrivalCity = b.getToCity();
						ReservationModel r = new ReservationModel(departureCity, arrivalCity, b.getBusAgency().getName(), 
								fromCity, toCity, route1.getHour(), route2.getHour(), b.getDate(), fare);
						routes.add(r);
					}
				}
			}
		}
		return routes;
	}
	
	public List<ReservationModel> findAllPossibleRoutesForAgency(String fromCity, String toCity, String date, int numberOfSeats, Long agencyId) {
		
		Optional<BusAgencyDto> busAgency = busAgencyRepository.findById(agencyId);
		List<BusDto> busses = busRepository.findByDateAndBusAgency(date, busAgency.get());
	
		StopsDto stop1 = stopsRepository.findByStopName(fromCity);
		StopsDto stop2 = stopsRepository.findByStopName(toCity);
		
		List<ReservationModel> routes = new ArrayList<ReservationModel>(); 
		
		String currentDate = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
		
		for(BusDto b: busses) {
			
			if((b.getNumberOfSeats() - numberOfSeats  > 0) && (b.getDate().compareTo(currentDate) >= 0)) {
			
				BusRouteDto route1 = routeRepository.findByStopAndBus(stop1, b);
				BusRouteDto route2 = routeRepository.findByStopAndBus(stop2, b);
			
				if(route1 != null && route2 != null) {
					Double fare1 = route1.getFare();
					Double fare2 = route2.getFare();
					Double fare = fare2 - fare1;
					if(fare >= 0) {
						String departureCity =  b.getFromCity();
						String arrivalCity = b.getToCity();
						ReservationModel r = new ReservationModel(departureCity, arrivalCity, b.getBusAgency().getName(), 
								fromCity, toCity, route1.getHour(), route2.getHour(), b.getDate(), fare);
						routes.add(r);
					}
				}
			}
		}
		return routes;
	}
	
	/*
	public BusModel sellTicket(String fromCity, String toCity, String busAgency, String date, int numberOfTickets) {
		
		BusAgencyDto busAgencyDto = busAgencyRepository.findByName(busAgency);
		BusDto bus = busRepository.findByFromCityAndToCityAndBusAgencyAndDate(fromCity, toCity, busAgencyDto, date);
		
		if(bus.getNumberOfSeats() - numberOfTickets > 0) {
			
			BusDto updatedBus = new BusDto();
			updatedBus.setBusId(bus.getBusId());;
			updatedBus.setFromCity(bus.getFromCity());
			updatedBus.setToCity(bus.getToCity());
			updatedBus.setNumberOfSeats(bus.getNumberOfSeats() - numberOfTickets);
			updatedBus.setDate(bus.getDate());
			updatedBus.setBusAgency(bus.getBusAgency());
			
			busRepository.save(updatedBus);
            
        	BusModel busModel = modelMapper.map(updatedBus, BusModel.class);
            
            return busModel;
		}
		return null;
	}*/
	
	public BusModel sellTicket(SellTicketModel sellTicket) {
		
		BusAgencyDto busAgencyDto = busAgencyRepository.findByName(sellTicket.getBusAgency());
		BusDto bus = busRepository.findByFromCityAndToCityAndBusAgencyAndDate(sellTicket.getFromCity(), sellTicket.getToCity(), busAgencyDto, sellTicket.getDate());
		
		if(bus.getNumberOfSeats() - sellTicket.getNumberOfTickets() > 0) {
			
			BusDto updatedBus = new BusDto();
			updatedBus.setBusId(bus.getBusId());;
			updatedBus.setFromCity(bus.getFromCity());
			updatedBus.setToCity(bus.getToCity());
			updatedBus.setNumberOfSeats(bus.getNumberOfSeats() - sellTicket.getNumberOfTickets());
			updatedBus.setDate(bus.getDate());
			updatedBus.setBusAgency(bus.getBusAgency());
			
			//System.out.println(sellTicket.getTravellerEmail());
			Notification.notifyReservation("mara.cornescu@gmail.com", sellTicket);
			busRepository.save(updatedBus);
			
        	BusModel busModel = modelMapper.map(updatedBus, BusModel.class);
            
            return busModel;
		}
		return null;
	}
	
	public TicketModel saveTicket(SellTicketModel sellTicket) {
		
		BusAgencyDto busAgencyDto = busAgencyRepository.findByName(sellTicket.getBusAgency());
		BusDto bus = busRepository.findByFromCityAndToCityAndBusAgencyAndDate(sellTicket.getFromCity(), sellTicket.getToCity(), busAgencyDto, sellTicket.getDate());
		TravellerDto traveller = travellerRepository.findByEmail(sellTicket.getTravellerEmail());
		TicketDto ticket = new TicketDto();
		
		if(bus.getNumberOfSeats() - sellTicket.getNumberOfTickets() > 0) {
			
			ticket.setFromCity(sellTicket.getFromCity());
			ticket.setToCity(sellTicket.getToCity());
			ticket.setNumberOfTickets(sellTicket.getNumberOfTickets());
			ticket.setBus(bus);
			ticket.setTraveller(traveller);
			
			ticketRepository.save(ticket);
            
			TicketModel ticketModel = modelMapper.map(ticket, TicketModel.class);
            
            return ticketModel;
		}
		return null;
	}
	
	/*
	public TicketModel saveTicket(TicketModel ticket) {

		TicketDto ticketDto = modelMapper.map(ticket, TicketDto.class);
    	
        //if (ticketRepository.) == null) {
		ticketRepository.save(ticketDto);
        	
        	//Notification.sendToken(studentDto.getToken(), studentDto.getEmail());
        return ticket;
        //}
    }*/
	
    public void deleteTicketById(Long ticketId) {
    	
    	Optional<TicketDto> ticket = ticketRepository.findById(ticketId);
    	BusDto busDto = ticket.get().getBus();
    	busDto.setNumberOfSeats(busDto.getNumberOfSeats() + ticket.get().getNumberOfTickets());
    	busRepository.save(busDto);
    	ticketRepository.deleteById(ticketId);   
    }
    
	public List<SellTicketModel> getAllTickets(Long busAgency) {
		List<SellTicketModel> ticketModel = new ArrayList<SellTicketModel>();
		List<TicketDto> ticketDto = ticketRepository.findAll();
		
		for(TicketDto a: ticketDto) {
			BusDto bus = a.getBus();
			if(bus.getBusAgency().getBusAgencyId() == busAgency) {
				SellTicketModel ticket = new SellTicketModel();
				ticket.setTicketId(a.getTicketId());
				ticket.setFromCity(a.getFromCity());
				ticket.setToCity(a.getToCity());
				ticket.setNumberOfTickets(a.getNumberOfTickets());
				ticket.setBusAgency(a.getBus().getBusAgency().getName());
				ticketModel.add(ticket);
			}
		}
		
		return ticketModel;
	}
    
    /*
	public List<TicketModel> getAllTickets(Long busAgency) {
		List<TicketModel> ticketModel = new ArrayList<TicketModel>();
		List<TicketDto> ticketDto = ticketRepository.findAll();
	
		for(TicketDto a: ticketDto) {
			BusDto bus = a.getBus();
			if(bus.getBusAgency().getBusAgencyId() == busAgency) {
				TicketModel ticket = modelMapper.map(a, TicketModel.class);
				ticketModel.add(ticket);
			}
		}
		
		return ticketModel;
	}*/
	
	/*
	public List<TicketModel> getAllTicketsByBus(Long busId) {
		List<TicketModel> ticketModel = new ArrayList<TicketModel>();
		
		BusDto busDto = new BusDto(busId);
		
		List<TicketDto> ticketDto = ticketRepository.findByBus(busDto);
	
		for(TicketDto a: ticketDto) {
			TicketModel ticket = modelMapper.map(a, TicketModel.class);
			ticketModel.add(ticket);
		}
		
		return ticketModel;
	}*/
	
	public List<SellTicketModel> getAllTicketsByBus(Long busId) {
		List<SellTicketModel> ticketModel = new ArrayList<SellTicketModel>();
		BusDto busDto = new BusDto(busId);
		
		List<TicketDto> ticketDto = ticketRepository.findByBus(busDto);
		
		for(TicketDto a: ticketDto) {
			SellTicketModel ticket = new SellTicketModel();
			ticket.setTicketId(a.getTicketId());
			ticket.setFromCity(a.getFromCity());
			ticket.setToCity(a.getToCity());
			ticket.setNumberOfTickets(a.getNumberOfTickets());
			ticket.setBusAgency(a.getBus().getBusAgency().getName());
			ticketModel.add(ticket);
		}
		
		return ticketModel;
	}
	
	public List<SellTicketModel> getAllTicketsByTraveller(Long travellerId) {
		List<SellTicketModel> ticketModel = new ArrayList<SellTicketModel>();
		
		TravellerDto travellerDto = new TravellerDto();
		travellerDto.setTravellerId(travellerId);
		
		List<TicketDto> ticketDto = ticketRepository.findByTraveller(travellerDto);
		
		for(TicketDto a: ticketDto) {
			SellTicketModel ticket = new SellTicketModel();
			ticket.setTicketId(a.getTicketId());
			ticket.setFromCity(a.getFromCity());
			ticket.setToCity(a.getToCity());
			ticket.setNumberOfTickets(a.getNumberOfTickets());
			ticket.setBusAgency(a.getBus().getBusAgency().getName());
			ticketModel.add(ticket);
		}
		
		return ticketModel;
	}
	
	public SellTicketModel getAllTicketsByBusAndTraveller(Long busId, Long travellerId) {
		BusDto busDto = new BusDto(busId);
		TravellerDto travellerDto = new TravellerDto();
		travellerDto.setTravellerId(travellerId);
		
		TicketDto ticketDto = ticketRepository.findByBusAndTraveller(busDto, travellerDto);
		
		SellTicketModel ticket = new SellTicketModel();
		ticket.setTicketId(ticketDto.getTicketId());
		ticket.setFromCity(ticketDto.getFromCity());
		ticket.setToCity(ticketDto.getToCity());
		ticket.setNumberOfTickets(ticketDto.getNumberOfTickets());
		ticket.setBusAgency(ticketDto.getBus().getBusAgency().getName());

		return ticket;
	}
	
	/*
	public List<TicketModel> getAllTicketsByTraveller(Long travellerId) {
		List<TicketModel> ticketModel = new ArrayList<TicketModel>();
		
		TravellerDto travellerDto = new TravellerDto();
		travellerDto.setTravellerId(travellerId);
		
		List<TicketDto> ticketDto = ticketRepository.findByTraveller(travellerDto);
	
		for(TicketDto a: ticketDto) {
			TicketModel ticket = modelMapper.map(a, TicketModel.class);
			ticketModel.add(ticket);
		}
		
		return ticketModel;
	}*/
	
	/*
	public TicketModel getAllTicketsByBusAndTraveller(Long busId, Long travellerId) {
		BusDto busDto = new BusDto(busId);
		TravellerDto travellerDto = new TravellerDto();
		travellerDto.setTravellerId(travellerId);
		
		TicketDto ticketDto = ticketRepository.findByBusAndTraveller(busDto, travellerDto);
	
		TicketModel ticket = modelMapper.map(ticketDto, TicketModel.class);
		
		return ticket;
	}*/

}
