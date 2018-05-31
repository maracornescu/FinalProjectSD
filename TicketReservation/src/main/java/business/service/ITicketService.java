package business.service;

import java.util.List;

import business.model.BusModel;
import business.model.ReservationModel;
import business.model.SellTicketModel;
import business.model.TicketModel;

public interface ITicketService {
	
	List<ReservationModel> findAllPossibleRoutes(String fromCity, String toCity, String date, int numberOfSeats);
	List<ReservationModel> findAllPossibleRoutesForAgency(String fromCity, String toCity, String date, int numberOfSeats, Long agencyId);
	BusModel sellTicket(SellTicketModel sellTicket);
	TicketModel saveTicket(SellTicketModel sellTicket);
	void deleteTicketById(Long ticketId);
	List<SellTicketModel> getAllTicketsByTraveller(Long travellerId);
	List<SellTicketModel> getAllTicketsByBus(Long busId);
	SellTicketModel getAllTicketsByBusAndTraveller(Long busId, Long travellerId);
	List<SellTicketModel> getAllTickets(Long busAgency);
}
