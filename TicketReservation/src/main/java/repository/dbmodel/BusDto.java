package repository.dbmodel;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bus")
public class BusDto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bus_id")
	private Long busId;
	
	@Column(name = "from_city")
	private String fromCity;
	
	@Column(name = "to_city")
	private String toCity;
		
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bus")
	private Set<BusRouteDto> route;
	
	@Column(name = "number_of_seats")
	private int numberOfSeats;
	
	@Column(name = "date")
	private String date;
	
	@ManyToOne
	@JoinColumn(name = "bus_agency_id")
	private BusAgencyDto busAgency;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bus")
	private Set<TicketDto> tickets;
	
	public BusDto() {
		
	}
	
	public BusDto(Long busId) {
		this.busId = busId;
	}
	
	public BusDto(String fromCity, String toCity, String date, int numberOfSeats, BusAgencyDto busAgency) {
		this.setFromCity(fromCity);
		this.setToCity(toCity);
		this.setDate(date);
		this.setNumberOfSeats(numberOfSeats);
		this.setBusAgency(busAgency);
	}

	public Long getBusId() {
		return busId;
	}

	public void setBusId(Long busId) {
		this.busId = busId;
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

	public BusAgencyDto getBusAgency() {
		return busAgency;
	}

	public void setBusAgency(BusAgencyDto busAgency) {
		this.busAgency = busAgency;
	}

	public Set<TicketDto> getTickets() {
		return tickets;
	}

	public void setTickets(Set<TicketDto> tickets) {
		this.tickets = tickets;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public Set<BusRouteDto> getRoute() {
		return route;
	}

	public void setRoute(Set<BusRouteDto> route) {
		this.route = route;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
