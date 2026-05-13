package com.darisky.webuitest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Home_Page {
    WebDriver theDriver;
    WebDriverWait wait;

    //Object finder
    By loginHomePageButton = By.id("login2");
    By checkUserName = By.id("nameofuser");
    By elementCartButton = By.xpath("//*[@id=\"cartur\"]");

    public Home_Page(WebDriver homePageDriver, WebDriverWait waitHomePage){
        this.theDriver = homePageDriver;
        this.wait = waitHomePage;
    }

    public void onDemoBlazeSite(){
        theDriver.get("https://www.demoblaze.com/index.html");
    }

    public void setLoginHomePageButton(){
        theDriver.findElement(loginHomePageButton).click();
    }

    public String setCheckUserName(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(checkUserName));
        return element.getText();
    }

    public void selectingProduct(String productName){
        By productNameLocation = By.xpath("//a[text()='" + productName + "']");
        wait.until(ExpectedConditions.elementToBeClickable(productNameLocation)).click();
    }

}
