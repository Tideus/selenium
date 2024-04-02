package ru.sharkvision;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyFirstTest extends TestBase {

    @Test
    public void myFirstTest() {
        driver.get("https://google.com");
    }

}
