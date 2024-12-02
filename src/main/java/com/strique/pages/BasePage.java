package com.strique.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * BasePage serves as a parent class for all page object classes. It handles the initialization of
 * WebElements using Selenium's PageFactory.
 */
public class BasePage {

  // WebDriver instance to be shared across all child classes
  WebDriver driver;

  /**
   * Constructor to initialize the WebDriver instance and WebElements.
   *
   * @param driver WebDriver instance passed from test classes or other utilities.
   */
  public BasePage(WebDriver driver) {
    this.driver = driver; // Assign WebDriver to the class variable
    PageFactory.initElements(driver, this); // Initialize WebElements using PageFactory
  }
}
