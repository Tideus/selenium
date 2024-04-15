package ru.sharkvision.lesson5;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.sharkvision.TestBase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CheckCountriesOrderTest extends TestBase {

    @Test
    public void checkCountriesOrder() {
        adminLogin();

        List<WebElement> countryRows = driver.findElements(By.cssSelector("form[name='countries_form'] tr.row"));

        List<String> countries = new ArrayList<>();
        List<String> countryZones = new ArrayList<>();
        List<String> sortedCountries;

        // check country sorted
        for (WebElement countryRow : countryRows) {
            List<WebElement> cells = countryRow.findElements(By.tagName("td"));

            String country = cells.get(4).getText();
            int zones = Integer.parseInt(cells.get(5).getText());

            countries.add(country);
            if (zones > 0)
                countryZones.add(country);
        }
        sortedCountries = new ArrayList<>(countries).stream().sorted().collect(Collectors.toList());
        assertEquals(sortedCountries, countries, "Countries not sorted");


        for (String country : countryZones) {
            WebElement countryRow = driver.findElement(By.xpath("//td[.='" + country + "']/a"));
            countryRow.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table#table-zones")));

            countries.clear();
            sortedCountries.clear();
            countryRows = driver.findElements(By.cssSelector("#table-zones input[name$='][name]']"));

            // check country zones sorted
            for (WebElement countryZone : countryRows) {
                String countryName = countryZone.getAttribute("value");
                countries.add(countryName);
            }
            sortedCountries = new ArrayList<>(countries).stream().sorted().collect(Collectors.toList());
            assertEquals(sortedCountries, countries, "Countries not sorted");

            driver.get("https://sharkvision.ru/litecart/admin/?app=countries&doc=countries");
        }


    }

    public void adminLogin() {
        driver.get("https://sharkvision.ru/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(), 'You are now logged in as admin')]")));
    }

}
