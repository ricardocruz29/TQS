package SampleTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

class TestEx1{
    WebDriver browser = null;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "/home/ricardo/Desktop/TQS/TQS/Lab3/geckodriver");
        this.browser = new FirefoxDriver();
    }

    @AfterEach
    public void tearDown(){
        this.browser.close();
    }

    @Test
    public void site_header_is_on_home_page() {
        this.browser.get("https://www.saucelabs.com");
        WebElement href = this.browser.findElement(By.xpath("//a[@href='https://accounts.saucelabs.com/']"));
        assertTrue((href.isDisplayed()));

    }
}