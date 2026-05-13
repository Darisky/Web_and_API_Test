package com.darisky.webuitest.stepdef;

import com.darisky.webuitest.Base;
import com.darisky.webuitest.pages.Product_Detail;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.assertEquals;

public class ProductDetailStepdefs extends Base {
    Product_Detail productDetail;

    @Then("user redirect into product detail")
    public void userRedirectIntoProductDetail() {
        productDetail = new Product_Detail(theDriver, wait);
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

    @When("user click chart")
    public void userClickChart() {
        productDetail.clickingChartButton();
    }
}
