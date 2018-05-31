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

import model.SellTicketModel;
import model.TicketModel;


public class TicketRequest {
	
	private ObjectMapper mapper;
	
	public TicketRequest() {
		mapper = new ObjectMapper();
	}
	
	/*
	public List<SellTicketModel> getAllTickets(Long travellerId) {
		
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();	
			String request = "http://localhost:8080/ticket?travellerId=" + travellerId;
					
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
			List<SellTicketModel> tickets = mapper.readValue(jsonString, typeFactory.constructCollectionType(List.class, SellTicketModel.class));
			
			httpClient.getConnectionManager().shutdown();
			
			return tickets;
			
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
		}
		return null;
	}*/
	
	
	public List<SellTicketModel> getAllTickets(Long busAgency, Long busId, Long travellerId) {
		
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();	
			
			String request = "http://localhost:8080/ticket?busAgency=";
			
			if(busId == null && busAgency == null) {
				request = "http://localhost:8080/ticket?travellerId=" + travellerId;
			}
			else if(busAgency != null && travellerId == null && busId == null) {
				request += "&busAgency=" + busAgency;
			}
			else if(busAgency != null && travellerId == null) {
				request += "&busAgency=" + busAgency + "&busId=" + busId;
			}
			else if(busAgency != null && busId == null) {
				request += "&busAgency=" + busAgency + "&travellerId=" + travellerId;
			}
			else {
				request += "&busAgency=" + busAgency + "&busId=" + busId + "&travellerId=" + travellerId;
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
			List<SellTicketModel> tickets = mapper.readValue(jsonString, typeFactory.constructCollectionType(List.class, SellTicketModel.class));
			
			httpClient.getConnectionManager().shutdown();
			
			return tickets;
			
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
		}
		return null;
	}
	
	
	public SellTicketModel saveTicket(SellTicketModel ticket) {
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			
			HttpPost postRequest = new HttpPost("http://localhost:8080/ticket");
			
			String jsonInString = mapper.writeValueAsString(ticket);
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
			return ticket;

		} catch (MalformedURLException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	 return null;
	}
	
	public String deleteTicketById(Long ticketId) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
		
			HttpDelete deleteRequest = new HttpDelete("http://localhost:8080/ticket/" + ticketId);
			HttpResponse response = httpClient.execute(deleteRequest);
			
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
			}

			httpClient.getConnectionManager().shutdown();
			return "Ticket with id = " + ticketId + " was successfully deleted!";

		} catch (MalformedURLException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	 return null;
	}
	
}
