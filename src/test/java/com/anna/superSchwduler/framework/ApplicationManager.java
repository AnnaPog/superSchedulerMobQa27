package com.anna.superSchwduler.framework;
import com.anna.superSchwduler.tests.TestBase;
import com.google.common.io.Files;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    AppiumDriver wd;
    UserHelper user;


    public void init() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "qa-27");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("appPackage", "com.example.svetlana.scheduler");
        capabilities.setCapability("appActivity", ".presentation.splashScreen.SplashScreenActivity");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("app", "/Users/annapogrebinskaya/Tools/v.0.0.3.apk");

        wd = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        wd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        user = new UserHelper(wd);

    }

    public void stop() {
        wd.quit();
    }

    public UserHelper getUser() {
        return user;
    }


    public String getCurrentURL() {
        return wd.getCurrentUrl();
    }

    public void goToURL(String url) {
        wd.get(url);
    }

    public void makeScreenshot(long time){
        File tmp = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("Screenshots/screenshot-"+time+".png");

        try {
            Files.copy(tmp, screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
