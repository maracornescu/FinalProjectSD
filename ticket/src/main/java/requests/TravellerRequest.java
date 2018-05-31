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

import model.TravellerModel;

public class TravellerRequest {
	
	private ObjectMapper mapper;
	
	public TravellerRequest() {
		mapper = new ObjectMapper();
	}
	
	public List<TravellerModel> getAllTravellers(String keyword) {
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();	
			
			String request = "http://localhost:8080/traveller";
			if(keyword != null) {
				request += "?keyword=" + keyword;
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
			List<TravellerModel> travellers = mapper.readValue(jsonString, typeFactory.constructCollectionType(List.class, TravellerModel.class));
			
			httpClient.getConnectionManager().shutdown();
			
			return travellers;
			
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
		}
		return null;
	}
	
	public TravellerModel getTravellerById(Long travellerId) {
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();	
			HttpGet getRequest = new HttpGet("http://localhost:8080/traveller/" + travellerId);
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
			
			TravellerModel traveller = mapper.readValue(jsonString, TravellerModel.class);
			
			httpClient.getConnectionManager().shutdown();
			
			return traveller;
			
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
		}
		return null;
	}
	
	public TravellerModel saveTraveller(TravellerModel traveller) {
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost("http://localhost:8080/traveller");
			
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
	
	public TravellerModel updateTraveller(Long travellerId, TravellerModel traveller) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			
			String request = "http://localhost:8080/traveller/" + traveller + "?";
			
			if(traveller.getEmail() != null) {
				request += "email=" + traveller.getEmail();
			}
			if(traveller.getFirstName() != null) {
				request += "&firstName=" + traveller.getFirstName();
			}
			if(traveller.getLastName() != null) {
				request += "&lastName=" + traveller.getLastName();
			}
			if(traveller.getPassword() != null) {
				request += "&password=" + traveller.getPassword();
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
			
			TravellerModel updatedTraveller = mapper.readValue(answer, TravellerModel.class);
			
			httpClient.getConnectionManager().shutdown();
			return updatedTraveller;

		} catch (MalformedURLException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	 return null;
	}
	
	public String deleteTravellerById(Long travellerId) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
		
			HttpDelete deleteRequest = new HttpDelete("http://localhost:8080/traveller/" + travellerId);
			HttpResponse response = httpClient.execute(deleteRequest);
			
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
			}

			httpClient.getConnectionManager().shutdown();
			return "Traveller with id = " + travellerId + " was successfully deleted!";

		} catch (MalformedURLException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	 return null;
	}

}
