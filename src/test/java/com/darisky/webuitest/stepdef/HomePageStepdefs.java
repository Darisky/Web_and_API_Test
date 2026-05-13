package com.darisky.webuitest.stepdef;

import com.darisky.webuitest.Base;
import com.darisky.webuitest.pages.Home_Page;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePageStepdefs extends Base {
    Home_Page homePage;
    @Given("user at home page about to login")
    public void userAtHomePageAboutToLogin() {
        homePage = new Home_Page(theDriver, wait);
        homePage.onDemoBlazeSite();
    }

    @When("user click Log In Button")
    public void userClickLogInButton() {
        homePage.setLoginHomePageButton();
    }

    @And("user see {string} if success login")
    public void userSeeIfSuccessLogin(String Text) {
        homePage.setCheckUserName();
        assertEquals(Text, homePage.setCheckUserName(), "Didn't Match");
    }

    @When("user at home page see list of product")
    public void userAtHomePageSeeListOfProduct() {
        homePage.onDemoBlazeSite();
    }

    @And("user click a product name {string}")
    public void userClickAProductName(String product) {
        homePage.selectingProduct(product);
    }


}
