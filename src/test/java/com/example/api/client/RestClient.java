package com.example.api.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class RestClient {

    // 🔹 Base GET (no headers, no params)
    public static Response get(String endpoint) {
        return given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    // 🔹 GET with Headers
    public static Response get(String endpoint, Map<String, String> headers) {
        return given()
                .headers(headers)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    // 🔹 GET with Headers and Query Params
    public static Response get(String endpoint, Map<String, String> headers, Map<String, Object> queryParams) {
        return given()
                .headers(headers)
                .queryParams(queryParams)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    // 🔹 POST with Body only
    public static Response post(String endpoint, Object body) {
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .log().all()
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    // 🔹 POST with Headers + Body
    public static Response post(String endpoint, Map<String, String> headers, Object body) {
        return given()
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(body)
                .log().all()
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    // 🔹 PUT request
    public static Response put(String endpoint, Map<String, String> headers, Object body) {
        return given()
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(body)
                .log().all()
                .when()
                .put(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    // 🔹 PATCH request
    public static Response patch(String endpoint, Map<String, String> headers, Object body) {
        return given()
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(body)
                .log().all()
                .when()
                .patch(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    // 🔹 DELETE (with headers)
    public static Response delete(String endpoint, Map<String, String> headers) {
        return given()
                .headers(headers)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .delete(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    // 🔹 DELETE (with headers + path param)
    public static Response delete(String endpoint, Map<String, String> headers, Map<String, Object> pathParams) {
        return given()
                .headers(headers)
                .pathParams(pathParams)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .delete(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    // 🔹 Generic method builder (for reusability)
    public static RequestSpecification requestBuilder(Map<String, String> headers, Map<String, Object> queryParams) {
        RequestSpecification request = given().contentType(ContentType.JSON);

        if (headers != null) request.headers(headers);
        if (queryParams != null) request.queryParams(queryParams);

        return request.log().all();
    }
}
