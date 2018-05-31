package requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import model.BusAgencyModel;
import model.BusModel;

public class BusRequest {
	
	private ObjectMapper mapper;
	
	public BusRequest() {
		mapper = new ObjectMapper();
	}
	
	public List<BusModel> getAllBuses(Long agencyId) {
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();	
			
			String request = "http://localhost:8080/bus";
			if(agencyId != null) {
				request += "?agencyId=" + agencyId;
			}
			
			HttpGet getRequest = new HttpGet(request);
			getRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output;
			String jsonString = "";
		
			while ((output = br.readLine()) != null) {
				jsonString += output;
			}
			
			TypeFactory typeFactory = mapper.getTypeFactory();
			List<BusModel> buses = mapper.readValue(jsonString, typeFactory.constructCollectionType(List.class, BusModel.class));
			
			httpClient.getConnectionManager().shutdown();
			
			return buses;
			
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
		}
		return null;
	}
	
	public BusModel getBusById(Long busId) {
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();	
			HttpGet getRequest = new HttpGet("http://localhost:8080/bus/" + busId);
			getRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output;
			String jsonString = "";
			
			while ((output = br.readLine()) != null) {
				jsonString += output;
			}
			
			BusModel bus = mapper.readValue(jsonString, BusModel.class);
			
			httpClient.getConnectionManager().shutdown();
			
			return bus;
			
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
		}
		return null;
	}
	
	public BusModel saveBus(BusModel bus) {
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost("http://localhost:8080/bus");
			
			String jsonInString = mapper.writeValueAsString(bus);
			StringEntity input = new StringEntity(jsonInString);
			
			input.setContentType("application/json");
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(
	                        new InputStreamReader((response.getEntity().getContent())));

			String output;
			
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			httpClient.getConnectionManager().shutdown();
			return bus;

		} catch (MalformedURLException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	 return null;
	}
	
	public BusModel updateBus(Long busId, BusModel bus) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			
			String request = "http://localhost:8080/bus/" + busId + "?";
			
			if(bus.getNumberOfSeats() != 0) {
				request += "numberOfSeats=" + bus.getNumberOfSeats();
			}
			if(bus.getFromCity() != null) {
				request += "&fromCity=" + bus.getFromCity();
			}
			if(bus.getToCity() != null) {
				request += "&toCity=" + bus.getToCity();
			}
			if(bus.getDate() != null) {
				request += "&date=" + bus.getDate();
			}
			
			HttpPut putRequest = new HttpPut(request);
			HttpResponse response = httpClient.execute(putRequest);
			
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(
	                        new InputStreamReader((response.getEntity().getContent())));

			String output;
			String answer = "";
			
			while ((output = br.readLine()) != null) {
				answer += output;
			}
			
			BusModel updatedBus = mapper.readValue(answer, BusModel.class);
			
			httpClient.getConnectionManager().shutdown();
			return updatedBus;

		} catch (MalformedURLException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	 return null;
	}
	
	public String deleteBusById(Long busId) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
		
			HttpDelete deleteRequest = new HttpDelete("http://localhost:8080/bus/" + busId);
			HttpResponse response = httpClient.execute(deleteRequest);
			
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
			}

			httpClient.getConnectionManager().shutdown();
			return "Bus with id = " + busId + " was successfully deleted!";

		} catch (MalformedURLException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	 return null;
	}


}
