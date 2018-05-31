package business.model;

import java.util.Date;

public class TicketModel {

	private Long ticketId;
	private Long travellerId;
	private Long busId;
	private String fromCity;
	private String toCity;
	private int numberOfTickets;
	private Date departureTime;
	private Date arrivalTime;

	public TicketModel() {
		
	}
	
	public TicketModel(Long busId, Long travellerId, String fromCity, String toCity, int numberOfTickets, Date departureTime) {
		this.setBusId(busId);
		this.setTravellerId(travellerId);
		this.setFromCity(fromCity);
		this.setToCity(toCity);
		this.setNumberOfTickets(numberOfTickets);
		this.setDepartureTime(departureTime);
	}
	
	public TicketModel(Long busId, String fromCity, String toCity, int numberOfTickets, Date departureTime, Date arrivalTime) {
		this.setBusId(busId);
		this.setTravellerId(travellerId);
		this.setFromCity(fromCity);
		this.setToCity(toCity);
		this.setNumberOfTickets(numberOfTickets);
		this.setDepartureTime(departureTime);
		this.setArrivalTime(arrivalTime);
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public int getNumberOfTickets() {
		return numberOfTickets;
	}

	public void setNumberOfTickets(int numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Long getTravellerId() {
		return travellerId;
	}

	public void setTravellerId(Long travellerId) {
		this.travellerId = travellerId;
	}

	public Long getBusId() {
		return busId;
	}

	public void setBusId(Long busId) {
		this.busId = busId;
	}

}
