package com.strique.pages;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * BasePage class provides the setup for initializing the WebDriver based on the browser and OS
 * parameters passed via TestNG XML.
 */
public class BasePageTest {

  // WebDriver instance to control the browser
  public WebDriver driver;
  // Logger instance for logging information during execution
  public Logger logger;

  /**
   * Setup method initializes the WebDriver based on the browser parameter.
   *
   * @param br The browser name (e.g., chrome, firefox, edge, safari).
   */
  @Test(priority = 0)
  @Parameters({"browser"}) // Read parameters from the master and TestNG XML file
  public void setup(String br) {
    // Create a Properties object to load configuration settings
    Properties prop = new Properties();
    try (FileReader reader = new FileReader("src/main/resources/config.properties")) {
      // Load the properties file to read configuration values (e.g., appURL)
      prop.load(reader);
    } catch (IOException e) {
      e.printStackTrace(); // Handle exceptions that may occur while loading the file
    }

    logger = LogManager.getLogger(this.getClass());

    // Switch-case to handle browser selection based on the parameter
    switch (br.toLowerCase()) {
      case "chrome":
        driver = new ChromeDriver();
        break; // Launch Chrome browser
      case "edge":
        driver = new EdgeDriver();
        break; // Launch Edge browser
      case "firefox":
        driver = new FirefoxDriver();
        break; // Launch Firefox browser
      case "safari":
        driver = new SafariDriver();
        break; // Launch Safari browser
      default:
        System.out.println("Invalid browser name..");
        return; // Handle invalid browser inputs
    }

    // Delete all cookies before starting the session
    driver.manage().deleteAllCookies();
    // reading url from properties file
    driver.get(prop.getProperty("appURL"));
    // Maximize the browser window
    driver.manage().window().maximize();
    // Wait for 20 seconds
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
  }
}