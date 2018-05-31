package repository.dbmodel;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "stops")
public class StopsDto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "stop_id")
	private Long stopId;
	
	@Column(name = "station_name")
	private String stopName;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "stop")
	private Set<BusRouteDto> route;
	
	public StopsDto(Long stopId) {
		this.stopId = stopId;
	}
	
	public StopsDto() {
		
	}

	public StopsDto(String stopName) {
		this.setStopName(stopName);
	}

	public Long getStopId() {
		return stopId;
	}

	public void setStopId(Long stopId) {
		this.stopId = stopId;
	}

	public String getStopName() {
		return stopName;
	}

	public void setStopName(String stopName) {
		this.stopName = stopName;
	}

	public Set<BusRouteDto> getRoute() {
		return route;
	}

	public void setRoute(Set<BusRouteDto> route) {
		this.route = route;
	}
	
}
