package com.example.api.utils;

import java.util.HashMap;
import java.util.Map;

import com.example.api.client.RestClient;

import io.restassured.response.Response;

public class PayloadBuilder {
	
	
	public static Response createBookingPayload(String firstname, String lastname, String totalprice,
            String depositpaid, String checkin, String checkout,
            String additionalneeds) {
		Map<String, Object> payload = new HashMap<>();
		payload.put("firstname", firstname);
        payload.put("lastname", lastname);
        payload.put("totalprice", Integer.parseInt(totalprice));
        payload.put("depositpaid", Boolean.parseBoolean(depositpaid));

        // Nested map for booking dates
        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", checkin);
        bookingDates.put("checkout", checkout);

        // Add nested map to main payload
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", additionalneeds);
        
        Response response = RestClient.post("/booking",payload);
		return response;
				
	}

}
