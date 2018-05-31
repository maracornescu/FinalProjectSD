package requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import model.ReservationModel;

public class PossibleRoutesRequest {
	
	private ObjectMapper mapper;
	
	public PossibleRoutesRequest() {
		mapper = new ObjectMapper();
	}
	
	public List<ReservationModel> getAllPossibleRoutes(String fromCity, String toCity, String date, int numberOfSeats, Long agencyId)  {
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();	
			
			String newToCity = "";
			if(toCity.contains(" ")) 
				newToCity = toCity.replace(" ", "%20");
			else
				newToCity = toCity;
			
			String newFromCity = "";
			if(fromCity.contains(" ")) 
				newFromCity = fromCity.replace(" ", "%20");
			else 
				newFromCity = fromCity;
				
			String request = "http://localhost:8080/travellerRoute?fromCity=" + newFromCity + "&toCity=" + newToCity + "&date=" + date + "&numberOfSeats=" + numberOfSeats;
			
			if(agencyId != null) {
				request += "&agencyId=" + agencyId;
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
			List<ReservationModel> possibleRoutes = mapper.readValue(jsonString, typeFactory.constructCollectionType(List.class, ReservationModel.class));
			
			httpClient.getConnectionManager().shutdown();
			
			return possibleRoutes;
			
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
		}
		return null;
	}

}
