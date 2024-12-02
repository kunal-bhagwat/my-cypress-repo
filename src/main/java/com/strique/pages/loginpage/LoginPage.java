package com.strique.pages.loginpage;

import com.strique.pages.BasePage;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * LoginPage class handles the login page interactions. It uses Page Object Model (POM) and extends
 * the BasePage class.
 */
public class LoginPage extends BasePage {

  private final WebDriverWait wait;

  // Locators for username input field
  @FindBy(xpath = "//input[@id='identifier-field']")
  WebElement striqueUserEmail;

  // Locator for the continue button after entering the username
  @FindBy(xpath = "//button[@class='cl-formButtonPrimary cl-button 🔒️ cl-internal-8viq2k']")
  WebElement userContinueButton;

  // Locator for the password input field
  @FindBy(xpath = "//input[@id='password-field' and @type='password' and @name='password']")
  WebElement striqueUserPassword;

  // Locator for the continue button after entering the password
  @FindBy(xpath = "//button[@class='cl-formButtonPrimary cl-button 🔒️ cl-internal-8viq2k']")
  WebElement passwordContinueButton;

  /** constructor to initialize the driver and call the parent class constructor. */
  public LoginPage(WebDriver driver) {
    super(driver); // Calls the constructor of BasePage
    PageFactory.initElements(driver, this); // Initialize elements using PageFactory
    wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Centralized WebDriverWait
  }

  /** Method to send the username to the username field. */
  public WebElement striqueUserEmail() {
    wait.until(ExpectedConditions.visibilityOf(striqueUserEmail));
    return striqueUserEmail;
  }

  /** Method to click the continue button after entering the username. */
  public WebElement userContinueButton() {
    wait.until(ExpectedConditions.elementToBeClickable(userContinueButton));
    return userContinueButton;
  }

  /** Method to send the password to the password field. */
  public WebElement striqueUserPassword() {
    wait.until(ExpectedConditions.visibilityOf(striqueUserPassword));
    return striqueUserPassword;
  }

  /** Method to click the continue button after entering the password. */
  public WebElement passwordContinueButton() {
    wait.until(ExpectedConditions.elementToBeClickable(passwordContinueButton));
    return passwordContinueButton;
  }
}
