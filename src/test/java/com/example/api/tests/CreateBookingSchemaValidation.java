package com.example.api.tests;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.api.base.BaseTest;
import com.example.api.client.RestClient;
import com.example.api.pojo.Booking;
import com.example.api.pojo.BookingDates;

import io.restassured.response.Response;
public class CreateBookingSchemaValidation extends BaseTest {

    @Test(description = "Verify booking API creates a booking successfully")
    public void testCreateBooking() {
        // Create booking dates object
        BookingDates dates = new BookingDates("2021-07-29", "2021-07-30");

        // Create booking object
        Booking booking = new Booking(
                "REKHA",
                "Ram",
                5000,
                true,
                dates,
                "dinner"
        );

        Response response = RestClient.post("/booking", booking);

        // Print response for debugging
        response.then().log().all();

        // ✅ Schema validation
        response.then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/create_booking_schema.json"));

   
    }
}