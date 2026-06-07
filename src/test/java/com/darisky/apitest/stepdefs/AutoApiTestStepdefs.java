package com.darisky.apitest.stepdefs;

import com.darisky.apitest.services.ApiTest;
import com.github.javafaker.Faker;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class AutoApiTestStepdefs {
    ApiTest userService = new ApiTest();
    Faker faker = new Faker();

    private String newUserId;
    private Response theResponse;

    //Main-Test
    @Given("the DummyAPI endpoint is configured")
    public void theDummyAPIEndpointIsConfigured() {
    }

    @When("I create a new user and get random email:")
    public void iCreateANewUserAndGetRandomEmail(DataTable apiTable) {
        String randomEmail = faker.internet().emailAddress();
        System.out.println("Generated Random Email: " + randomEmail);

        List<Map<String, String>> data = apiTable.asMaps(String.class, String.class);
        String title = data.get(0).get("Title");
        String fName = data.get(0).get("First_Name");
        String lName = data.get(0).get("Last_Name");

        JSONObject newUser = new JSONObject();
        newUser.put("title", title)
                .put("firstName", fName)
                .put("lastName", lName)
                .put("email", randomEmail);

        theResponse = userService.createNewUser(newUser);
    }

    @And("the user is successfully created and an ID is returned")
    public void theUserIsSuccessfullyCreatedAndAnIDIsReturned() {
        theResponse.then().assertThat().statusCode(200);

        newUserId = theResponse.jsonPath().getString("id");
        System.out.println("=======================================");
        System.out.println("SUCCESS: User created with ID: "+ newUserId);
        System.out.println("=======================================");
    }

    @And("I fetch the user using the generated ID")
    public void iFetchTheUserUsingTheGeneratedID() {
        theResponse.then().assertThat()
                .body(matchesJsonSchemaInClasspath("SchemaValidator.json"));
        theResponse = userService.getUser(newUserId);
        System.out.println("=======================================");
        System.out.println("SUCCESS Validating Schema for user: "+ newUserId);
        System.out.println("=======================================");
    }

    @And("the API should return status code {int}")
    public void theAPIShouldReturnStatusCode(int expectedResponse) {
        theResponse.then().log().all()
                .assertThat().statusCode(expectedResponse);
        System.out.println("=======================================");
        System.out.println("SUCCESS: Showing Created User");
        System.out.println("=======================================");
    }

    @And("I update the user's first name to {string}")
    public void iUpdateTheUserSFirstNameTo(String newFirstName) {
        JSONObject newUserData = new JSONObject();
        newUserData.put("firstName", newFirstName);
        theResponse = userService.updateUser(newUserId, newUserData);
    }

    @Then("the user is successfully updated")
    public void theUserIsSuccessfullyUpdated() {
        theResponse.then().log().all()
                .assertThat().statusCode(200);
        String actualFirstName = theResponse.jsonPath().getString("firstName");
        assertEquals("The first name did not update correctly!","DenUpdated", actualFirstName);
    }

    @And("I delete the user using the generated ID to clean up data")
    public void iDeleteTheUserUsingTheGeneratedIDToCleanUpData() {
        theResponse = userService.deleteUser(newUserId);
        theResponse.then().assertThat().statusCode(200);
        System.out.println("=======================================");
        System.out.println("CLEANUP SUCCESS: User " + newUserId );
        System.out.println("=======================================");
    }

    //Tag-Test
    @When("I fetch the tag list")
    public void iFetchTheTagList() {
        theResponse = userService.getTagList();
    }

    @Then("the API return tag list")
    public void theAPIReturnTagList() {
        theResponse.then()
                .assertThat().statusCode(200)
                .assertThat().body("data.size()", org.hamcrest.Matchers.greaterThan(0));
        System.out.println("=======================================");
        System.out.println("SUCCESS: Tag list fetched successfully!");
        System.out.println("=======================================");
    }

    //Negative-Test
    @When("Create user with title {string}, first name {string}, last name {string} and email {string}")
    public void createUserWithTitleFirstNameLastNameAndEmail(String title, String fName, String lname, String email) {
        JSONObject newUser = new JSONObject();
        newUser.put("title", title)
                .put("firstName", fName)
                .put("lastName", lname)
                .put("email", email);

        theResponse = userService.createNewUser(newUser);
    }

    @Then("report error with code {int}, {string} and see message {string}")
    public void reportErrorWithCodeExpected_CodeAndSeeMessage(int errCode, String dataPath, String errMessage) {
        theResponse.then().assertThat().statusCode(errCode);

        String errorMessage = theResponse.jsonPath().getString(dataPath);
        assertEquals(errMessage, errorMessage);
        System.out.println("=======================================");
        System.out.println("Error with Message: " + errorMessage);
        System.out.println("=======================================");
    }
}
