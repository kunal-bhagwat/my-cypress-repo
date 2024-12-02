package com.strique.pages.loginpage;

import com.strique.pages.BasePageTest;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

/** Class for login test case, extending the base class for setup. */
public class LoginPageTest extends BasePageTest {

  /**
   * Test method for logging in. This uses the Page Object Model (POM) to interact with the login
   * page elements.
   */
  @Test
  public void signIn() throws InterruptedException {
    logger.info("********Starting LoginTest********");

    // Create an instance of the LoginPage object
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    LoginPage lp = new LoginPage(driver);

    logger.info("Entering login credentials");

    // Wait for the username field to be visible
    WebElement striqueUserEmail =
        wait.until(ExpectedConditions.visibilityOf(lp.striqueUserEmail()));
    striqueUserEmail.sendKeys("arya.khadakkar@strique.io");

    // Wait for the continue button to be clickable and click it
    WebElement userContinueButton =
        wait.until(ExpectedConditions.elementToBeClickable(lp.userContinueButton()));
    userContinueButton.click();

    // Wait for the password field to load and send password
    WebElement striqueUserPassword =
        wait.until(ExpectedConditions.visibilityOf(lp.striqueUserPassword()));
    striqueUserPassword.sendKeys("AryaK@123456");

    // Wait for the second continue button to be clickable and click it
    WebElement passwordContinueButton =
        wait.until(ExpectedConditions.elementToBeClickable(lp.passwordContinueButton()));
    passwordContinueButton.click();

    logger.info("Login completed");

    // Close the browser after completing the login steps
    driver.close();
  }
}
