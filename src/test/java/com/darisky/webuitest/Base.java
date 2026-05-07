package com.darisky.webuitest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Base {
    protected static WebDriver theDriver;
    protected static WebDriverWait wait;

    protected void getDriver(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        theDriver = new ChromeDriver(options);
    }

    protected void waiting(){
        wait = new WebDriverWait(theDriver, Duration.ofSeconds(100));
    }
}
