package ptua;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.NoSuchElementException;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SearchStepsBlaze {
    private WebDriver webDriver;

    @When("I go to {string}")
    public void iGoTo(String url) {
        WebDriverManager.firefoxdriver().setup();
        webDriver = new FirefoxDriver();
        webDriver.get(url);
    }

    @And("I check that title is {string}")
    public void iCheckTitle(String searchQuery) {
        assertThat(webDriver.findElement(By.cssSelector("h1")).getText(), is(searchQuery));
    }

    @And("I check the flights available from a location to another are {string} and {string}")
    public void iCheckFlights(String flights_from, String flights_to) {
        assertThat(webDriver.findElement(By.name("fromPort")).getText(), is(flights_from));
        assertThat(webDriver.findElement(By.name("toPort")).getText(), is(flights_to));
    }

    @Then("I want to book a flight from {string} to {string}")
    public void iWantToBookAFlight(String booked_location_from, String booked_location_to) {
        {
            WebElement dropdown = webDriver.findElement(By.name("fromPort"));
            dropdown.findElement(By.xpath("//option[. = '" + booked_location_from + "']")).click();
        }

        webDriver.findElement(By.cssSelector(".form-inline:nth-child(1) > option:nth-child(3)")).click();
        webDriver.findElement(By.name("toPort")).click();

        {
            WebElement dropdown = webDriver.findElement(By.name("toPort"));
            dropdown.findElement(By.xpath("//option[. = '" + booked_location_to + "']")).click();
        }
    }

    @Then("I confirm the flights from {string} to {string}")
    public void iBookAFlight(String booked_location_from, String booked_location_to) {
        webDriver.findElement(By.cssSelector(".form-inline:nth-child(4) > option:nth-child(5)")).click();
        webDriver.findElement(By.cssSelector(".btn-primary")).click();
        assertThat(webDriver.findElement(By.cssSelector("h3")).getText(), is("Flights from " + booked_location_from + " to " + booked_location_to + ":"));
        webDriver.findElement(By.cssSelector("tr:nth-child(1) .btn")).click();
        assertThat(webDriver.findElement(By.cssSelector("p:nth-child(3)")).getText(), is("Flight Number: UA954"));
    }

    @And("I fullfill all the information needed")
    public void iFullFillInformation() {
        webDriver.findElement(By.id("inputName")).click();
        webDriver.findElement(By.id("inputName")).sendKeys("Jonh Smith");
        webDriver.findElement(By.id("city")).click();
        webDriver.findElement(By.id("city")).sendKeys("Boston");
        webDriver.findElement(By.id("creditCardNumber")).click();
        webDriver.findElement(By.id("creditCardNumber")).sendKeys("123432123");
        webDriver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @Then("I expect the confirmation {string}")
    public void iExpectConfirmation(String confirmationTitle) {
        assertThat(webDriver.getTitle(), is(confirmationTitle));
        webDriver.quit();
    }
}
