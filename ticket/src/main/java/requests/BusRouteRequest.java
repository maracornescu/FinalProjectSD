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
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import model.BusAgencyModel;
import model.BusRouteModel;

public class BusRouteRequest {
	
	private ObjectMapper mapper;
	
	public BusRouteRequest() {
		mapper = new ObjectMapper();
	}
	
	public BusRouteModel saveRoute(BusRouteModel route) {
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost("http://localhost:8080/route");
			
			String jsonInString = mapper.writeValueAsString(route);
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
			return route;

		} catch (MalformedURLException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	 return null;
	}
	
	public List<BusRouteModel> getAllRoutes(Long busId, Long stopId) {
		
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();	
			
			String request = "http://localhost:8080/route";
			
			if(busId == null && stopId == null) {
				request = "http://localhost:8080/route";
			}
			else if(busId == null) {
				request += "?stopId=" + stopId;
			}
			else if(stopId == null) {
				request += "?busId=" + busId;
			}
			else {
				request += "?busId=" + busId + "&stopId=" + stopId;
			}
			
			System.out.println(request);
			
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
			//List<BusRouteModel> attendances = mapper.readValue(jsonString, typeFactory.constructCollectionType(List.class, BusRouteModel.class));
			List<BusRouteModel> busses = mapper.readValue(jsonString, typeFactory.constructCollectionType(List.class, BusRouteModel.class));
			
			
			httpClient.getConnectionManager().shutdown();
			
			return busses;
			
			} catch (ClientProtocolException e) {
				//e.printStackTrace();
			} catch (IOException e) {
				//e.printStackTrace();
		}
		return null;
	}
	
	public String deleteBusRouteById(Long busRouteId) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
		
			HttpDelete deleteRequest = new HttpDelete("http://localhost:8080/route/" + busRouteId);
			HttpResponse response = httpClient.execute(deleteRequest);
			
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
			}

			httpClient.getConnectionManager().shutdown();
			return "Route with id = " + busRouteId + " was successfully deleted!";

		} catch (MalformedURLException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	 return null;
	}
}
