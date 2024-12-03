package com.strique.pages.loginpage;

import java.time.Duration;
import java.util.Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

/** Class for Safari Browser Login Test Case . */
public class SafariLoginTest {

  /** Test method to validate the login functionality on Safari browser. */
  @Test
  public void signIn() throws InterruptedException {
    // Set up the WebDriver for Safari
    WebDriver driver = new SafariDriver();

    // Navigate to the login page
    driver.get("https://dash.strique.io/auth/sign-in");

    // Set up explicit wait for synchronizing with the browser actions
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    driver.manage().window().maximize();

    // Wait for the username field to be visible and send username
    WebElement striqueUserEmail =
        wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.xpath("//input[@id='identifier-field']")));
    striqueUserEmail.sendKeys("teststrique@gmail.com");

    // Wait for the page to fully load after clicking "Continue"
    WebElement userContinueButton =
        wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.xpath(
                    "//button[@class='cl-formButtonPrimary cl-button 🔒️ cl-internal-8viq2k']")));
    userContinueButton.click();

    // Wait for the page to fully load after clicking "Continue"
    WebDriverWait waitForPassword = new WebDriverWait(driver, Duration.ofSeconds(20));
    waitForPassword.until(
        webDriver ->
            Objects.equals(
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState"),
                "complete"));
    for (int i = 0; i < 3; i++) {
      try {
        WebElement password =
            wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@id='password-field']")));
        password.sendKeys("test@strique");
        break; // Exit loop if successful
      } catch (StaleElementReferenceException e) {
        System.out.println("Stale element encountered.");
      }
    }

    // Wait for the continue button to be clickable and click it
    WebElement passwordContinueButton =
        wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.xpath(
                    "//button[@class='cl-formButtonPrimary cl-button 🔒️ cl-internal-8viq2k']")));
    passwordContinueButton.click();
    Thread.sleep(5000);

    // Close the browser
    driver.quit();

    // Log the test execution success
    System.out.println("SafariLogin successfully executed");
  }
}
