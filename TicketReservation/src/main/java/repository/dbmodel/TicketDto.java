package repository.dbmodel;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tickets")
public class TicketDto implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ticket_id")
	private Long ticketId;
	
	@ManyToOne
	@JoinColumn(name = "traveller_id")
	private TravellerDto traveller;
	
	@ManyToOne
	@JoinColumn(name = "bus_id")
	private BusDto bus;
	
	@Column(name = "from_city")
	private String fromCity;
	
	@Column(name = "to_city")
	private String toCity;
	
	@Column(name = "number_of_tickets")
	private int numberOfTickets;
	
	@Column(name = "departure_time")
	private Date departureTime;
	
	@Column(name = "arrival_time")
	private Date arrivalTime;

	public TicketDto() {
		
	}
	
	public TicketDto(TravellerDto traveller, BusDto bus, String fromCity, String toCity, int numberOfTickets, Date departureTime) {
		this.setTraveller(traveller);
		this.setBus(bus);
		this.setFromCity(fromCity);
		this.setToCity(toCity);
		this.setNumberOfTickets(numberOfTickets);
		this.setDepartureTime(departureTime);
	}
	
	public TicketDto(BusDto bus, String fromCity, String toCity, int numberOfTickets, Date departureTime, Date arrivalTime) {
		this.setTraveller(traveller);
		this.setBus(bus);
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

	public TravellerDto getTraveller() {
		return traveller;
	}

	public void setTraveller(TravellerDto traveller) {
		this.traveller = traveller;
	}

	public BusDto getBus() {
		return bus;
	}

	public void setBus(BusDto bus) {
		this.bus = bus;
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
}
