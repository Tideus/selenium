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


public class CheckGeoZonesOrderTest extends TestBase {

    @Test
    public void checkGeoZoneOrder() {
        adminLogin();

        List<WebElement> countryRows = driver.findElements(By.cssSelector("form[name='geo_zones_form'] table tr.row"));

        List<String> countries = countryRows.stream()
                .map(item -> item.findElements(By.tagName("td")).get(2).getText())
                .collect(Collectors.toList());


        for (String country : countries) {
            WebElement countryRow = driver.findElement(By.xpath("//td[.='" + country + "']/a"));
            countryRow.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table#table-zones")));

            List<WebElement> zonesOptions = driver.findElements(By.cssSelector("select[name$='][zone_code]'] option[selected=selected]"));

            List<String> zones = zonesOptions.stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());

            List<String> sortedZones = new ArrayList<>(zones).stream().sorted().collect(Collectors.toList());
            assertEquals(sortedZones, zones, "Countries not sorted");

            driver.get("https://sharkvision.ru/litecart/admin/?app=geo_zones&doc=geo_zones");
        }
    }

    public void adminLogin() {
        driver.get("https://sharkvision.ru/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(), 'You are now logged in as admin')]")));
    }

}
