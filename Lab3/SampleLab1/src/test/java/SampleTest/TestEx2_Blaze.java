package SampleTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import java.util.HashMap;
import java.util.Map;

public class TestEx2_Blaze {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;

  @BeforeEach
  public void setUp() {
    // using default in PATH instead
    System.setProperty("webdriver.gecko.driver", "/home/ricardo/Desktop/TQS/TQS/Lab3/geckodriver");
    driver = new FirefoxDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }

  @AfterEach
  public void tearDown() {
    driver.quit();
  }

  @Test
  void buy_tickets_boston_ny() {
    driver.get("https://blazedemo.com/");
    driver.manage().window().setSize(new Dimension(909, 842));
    assertThat(driver.findElement(By.cssSelector("h1")).getText(), is("Welcome to the Simple Travel Agency!"));
    assertThat(driver.findElement(By.name("fromPort")).getText(), is("Paris\nPhiladelphia\nBoston\nPortland\nSan Diego\nMexico City\nSão Paolo"));
    assertThat(driver.findElement(By.name("toPort")).getText(), is("Buenos Aires\nRome\nLondon\nBerlin\nNew York\nDublin\nCairo"));
    {
      WebElement dropdown = driver.findElement(By.name("fromPort"));
      dropdown.findElement(By.xpath("//option[. = 'Boston']")).click();
    }
    driver.findElement(By.cssSelector(".form-inline:nth-child(1) > option:nth-child(3)")).click();
    driver.findElement(By.name("toPort")).click();
    {
      WebElement dropdown = driver.findElement(By.name("toPort"));
      dropdown.findElement(By.xpath("//option[. = 'New York']")).click();
    }
    driver.findElement(By.cssSelector(".form-inline:nth-child(4) > option:nth-child(5)")).click();
    driver.findElement(By.cssSelector(".btn-primary")).click();
    assertThat(driver.findElement(By.cssSelector("h3")).getText(), is("Flights from Boston to New York:"));
    driver.findElement(By.cssSelector("tr:nth-child(1) .btn")).click();
    assertThat(driver.findElement(By.cssSelector("p:nth-child(3)")).getText(), is("Flight Number: UA954"));
    driver.findElement(By.id("inputName")).click();
    driver.findElement(By.id("inputName")).sendKeys("Jonh Smith");
    driver.findElement(By.id("city")).click();
    driver.findElement(By.id("city")).sendKeys("Boston");
    driver.findElement(By.id("creditCardNumber")).click();
    driver.findElement(By.id("creditCardNumber")).sendKeys("123432123");
    driver.findElement(By.cssSelector(".btn-primary")).click();
    assertThat(driver.getTitle(), is("BlazeDemo Confirmation"));
  }
}

