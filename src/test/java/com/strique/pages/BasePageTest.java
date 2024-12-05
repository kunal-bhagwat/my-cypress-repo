package com.strique.pages;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BasePageTest {

  public WebDriver driver;
  public Logger logger;

  @Test(priority = 0)
  @Parameters({"browser", "headless"})
  public void setup(String br, String headless) {
    // Initialize the logger here to ensure it's not null
    logger = LogManager.getLogger(this.getClass());

    Properties prop = new Properties();
    try (FileReader reader = new FileReader("src/main/resources/config.properties")) {
      prop.load(reader);
    } catch (IOException e) {
      e.printStackTrace();
    }

    boolean isHeadless = Boolean.parseBoolean(headless);

    switch (br.toLowerCase()) {
      case "chrome":
        ChromeOptions chromeOptions = new ChromeOptions();
        if (isHeadless) {
          chromeOptions.addArguments("--headless");
          chromeOptions.addArguments("--disable-gpu");
          chromeOptions.addArguments("--window-size=1920x1080");
        }
        driver = new ChromeDriver(chromeOptions);
        break;
      case "edge":
        EdgeOptions edgeOptions = new EdgeOptions();
        if (isHeadless) {
          edgeOptions.addArguments("--headless");
          edgeOptions.addArguments("--disable-gpu");
          edgeOptions.addArguments("--window-size=1920x1080");
        }
        driver = new EdgeDriver(edgeOptions);
        break;
      case "firefox":
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        if (isHeadless) {
          firefoxOptions.addArguments("--headless");
        }
        driver = new FirefoxDriver(firefoxOptions);
        break;
      case "safari":
        if (isHeadless) {
          System.out.println("Safari does not support headless mode on macOS");
        }
        driver = new SafariDriver();
        break;
      default:
        System.out.println("Invalid browser name..");
        return;
    }

    driver.manage().deleteAllCookies();
    driver.get(prop.getProperty("appURL"));
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

    // Log the setup information
    logger.info("Browser setup completed for: " + br + ", Headless: " + headless);
  }
}
