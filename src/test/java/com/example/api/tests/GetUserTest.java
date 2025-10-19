package com.example.api.tests;

import com.example.api.base.BaseTest;
import com.example.api.client.RestClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetUserTest extends BaseTest {

    @Test(description = "Verify GET user API returns valid response")
    public void testGetUser() {
        Response response = RestClient.get("/booking/519");
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.jsonPath().getString("firstname").contains("REKHA"));
    }
}
