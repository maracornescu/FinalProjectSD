package requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;

import model.TravellerModel;

public class RegisterTravellerRequest {
	
	private ObjectMapper mapper;
	
	public RegisterTravellerRequest() {
		mapper = new ObjectMapper();
	}
	
	public TravellerModel registerTraveller(TravellerModel traveller) {
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost("http://localhost:8080/registerTraveller");
			
			String jsonInString = mapper.writeValueAsString(traveller);
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
			return traveller;

		} catch (MalformedURLException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	 return null;
	}

}
