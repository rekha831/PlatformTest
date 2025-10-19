package com.example.api.base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import com.example.api.utils.ConfigReader;

public class BaseTest {
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigReader.get("base.url");
    }
}
