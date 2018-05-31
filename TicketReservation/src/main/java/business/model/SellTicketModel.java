package business.model;

public class SellTicketModel {
	
	private Long ticketId;
	private String fromCity;
	private String toCity;
	private String busAgency;
	private String date;
	private int numberOfTickets;
	private String travellerEmail;
	
	public SellTicketModel() {
		
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

	public String getBusAgency() {
		return busAgency;
	}

	public void setBusAgency(String busAgency) {
		this.busAgency = busAgency;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getNumberOfTickets() {
		return numberOfTickets;
	}

	public void setNumberOfTickets(int numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}

	public String getTravellerEmail() {
		return travellerEmail;
	}

	public void setTravellerEmail(String travellerEmail) {
		this.travellerEmail = travellerEmail;
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
}
