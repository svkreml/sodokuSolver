package svkreml.fun.sudoku;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MyWebDriver {

    WebDriver chromeDriver;
    MyWebDriver(){

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);


        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);



        chromeDriver = new ChromeDriver(options);


    }

    public void get(String url) {
        chromeDriver.get(url);
    }

}