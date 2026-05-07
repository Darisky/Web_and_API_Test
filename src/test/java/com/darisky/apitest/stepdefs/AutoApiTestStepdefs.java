package com.darisky.apitest.stepdefs;

import com.darisky.apitest.services.ApiTest;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.JSONObject;

import static org.junit.Assert.assertEquals;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class AutoApiTestStepdefs {
    ApiTest userService = new ApiTest();
    Faker faker = new Faker();

    private String newUserId;
    private Response theResponse;

    @Given("the DummyAPI endpoint is configured")
    public void theDummyAPIEndpointIsConfigured() {
    }

    @When("I create a new user with the title {string}, first name {string}, last name {string} and a randomly generated email")
    public void iCreateANewUserWithTheTitleFirstNameLastNameAndARandomlyGeneratedEmail(String title, String firstName, String lastName) {
        String randomEmail = faker.internet().emailAddress();
        System.out.println("Generated Test Email: " + randomEmail);

        JSONObject newUser = new JSONObject();
        newUser.put("title", title)
                .put("firstName", firstName)
                .put("lastName", lastName)
                .put("email", randomEmail);

        theResponse = userService.createNewUser(newUser);
    }

    @Then("the user is successfully created and an ID is returned")
    public void theUserIsSuccessfullyCreatedAndAnIDIsReturned() {
        theResponse.then().assertThat().statusCode(200);

        newUserId = theResponse.jsonPath().getString("id");
        System.out.println("<------SUCCESS: User created with ID: " + newUserId + "------>");
    }

    @When("I fetch the user using the generated ID")
    public void iFetchTheUserUsingTheGeneratedID() {
        theResponse.then().assertThat()
                .body(matchesJsonSchemaInClasspath("SchemaValidator.json"));
        theResponse = userService.getUser(newUserId);
        System.out.println("<------ SUCCESS Validating Schema for user: " + newUserId + "------>");
    }

    @Then("the API should return status code {int}")
    public void theAPIShouldReturnStatusCode(int expectedResponse) {
        theResponse.then().log().all()
                .assertThat().statusCode(expectedResponse);
        System.out.println("<------SUCCESS: Showing Created User ------>");
    }

    @When("I update the user's first name to {string}")
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
        assertEquals("The first name did not update correctly!", actualFirstName,"DenUpdated");
    }

    @And("I delete the user using the generated ID to clean up data")
    public void iDeleteTheUserUsingTheGeneratedIDToCleanUpData() {
        theResponse = userService.deleteUser(newUserId);
        theResponse.then().assertThat().statusCode(200);

        System.out.println("<------ CLEANUP SUCCESS: User " + newUserId + " has been permanently deleted. ------>");
    }

    @When("I fetch the tag list")
    public void iFetchTheTagList() {
        theResponse = userService.getTagList();
    }

    @Then("the API return tag list")
    public void theAPIReturnTagList() {
        theResponse.then()
                .assertThat().statusCode(200)
                .assertThat().body("data.size()", org.hamcrest.Matchers.greaterThan(0));
        System.out.println("SUCCESS: Tag list fetched successfully!");
    }
}
