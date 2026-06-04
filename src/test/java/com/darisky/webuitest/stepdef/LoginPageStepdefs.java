package com.darisky.webuitest.stepdef;

import com.darisky.webuitest.Base;
import com.darisky.webuitest.pages.Login_Page;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class LoginPageStepdefs extends Base {
    Login_Page loginPage = new Login_Page(theDriver, wait);

    //login-Sequence
    @And("user see user name and password field")
    public void userSeeUserNameAndPasswordField() {
        loginPage.checkLoginField();
    }
    @When("user input valid user name and password with {string} and {string}")
    public void userInputValidUserNameAndPasswordWithAnd(String userName, String password) {
        loginPage.inputCredential(userName, password);
    }
    @And("user click Log In at login field")
    public void userClickLogInAtLoginField() {
        loginPage.setClickLogin();
    }

    //login-sequence-Negative-Test
    @When("user input invalid user name and password with {string} and {string}")
    public void userInputInvalidUserNameAndPasswordWithAnd(String userName, String password) {
        loginPage.inputCredential(userName, password);
    }

    @Then("user see error message {string}")
    public void userSeeErrorMessage(String expectedErrorMessage) {
        String errorMessage = loginPage.getErrorAlert();
        assertEquals(expectedErrorMessage, errorMessage);
        System.out.println("Error! " + errorMessage);
    }
}
