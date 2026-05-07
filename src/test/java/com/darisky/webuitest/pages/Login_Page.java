package com.darisky.webuitest.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login_Page {
    WebDriver theDriver;
    WebDriverWait wait;

    By userNameInputField = By.id("loginusername");
    By userPasswordInputField = By.id("loginpassword");
    By clickLogin = By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]");

    public Login_Page(WebDriver loginPageDriver, WebDriverWait loginPageWait){
        this.theDriver = loginPageDriver;
        this.wait = loginPageWait;
    }

    public boolean checkLoginField(){
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(userNameInputField));
            wait.until(ExpectedConditions.visibilityOfElementLocated(userPasswordInputField));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public void inputCredential(String inputUserName, String inputPassword){
        theDriver.findElement(userNameInputField).sendKeys(inputUserName);
        theDriver.findElement(userPasswordInputField).sendKeys(inputPassword);
    }

    public void setClickLogin(){
        theDriver.findElement(clickLogin).click();
    }

    public String getErrorAlert(){
        wait.until(ExpectedConditions.alertIsPresent());
        Alert popUpAlert = theDriver.switchTo().alert();
        String readErrorMessage = popUpAlert.getText();
        popUpAlert.accept();

        return readErrorMessage;
    }
}
