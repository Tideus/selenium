package ru.sharkvision;

import org.junit.jupiter.api.Test;

public class MyFirstTest extends TestBase {

    @Test
    public void myFirstTest() {
        driver.get("https://google.com");
    }

}
