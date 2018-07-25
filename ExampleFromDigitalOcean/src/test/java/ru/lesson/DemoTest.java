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

    /*@Before
    public void openDriver() throws Exception{
        //final DesiredCapabilities browser = DesiredCapabilities.chrome();
        final DesiredCapabilities browser = DesiredCapabilities.firefox();
        browser.setCapability("enableVNC",true);
        browser.setCapability("screenResolution","1920x1024x24");
        //driver = new RemoteWebDriver(new URL("http://95.85.41.216:4444/wd/hub"),browser);
        driver = new RemoteWebDriver(new URL("http://192.168.100.8:8080/wd/hub"),browser);
    }*/

    /*@Before
    public void openDriver() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("firefox");
        capabilities.setVersion("latest");

        RemoteWebDriver remoteWebDriver = new RemoteWebDriver(
                //URI.create("http://localhost:4444/wd/hub").toURL(),capabilities);
                //URI.create("http://192.168.100.9:4444/wd/hub").toURL(),capabilities);
                URI.create("http://192.168.100.9:4444/wd/hub").toURL(),capabilities);
    }*/

   /* @Before
    public void openDriver() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("UNKNOWN");
        capabilities.setVersion("");

        RemoteWebDriver driver = new RemoteWebDriver(
                 //URI.create("http://localhost:4444/wd/hub").toURL(),
                URI.create("http://192.168.100.9:4444/wd/hub").toURL(),
                capabilities
        );
    }*/

    /*@Before
    public void openDriver() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");

        RemoteWebDriver driver = new RemoteWebDriver(
                URI.create("http://localhost:4444/wd/hub").toURL(),
                capabilities
        );
    }*/

    /*@Before
    public void openDriver() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        RemoteWebDriver driver = new RemoteWebDriver(
                URI.create("http://192.168.100.9:4444/wd/hub").toURL(),
                capabilities
        );
    }*/

 /*   @Before
    public void openDriver() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();

        //capabilities.setBrowserName("firefox");
        capabilities.setBrowserName("chrome");

        RemoteWebDriver driver = new RemoteWebDriver(

                URI.create("http://192.168.100.10:4444/wd/hub").toURL(),
                //URI.create("http://127.0.0.1:38055/wd/hub").toURL(),
                //URI.create("http://192.168.100.10:8080/wd/hub").toURL(),

                capabilities
                //driver = new RemoteWebDriver(new URL("http://192.168.100.8:8080/wd/hub"),capabilities));
        );
        //driver = new RemoteWebDriver(new URL("http://192.168.100.8:8080/wd/hub"),browser);

}*/


   /* //Отработал раз
    @Before
    public void openDriver() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();

        //capabilities.setBrowserName("firefox");
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("latest");
        driver = new RemoteWebDriver(

                //Работало
                URI.create("http://192.168.100.8:4444/wd/hub").toURL(),

                capabilities
                //driver = new RemoteWebDriver(new URL("http://192.168.100.8:8080/wd/hub"),capabilities));
        );
        //driver = new RemoteWebDriver(new URL("http://192.168.100.8:8080/wd/hub"),browser);

    }*/

    @Before
    public void openDriver() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();

        //capabilities.setBrowserName("firefox");
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
                //driver = new RemoteWebDriver(new URL("http://192.168.100.8:8080/wd/hub"),capabilities));
        );
        //driver = new RemoteWebDriver(new URL("http://192.168.100.8:8080/wd/hub"),browser);

    }
    @Test
    public void browserTest() throws Exception{
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
