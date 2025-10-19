package com.example.api.tests;

import com.example.api.annotations.CSVFile;
import com.example.api.base.BaseTest;
import com.example.api.client.RestClient;
import com.example.api.pojo.Booking;
import com.example.api.pojo.BookingDates;
import com.example.api.pojo.User;
import com.example.api.utils.CSVUtils;
import com.example.api.utils.PayloadBuilder;
import com.opencsv.exceptions.CsvValidationException;

import dataproviders.TestDataProvider;
import io.restassured.response.Response;

import java.util.Collection;
import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateBookingTest extends BaseTest {
	
	//Using getter and setter
	@CSVFile(path = "src/test/resources/testdata/createUser1.csv")
	@Test(dataProvider = "csvDataProvider", dataProviderClass = TestDataProvider.class, enabled = false)
	public void testCreateBooking() {
		// Create booking dates object
		BookingDates dates = new BookingDates("2021-07-29", "2021-07-30");

		// Create booking object
		Booking booking = new Booking("REKHA", "Ram", 5000, true, dates, "dinner");

		Response response = RestClient.post("/booking", booking);

		// Assertions
		Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
		Assert.assertEquals(response.jsonPath().getString("booking.firstname"), booking.getFirstname());
		Assert.assertEquals(response.jsonPath().getString("booking.lastname"), booking.getLastname());
		Assert.assertEquals(response.jsonPath().getInt("booking.totalprice"), booking.getTotalprice());
		Assert.assertEquals(response.jsonPath().getString("booking.additionalneeds"), booking.getAdditionalneeds());

	}
	// 1️⃣ DataProvider: reads CSV

	// 2️⃣ Test method: references the DataProvider
	@Test(enabled = false, dataProvider = "csvData", description = "Verify booking API creates a booking via CSV successfully")
	public void testCreateBooking1(String firstname, String lastname, String totalprice, String depositpaid,
			String checkin, String checkout, String additionalneeds) {

		// Build payload
		JSONObject payload = new JSONObject();
		payload.put("firstname", firstname);
		payload.put("lastname", lastname);
		payload.put("totalprice", Integer.parseInt(totalprice));
		payload.put("depositpaid", Boolean.parseBoolean(depositpaid));

		JSONObject bookingDates = new JSONObject();
		bookingDates.put("checkin", checkin);
		bookingDates.put("checkout", checkout);

		payload.put("bookingdates", bookingDates);
		payload.put("additionalneeds", additionalneeds);

		System.out.println("Payload: " + payload.toString());

		// Send POST request
		Response response = RestClient.post("/booking", payload.toString());
		System.out.println("Response Status Code: " + response.getStatusCode());
		System.out.println("Response Body: " + response.getBody().asString());
	}

	// 2️⃣ Test method: references the DataProvider
	@CSVFile(path = "src/test/resources/testdata/createUser1.csv")
	@Test(dataProvider = "csvDataProvider", dataProviderClass = TestDataProvider.class)
	public void testCreateBooking2(String firstname, String lastname, String totalprice, String depositpaid,
			String checkin, String checkout, String additionalneeds) {
		PayloadBuilder.createBookingPayload(firstname, lastname, totalprice, depositpaid, checkin, checkout,
				additionalneeds);

		// Send POST request
		Response response = PayloadBuilder.createBookingPayload(firstname, lastname, totalprice, depositpaid, checkin,
				checkout, additionalneeds);
		System.out.println("Response Status Code: " + response.getStatusCode());
		System.out.println("Response Body: " + response.getBody().asString());
		Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
		Assert.assertEquals(response.jsonPath().getString("booking.firstname"), firstname);
		Assert.assertEquals(response.jsonPath().getString("booking.lastname"), lastname);
		Assert.assertEquals(response.jsonPath().getInt("booking.totalprice"), Integer.parseInt(totalprice));
		Assert.assertEquals(response.jsonPath().getString("booking.additionalneeds"), additionalneeds);
		

	}

}
