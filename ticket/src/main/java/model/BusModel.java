package model;

public class BusModel {
	
	private Long busId;
	private String fromCity;
	private String toCity;
	private int numberOfSeats;
	private String date;
	private Long busAgencyId;
	
	public BusModel() {
		
	}
	
	public BusModel(Long busId) {
		this.busId = busId;
	}
	
	public BusModel(String fromCity, String toCity, String date, int numberOfSeats, Long busAgencyId) {
		this.setFromCity(fromCity);
		this.setToCity(toCity);
		this.setDate(date);
		this.setNumberOfSeats(numberOfSeats);
		this.setBusAgencyId(busAgencyId);
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

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getBusAgencyId() {
		return busAgencyId;
	}

	public void setBusAgencyId(Long busAgencyId) {
		this.busAgencyId = busAgencyId;
	}

}
