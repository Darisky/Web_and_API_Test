package com.darisky.apitest.services;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class ApiTest {
    private static final String appIdCred = "63a804408eb0cb069b57e43a";

    public ApiTest(){
        RestAssured.baseURI = "https://dummyapi.io/data/v1/";
    }

    public Response createNewUser(JSONObject payload){
        return given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("app-id", appIdCred)
                .body(payload.toString())
                .when().post("user/create");
    }

    public Response getUser(String userId) {
        return given().log().all()
                .header("app-id", appIdCred)
                .when().get("user/" + userId);
    }

    public Response updateUser(String userId, JSONObject payload) {
        return given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("app-id", appIdCred)
                .body(payload.toString())
                .when().put("user/" + userId);
    }

    public Response deleteUser(String userId) {
        return given().log().all()
                .header("app-id", appIdCred)
                .when().delete("user/" + userId);
    }

    public Response getTagList(){
        return given().log().all()
                .header("app-id", appIdCred)
                .when().get("tag/");
    }
}
