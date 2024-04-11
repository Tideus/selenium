package ru.sharkvision.lesson2;

import org.junit.jupiter.api.Test;
import ru.sharkvision.TestBase;

public class MyFirstTest extends TestBase {

    @Test
    public void myFirstTest() {
        driver.get("https://google.com");
    }

}
