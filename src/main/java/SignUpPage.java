import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {
  private final RemoteWebDriver driver;
  private final WebDriverWait wait;
  private final By emailField = cssSelector("#email");
  private final By confirmEmailField = cssSelector("#confirm");
  private final By passwordField = cssSelector("#password");
  private final By displayNameField = cssSelector("#displayname");
  private final By monthDropDown = cssSelector("#register-dob-month");
  private final By dayField = cssSelector("#register-dob-day");
  private final By yearField = cssSelector("#register-dob-year");
  private final By shareCheckbox = cssSelector("#register-thirdparty");
  private final By registerButton = cssSelector("button");
  private final By cookiePopUp = cssSelector("div#onetrust-banner-sdk");
  private final By cookieAcceptButton = cssSelector(System.getProperty("cookieButtonElementPath"));

  public SignUpPage(RemoteWebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, 10);
  }

  public SignUpPage open() {
    driver.get("https://www.spotify.com/pl/signup/");
    return this;
  }

  public SignUpPage typeEmail(String email) {

    driver.findElement(emailField).sendKeys(email);
    return this;
  }

  public SignUpPage typeConfirmEmailField(String email) {
    driver.findElement(confirmEmailField).sendKeys(email);
    return this;}

  public SignUpPage typePassword(String password) {
    driver.findElement(passwordField).sendKeys(password);
    return this;}

  public SignUpPage typeName(String name) {return this;}

  public SignUpPage setMonth(String month) {
    return this;}

  public SignUpPage typeDay(String day) {return this;}

  public SignUpPage typeYear(String year) {return this;}

  public SignUpPage setShare(boolean share) {return this;}

  public void submit() {
    driver.findElement(registerButton).click();
  }

  public SignUpPage acceptCookie() {
    if (isCookiePopupAppearedAndReadyToBeClicked()) {
      driver.findElement(cookieAcceptButton).click();
    }
    return this;
  }

  private boolean isCookiePopupAppearedAndReadyToBeClicked() {
    try {
      WebElement popUp = wait.until(
              presenceOfElementLocated(cookiePopUp));
      System.out.println("pop up is located");
      System.out.println("Printing out inner html of the pop-up");
      System.out.println(popUp.getAttribute("innerHTML"));
      WebElement button = wait.until(
              elementToBeClickable(cookieAcceptButton));
      System.out.println("accept button clickable");
      return true;
    } catch (TimeoutException e){
      System.out.println("Timed out after 10 sec");
      return false;
    }
  }
}
