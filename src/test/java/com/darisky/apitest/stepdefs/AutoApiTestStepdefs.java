package com.darisky.apitest.stepdefs;

import com.darisky.apitest.services.ApiTest;
import com.github.javafaker.Faker;
import io.cucumber.java.PendingException;
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

    //Main-Test
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
        System.out.println("<------ SUCCESS: Tag list fetched successfully! ------>");
    }

    //Negative-Test
    @When("Create user with title {string}, first name {string}, last name {string} and without email")
    public void createUserWithTitleFirstNameLastNameAndWithoutEmail(String title, String firstName, String lastName) {
        JSONObject newUser = new JSONObject();
        newUser.put("title", title)
                .put("firstName", firstName)
                .put("lastName", lastName);

        theResponse = userService.createNewUser(newUser);
    }

    @Then("report error with code {int} and see message {string}")
    public void reportErrorWithCodeAndSeeMessage(int errCode, String errMessage) {
        theResponse.then().log().all()
                .assertThat().statusCode(errCode) ;

        String errorMessage = theResponse.jsonPath().getString("data.email");
        assertEquals("here's error message", errMessage, errorMessage);
        System.out.println("<------ Error Message: " + errorMessage + "------>");
    }

    @When("Create user with first name with more {int} character {string} title {string} last name {string} email auto generated")
    public void createUserWithFirstNameWithMoreCharacterTitleLastNameEmailAutoGenerated(int lengthChar, String firstName, String title, String lastName) {
        String randomEmail = faker.internet().emailAddress();
        System.out.println("Generated Test Email: " + randomEmail);

        JSONObject newUser = new JSONObject();
        newUser.put("title", title)
                .put("firstName", firstName)
                .put("lastName", lastName)
                .put("email", randomEmail);

        theResponse = userService.createNewUser(newUser);
    }

    @Then("tester see report error")
    public void testerSeeReportError() {
        theResponse.then().assertThat().statusCode(400) ;
        String errorMessage = theResponse.jsonPath().getString("data.firstName");
        System.out.println("<------ Error Message: " + errorMessage + "------>");
    }
}
