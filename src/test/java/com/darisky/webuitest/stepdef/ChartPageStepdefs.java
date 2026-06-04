package com.darisky.webuitest.stepdef;

import com.darisky.webuitest.Base;
import com.darisky.webuitest.pages.Checkout_Page;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

public class ChartPageStepdefs extends Base {
    Checkout_Page checkoutPage = new Checkout_Page(theDriver, wait);

    @And("user redirect into chart detail with list of selected product")
    public void userRedirectIntoChartDetailWithListOfSelectedProduct() {
        checkoutPage.chartUrl();
        String getUrl = theDriver.getTitle();
        System.out.println("Current URL: " + getUrl);
    }

    @And("user verify name {string} of product and price {int}")
    public void userVerifyNameOfProductAndPrice(String productName, int productPrice) {
        checkoutPage.isProductInCart(productName, productPrice);
    }

    @And("user click place order")
    public void userClickPlaceOrder() {
        checkoutPage.clickPlaceOrder();
    }

    @And("user see form order")
    public void userSeeFormOrder() {
        checkoutPage.checkFormOrder();
    }

    @When("user input detail information:")
    public void userInputDetailInformation(DataTable formTable) {
        List<Map<String, String>> dataTable = formTable.asMaps(String.class, String.class);
        String name = dataTable.get(0).get("Name");
        String country = dataTable.get(0).get("Country");
        String city = dataTable.get(0).get("City");
        String ccNumber = dataTable.get(0).get("Credit_Card");
        String ccYear = dataTable.get(0).get("Year");
        String ccMonth = dataTable.get(0).get("Month");

        checkoutPage.formCheckOut(name, country, city, ccNumber, ccMonth, ccYear);
    }

    @And("user verify total purchase")
    public void userVerifyTotalPurchase() {
        checkoutPage.checkTotalPrice();
        System.out.println("Here's total price" + checkoutPage.checkTotalPrice());
    }

    @And("user click purchase")
    public void userClickPurchase() {
        checkoutPage.clickPurchaseButton();
    }

    @And("user see popup purchase confirmation")
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
