package ru.sharkvision.lesson3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {


    public WebDriver driver;
    public WebDriverWait wait;


    public WebDriver getDriver(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        return driver;
    }

    @AfterEach
    public void stopDriver() {
        this.driver.quit();
        this.driver = null;
    }


    @Test
    public void adminLoginChrome() {
        WebDriver chromeDriver = getDriver(new ChromeDriver());
        adminLogin(chromeDriver);
    }

    @Test
    public void adminLoginFirefox() {
        WebDriver firefoxDriver = getDriver(new FirefoxDriver());
        adminLogin(firefoxDriver);
    }

    @Test
    public void adminLoginEdge() {
        System.setProperty("webdriver.edge.driver", "C://Tools//msedgedriver.exe");
        WebDriver edgeDriver = getDriver(new EdgeDriver());
        adminLogin(edgeDriver);
    }


    public void adminLogin(WebDriver driver) {
        driver.get("https://sharkvision.ru/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(), 'You are now logged in as admin')]")));
    }
}
