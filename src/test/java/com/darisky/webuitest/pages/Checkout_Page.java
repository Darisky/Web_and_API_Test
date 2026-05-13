package com.darisky.webuitest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Checkout_Page {
    WebDriver theDriver;
    WebDriverWait wait;

    //form
    By elementPlaceProduct = By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button");
    By formNameInputField = By.xpath("//*[@id=\"name\"]");
    By formCountryInputField = By.xpath("//*[@id=\"country\"]");
    By formCityInputField = By.xpath("//*[@id=\"city\"]");
    By formCCinputInputField = By.xpath("//*[@id=\"card\"]");
    By formMonthInputField = By.xpath("//*[@id=\"month\"]");
    By formYearInputField = By.xpath("//*[@id=\"year\"]");

    //else
    By elementTotalPrice = By.xpath("//*[@id=\"totalm\"]");
    By elementPurchaseButton = By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]");
    By elementFinalOkButton = By.xpath("/html/body/div[10]/div[7]/div/button");

    public Checkout_Page(WebDriver driverCheckOut, WebDriverWait waitDriverCheckOut){
        this.theDriver = driverCheckOut;
        this.wait = waitDriverCheckOut;
    }

    public void chartUrl(){
        theDriver.getCurrentUrl();
    }

    public boolean isProductInCart(String productName, int productPrice) {
        String xpathExpression = "//tr[contains(., '" + productName + "') and contains(., '" + productPrice + "')]";
        By productInTable = By.xpath(xpathExpression);
        try {
            WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(productInTable));
            return product.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickPlaceOrder(){
        wait.until(ExpectedConditions.elementToBeClickable(elementPlaceProduct));
        theDriver.findElement(elementPlaceProduct).click();
    }

    public boolean checkFormOrder(){
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"orderModal\"]/div")));
        return true;
        }catch (Exception e){
            return false;
        }
    }

    public void formCheckOut(String name, String country, String city, String cc, String  month, String  year ){
        theDriver.findElement(formNameInputField).sendKeys(name);
        theDriver.findElement(formCountryInputField).sendKeys(country);
        theDriver.findElement(formCityInputField).sendKeys(city);
        theDriver.findElement(formCCinputInputField).sendKeys(String.valueOf(cc));
        theDriver.findElement(formMonthInputField).sendKeys(String.valueOf(month));
        theDriver.findElement(formYearInputField).sendKeys(String.valueOf(year));
    }

    public String checkTotalPrice(){
        WebElement thePrice = wait.until(ExpectedConditions.visibilityOfElementLocated(elementTotalPrice));
        return thePrice.getText();
    }

    public void clickPurchaseButton(){
        wait.until(ExpectedConditions.elementToBeClickable(elementPurchaseButton)).click();
    }

    public boolean finalConfirmation(){
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[10]/p")));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public String checkDetail(){
        WebElement details = theDriver.findElement(By.xpath("/html/body/div[10]/p"));
        return details.getText();
    }

    public void clickOkButton(){
        wait.until(ExpectedConditions.elementToBeClickable(elementFinalOkButton)).click();
    }
}
