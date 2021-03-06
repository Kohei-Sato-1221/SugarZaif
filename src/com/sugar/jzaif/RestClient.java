package com.sugar.jzaif;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class RestClient {
	private static String apiKey = "";
	
	/*　参考：http://www.techscore.com/blog/2016/09/20/jersey-client-api/　*/
	public static String get(String subject, String content) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("https://script.google.com")
			    .path("/macros/s/AKfycbwPXXdYUU00dkylvvfOveP1RXHyuI78FNTdV_da0MWingkL_No/exec")
			    .queryParam("key", apiKey)
			    .queryParam("subject", subject)
			    .queryParam("content", content);
			 
		
			String result;
			try {
			    result = target.request().get(String.class);
			} catch (Exception e) {
				result = "error: response=" + e.getMessage();
			    System.out.println("error: response=" + e.getMessage());
			    throw e;
			}
		return result;
	}
	
	public static void setApiKey(String apiKey) {
		RestClient.apiKey = apiKey;
	}
}
