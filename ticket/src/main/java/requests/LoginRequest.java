package requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;

import model.AdminModel;
import model.BusAgencyModel;
import model.TravellerModel;
import model.UserModel;

public class LoginRequest {
	
	public UserModel login(String email, String password) {
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			String request = "http://localhost:8080/login?email=" + email + "&password=" + password;
			
			HttpGet getRequest = new HttpGet(request);
			
			getRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output;
			String answer = "";

			while ((output = br.readLine()) != null) {
				answer += output;
			}
			
			ObjectMapper mapper = new ObjectMapper();
			TravellerModel traveller;
			BusAgencyModel agency;
			AdminModel admin;
						
			
			if(answer.contains("firstName")) {
				traveller = mapper.readValue(answer, TravellerModel.class);
				return traveller;
			}
			else if (answer.contains("name")) {
				agency = mapper.readValue(answer, BusAgencyModel.class);
				return agency;
			}
			else {
				admin = mapper.readValue(answer, AdminModel.class);
				return admin;
			}
			
			
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return null;
	}
}