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

public class BusAgencyRequest {
	
	private ObjectMapper mapper;
	
	public BusAgencyRequest() {
		mapper = new ObjectMapper();
	}
	
	public List<BusAgencyModel> getAllBusAgencies() {
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();	
			
			HttpGet getRequest = new HttpGet("http://localhost:8080/agency");
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
			List<BusAgencyModel> agencies = mapper.readValue(jsonString, typeFactory.constructCollectionType(List.class, BusAgencyModel.class));
			
			httpClient.getConnectionManager().shutdown();
			
			return agencies;
			
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
		}
		return null;
	}
	
	public BusAgencyModel getBusAgencyById(Long agencyId) {
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();	
			HttpGet getRequest = new HttpGet("http://localhost:8080/agency/" + agencyId);
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
			
			BusAgencyModel agency = mapper.readValue(jsonString, BusAgencyModel.class);
			
			httpClient.getConnectionManager().shutdown();
			
			return agency;
			
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
		}
		return null;
	}
	
	public BusAgencyModel saveAgency(BusAgencyModel agency) {
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost("http://localhost:8080/agency");
			
			String jsonInString = mapper.writeValueAsString(agency);
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
			return agency;

		} catch (MalformedURLException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	 return null;
	}
	
	public BusAgencyModel updateAgency(Long agencyId, BusAgencyModel agency) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			
			String request = "http://localhost:8080/agency/" + agencyId + "?";
			
			if(agency.getEmail() != null) {
				request += "email=" + agency.getEmail();
			}
			if(agency.getName() != null) {
				request += "&name=" + agency.getName();
			}
			if(agency.getPassword() != null) {
				request += "&password=" + agency.getPassword();
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
			
			BusAgencyModel updatedAgency = mapper.readValue(answer, BusAgencyModel.class);
			
			httpClient.getConnectionManager().shutdown();
			return updatedAgency;

		} catch (MalformedURLException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	 return null;
	}
	
	public String deleteAgencyById(Long agencyId) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
		
			HttpDelete deleteRequest = new HttpDelete("http://localhost:8080/agency/" + agencyId);
			HttpResponse response = httpClient.execute(deleteRequest);
			
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
			}

			httpClient.getConnectionManager().shutdown();
			return "Agency with id = " + agencyId + " was successfully deleted!";

		} catch (MalformedURLException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	 return null;
	}


}
