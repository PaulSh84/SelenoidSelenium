import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SignUpPageTest {

  private RemoteWebDriver driver;

  @BeforeClass
  public void beforeClass() throws MalformedURLException {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("browserName", "chrome");
    capabilities.setCapability("browserVersion", "92.0");
    capabilities.setCapability("selenoid:options", new HashMap<String, Object>() {{
      put("enableVNC", true);
      put("enableVideo", true);
    }});
    driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), capabilities);
  }

  @Test
  public void allRequiredFieldsShouldBeFilled() {
    SignUpPage page = new SignUpPage(driver);
    page.open().acceptCookie()
        .typeEmail("test@test.com")
        .typeConfirmEmailField("test@test.com")
        .typePassword("test12345")
        .submit();
  }

  @AfterMethod
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}