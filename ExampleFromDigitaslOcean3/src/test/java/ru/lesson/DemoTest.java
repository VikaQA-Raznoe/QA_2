package ru.lesson;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.UUID;

public class DemoTest {
    private RemoteWebDriver driver;

    @Test
    public void browserFirefoxTest() throws Exception{

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("firefox");
        capabilities.setVersion("60.0");
        //Эксперимент
        capabilities.setCapability("enableVNC",true);
        capabilities.setCapability("enableVideo",true);
        driver = new RemoteWebDriver(

                //Работает
                //URI.create("http://192.168.100.8:4444/wd/hub").toURL(),
                URI.create("http://192.168.100.5:4444/wd/hub").toURL(),
                capabilities
        );

        try {
            Thread.sleep(30000);
            driver.get("http://duckduckgo.com/");
            WebElement input = driver.findElement(By.cssSelector("input#search_form_input_homepage"));
            input.sendKeys("selenium", Keys.ENTER);
            Thread.sleep(10000);
        } finally {
            takeScreenshot(driver);
        }
    }

    @Test
    //@Test(invocationCount=10, threadPool=10)
    public void browserChromeTest() throws Exception{

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("66.0");
        //Эксперимент
        capabilities.setCapability("enableVNC",true);
        capabilities.setCapability("enableVideo",true);
        driver = new RemoteWebDriver(

                //Работает
                //URI.create("http://192.168.100.8:4444/wd/hub").toURL(),
                URI.create("http://192.168.100.5:4444/wd/hub").toURL(),
                capabilities
        );

        try {
            Thread.sleep(30000);
            driver.manage().window().setSize(new Dimension(1920,1080));
            driver.get("http://duckduckgo.com/");
            WebElement input = driver.findElement(By.cssSelector("input#search_form_input_homepage"));
            input.sendKeys("selenium", Keys.ENTER);
            Thread.sleep(10000);
        } finally {
            takeScreenshot(driver);
        }
    }

    //@Test(threadPoolSize = 100, invocationCount = 100)
    //@DataProvider(parallel = true)

    public void takeScreenshot(WebDriver driver) throws Exception{
        byte[] screen = ((TakesScreenshot) new Augmenter().augment(driver)).getScreenshotAs(OutputType.BYTES);
        UUID uuid = UUID.randomUUID();
        FileUtils.writeByteArrayToFile(new File(uuid.toString() + ".png"),screen);
    }

    @After
    public void closeDriver(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }
}
