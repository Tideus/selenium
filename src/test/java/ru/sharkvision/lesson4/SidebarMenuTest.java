package ru.sharkvision.lesson4;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.sharkvision.TestBase;

import java.util.List;
import java.util.stream.Collectors;

public class SidebarMenuTest extends TestBase {

    @Test
    public void clickSidebarMenu() {
        adminLogin();

        List<WebElement> menuItemsList = driver.findElements(By.cssSelector("#box-apps-menu li#app-"));
        List<String> textMenuItems = menuItemsList.stream()
                .map(item -> item.findElement(By.cssSelector("span.name")).getText())
                .collect(Collectors.toList());

        for (String textItem : textMenuItems) {
            WebElement menuItem = driver.findElement(By.xpath("//span[.='" + textItem + "']"));
            menuItem.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content h1")));

            List<WebElement> menuSubItemsList = driver.findElements(By.cssSelector("ul.docs li"));
            List<String> textSubItems = menuSubItemsList.stream()
                    .map(item -> item.findElement(By.cssSelector("span.name")).getText())
                    .collect(Collectors.toList());

            for (String textSubItem : textSubItems) {
                WebElement subItem = driver.findElement(By.xpath("//span[.='" + textSubItem + "']"));
                subItem.click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content h1")));
            }

        }
    }


    public void adminLogin() {
        driver.get("https://sharkvision.ru/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(), 'You are now logged in as admin')]")));
    }
}
