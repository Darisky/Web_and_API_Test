package com.darisky.webuitest.stepdef;

import com.darisky.webuitest.Base;
import com.darisky.webuitest.pages.Home_Page;
import com.darisky.webuitest.pages.Login_Page;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePageStepdefs extends Base {
    Home_Page homePage = new Home_Page(theDriver, wait);
    Login_Page loginPage = new Login_Page(theDriver, wait);

    @Given("user at home page about to login")
    public void userAtHomePageAboutToLogin() {
        homePage.onDemoBlazeSite();
    }

    @And("user click Log In Button")
    public void userClickLogInButton() {
        homePage.setLoginHomePageButton();
    }

    @And("user see {string} if success login")
    public void userSeeIfSuccessLogin(String Text) {
        homePage.setCheckUserName();
        assertEquals(Text, homePage.setCheckUserName(), "Didn't Match");
    }

    @And("user at home page see list of product")
    public void userAtHomePageSeeListOfProduct() {
        homePage.onDemoBlazeSite();
    }

    @And("user click a product name {string}")
    public void userClickAProductName(String product) {
        homePage.selectingProduct(product);
    }

    @Given("user already logged in")
    public void userAlreadyLoggedIn() {
        String userName = "juniorTester";
        String password = "theJuniorTester";

        homePage.onDemoBlazeSite();
        homePage.setLoginHomePageButton();
        loginPage.checkLoginField();
        loginPage.inputCredential(userName, password);
        loginPage.setClickLogin();
    }
}
