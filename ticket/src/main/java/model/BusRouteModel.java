package model;

public class BusRouteModel {
	
	private Long busRouteId;
	private Long busId;
	private Long stopId;
	private Double distance;
	private String hour;
	private Double fare;
	
	public BusRouteModel() {
		
	}
	
	public BusRouteModel(Long busRouteId) {
		this.busRouteId = busRouteId;
	}
	
	public BusRouteModel(Long busId, Long stopId, Double distance, String hour, Double fare) {
		this.busId = busId;
		this.stopId = stopId;
		this.distance = distance;
		this.hour = hour;
		this.fare = fare;
	}
	
	public BusRouteModel(Long busId, Long stopId, String hour, Double fare) {
		this.busId = busId;
		this.stopId = stopId;
		this.hour = hour;
		this.fare = fare;
	}

	public Long getBusRouteId() {
		return busRouteId;
	}

	public void setBusRouteId(Long busRouteId) {
		this.busRouteId = busRouteId;
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

	public Long getBusId() {
		return busId;
	}

	public void setBusId(Long busId) {
		this.busId = busId;
	}

	public Long getStopId() {
		return stopId;
	}

	public void setStopId(Long stopId) {
		this.stopId = stopId;
	}
}
