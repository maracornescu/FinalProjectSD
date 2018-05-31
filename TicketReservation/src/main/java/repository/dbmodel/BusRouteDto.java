package repository.dbmodel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "route")
public class BusRouteDto implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "route_id")
	private Long busRouteId;
	
	@ManyToOne
	@JoinColumn(name = "bus_id")
	private BusDto bus;
	
	@ManyToOne
	@JoinColumn(name = "stop_id")
	private StopsDto stop;
	
	@Column(name = "distance")
	private Double distance;
	
	@Column(name = "hour")
	private String hour;
	
	@Column(name = "fare")
	private Double fare;
	
	public BusRouteDto() {
		
	}
	
	public BusRouteDto(Long busRouteId) {
		this.busRouteId = busRouteId;
	}
	
	public BusRouteDto(BusDto bus, StopsDto stop, Double distance, String hour, Double fare) {
		this.bus = bus;
		this.stop = stop;
		this.distance = distance;
		this.hour = hour;
		this.fare = fare;
	}

	public Long getBusRouteId() {
		return busRouteId;
	}

	public void setBusRouteId(Long busRouteId) {
		this.busRouteId = busRouteId;
	}

	public BusDto getBus() {
		return bus;
	}

	public void setBus(BusDto bus) {
		this.bus = bus;
	}

	public StopsDto getStop() {
		return stop;
	}

	public void setStop(StopsDto stop) {
		this.stop = stop;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public Double getFare() {
		return fare;
	}

	public void setFare(Double fare) {
		this.fare = fare;
	}
}
