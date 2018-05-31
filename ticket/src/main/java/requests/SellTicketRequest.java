package requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;

import model.BusModel;
import model.SellTicketModel;

public class SellTicketRequest {
	
	private ObjectMapper mapper;
	
	public SellTicketRequest() {
		mapper = new ObjectMapper();
	}
	
	public BusModel sellTicket(SellTicketModel sellTicket) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			
			String newToCity = "";
			if(sellTicket.getToCity().contains(" ")) 
				newToCity = sellTicket.getToCity().replace(" ", "%20");
			else
				newToCity = sellTicket.getToCity();
			
			String newFromCity = "";
			if(sellTicket.getFromCity().contains(" ")) 
				newFromCity = sellTicket.getFromCity().replace(" ", "%20");
			else 
				newFromCity = sellTicket.getFromCity();
			
			String request = "http://localhost:8080/reservation?fromCity=" + newFromCity + "&toCity=" + newToCity +
					"&busAgency=" + sellTicket.getBusAgency() + "&date=" + sellTicket.getDate() + "&numberOfTickets=" + sellTicket.getNumberOfTickets();
			
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
			
			BusModel updatedTicket = mapper.readValue(answer, BusModel.class);
			
			httpClient.getConnectionManager().shutdown();
			return updatedTicket;

		} catch (MalformedURLException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	 return null;
	}

}
