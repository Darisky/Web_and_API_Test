package com.darisky.webuitest.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Product_Detail {
    WebDriver theDriver;
    WebDriverWait wait;

    By elementAddChart = By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a");
    By elementChartButton = By.xpath("//*[@id=\"cartur\"]");

    public Product_Detail(WebDriver productDriver, WebDriverWait productWait){
       this.theDriver = productDriver;
       this.wait = productWait;
    }

    public void productUrl(){
        theDriver.getCurrentUrl();
    }

    public void clickAddChart(){
        wait.until(ExpectedConditions.elementToBeClickable(elementAddChart));

        theDriver.findElement(elementAddChart).click();
    }

    public String addedChartConfirmation(){
        wait.until(ExpectedConditions.alertIsPresent());
        Alert confirmationAlert = theDriver.switchTo().alert();
        String readConfirmation = confirmationAlert.getText();
        confirmationAlert.accept();

        return readConfirmation;
    }

    public void clickingChartButton(){
        theDriver.findElement(elementChartButton).click();
    }


}
