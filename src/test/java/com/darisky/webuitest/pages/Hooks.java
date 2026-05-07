package com.darisky.webuitest.pages;

import com.darisky.webuitest.Base;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends Base {
    @Before("@web")
    public void beforeTest(){
        getDriver();
        waiting();
    }

    @After("@web")
    public void afterTest(){
        theDriver.quit();
    }
}
