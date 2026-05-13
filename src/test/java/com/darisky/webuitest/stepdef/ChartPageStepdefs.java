package com.darisky.webuitest.stepdef;

import com.darisky.webuitest.Base;
import com.darisky.webuitest.pages.Checkout_Page;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ChartPageStepdefs extends Base {
    Checkout_Page checkoutPage;

    @Then("user redirect into chart detail with list of selected product")
    public void userRedirectIntoChartDetailWithListOfSelectedProduct() {
        checkoutPage = new Checkout_Page(theDriver, wait);
        checkoutPage.chartUrl();
        String getUrl = theDriver.getTitle();
        System.out.println("Current URL: " + getUrl);
    }

    @And("user verify name {string} of product and price {int}")
    public void userVerifyNameOfProductAndPrice(String productName, int productPrice) {
        checkoutPage.isProductInCart(productName, productPrice);
    }

    @Then("user click place order")
    public void userClickPlaceOrder() {
        checkoutPage.clickPlaceOrder();
    }

    @And("user see form order")
    public void userSeeFormOrder() {
        checkoutPage.checkFormOrder();
    }

    @When("user input Name {string} Country {string} City {string} Credit card {string} Month {string} Year {string}")
    public void userInputNameCountryCityCreditCardMonthYear(String name, String country, String city, String cc, String month, String year) {
        checkoutPage.formCheckOut(name, country, city, cc, month, year);
    }

    @And("user verify total purchase")
    public void userVerifyTotalPurchase() {
        checkoutPage.checkTotalPrice();
        System.out.println("Here's total price" + checkoutPage.checkTotalPrice());
    }

    @Then("user click purchase")
    public void userClickPurchase() {
        checkoutPage.clickPurchaseButton();
    }

    @When("user see popup purchase confirmation")
    public void userSeePopupPurchaseConfirmation() {
        checkoutPage.checkFormOrder();
    }

    @Then("user verify purchase order and get id order")
    public void userVerifyPurchaseOrderAndGetIdOrder() {
        checkoutPage.checkDetail();
        System.out.printf("Here's your checkout detail" + checkoutPage.checkDetail());
    }

    @And("user click OK button")
    public void userClickOKButton() {
        checkoutPage.clickOkButton();
    }
}
