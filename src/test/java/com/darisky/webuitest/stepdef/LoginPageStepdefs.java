package com.darisky.webuitest.stepdef;

import com.darisky.webuitest.Base;
import com.darisky.webuitest.pages.Login_Page;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertEquals;

public class LoginPageStepdefs extends Base {
    Login_Page loginPage;

    //login-Sequence
    @Then("user see user name and password field")
    public void userSeeUserNameAndPasswordField() {
        loginPage = new Login_Page(theDriver, wait);
        loginPage.checkLoginField();
    }

    @And("user input valid user name and password with {string} and {string}")
    public void userInputValidUserNameAndPasswordWithAnd(String inputUserName, String inputPassword) {
        loginPage.inputCredential(inputUserName, inputPassword);
    }

    @Then("user click login button")
    public void userClickLoginButton() {
        loginPage.setClickLogin();
    }

    //login-sequence-Negative-Test
    @And("user input invalid username and password with {string} and {string}")
    public void userInputInvalidUsernameAndPasswordWithAnd(String invalidUserName, String invalidPassword) {
        loginPage.inputCredential(invalidUserName, invalidPassword);
    }

    @And("user see error message {string}")
    public void userSeeErrorMessage(String expectedErrorMessage) {
        String errorMessage = loginPage.getErrorAlert();
        assertEquals(expectedErrorMessage, errorMessage);
        System.out.println("Error Message : " + errorMessage);
    }

    @And("user skip input username and password with {string} and {string}")
    public void userSkipInputUsernameAndPasswordWithAnd(String emptyUserName, String emptyPassword) {
        loginPage.inputCredential(emptyUserName, emptyPassword);
    }
}
