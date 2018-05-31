package model;

public class ReservationModel {
	
	private String departureCity;
	private String arrivalCity;
	private String busAgency;
	private String fromCity;
	private String toCity;
	private String departureTime;
	private String arrivalTime;
	private String date;
	private Double price;
	
	public ReservationModel() {
		
	}
	
	public ReservationModel(String fromCity, String toCity, String departureTime, String arrivalTime, Double price) {
		this.fromCity = fromCity;
		this.toCity = toCity;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.price = price;
	}
	
	public ReservationModel(String departureCity, String arrivalCity, String busAgency,
			String fromCity, String toCity, String departureTime, String arrivalTime, String date, Double price) {
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
		this.busAgency = busAgency;
		this.fromCity = fromCity;
		this.toCity = toCity;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.date = date;
		this.price = price;
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

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}

	public String getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
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
	
	
}
