package ru.sharkvision.lesson2;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.sharkvision.TestBase;

public class LoginTest extends TestBase {

    @Test
    public void adminSuccessfulLogin() {
        driver.get("https://sharkvision.ru/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(), 'You are now logged in as admin')]")));
    }
}
