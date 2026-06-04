package com.darisky.webuitest.stepdef;

import com.darisky.webuitest.Base;
import com.darisky.webuitest.pages.Product_Detail;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.assertEquals;

public class ProductDetailStepdefs extends Base {
    Product_Detail productDetail = new Product_Detail(theDriver, wait);

    @Then("user redirect into product detail")
    public void userRedirectIntoProductDetail() {
        productDetail.productUrl();
    }


    @When("user click add to chart button")
    public void userClickAddToChartButton() {
        productDetail.clickAddChart();
    }

    @Then("user see popup {string}")
    public void userSeePopup(String popUpAddedProduct) {
       String productAddedNotification = productDetail.addedChartConfirmation();
       assertEquals(popUpAddedProduct, productAddedNotification);
        System.out.println("Pop-Up Notification says: " + productAddedNotification);
    }

    @And("user click chart")
    public void userClickChart() {
        productDetail.clickingChartButton();
    }
}
