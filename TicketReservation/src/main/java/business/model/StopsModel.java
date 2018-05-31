package business.model;

public class StopsModel {

	private Long stopId;
	private String stopName;
	
	public StopsModel() {
		
	}
	
	public StopsModel(Long stopId) {
		this.stopId = stopId;
	}
	
	public StopsModel(String stopName) {
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
}
