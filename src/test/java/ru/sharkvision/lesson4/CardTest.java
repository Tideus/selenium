package ru.sharkvision.lesson4;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.sharkvision.TestBase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest extends TestBase {

    @Test
    public void checkStickerOnCard() {

        driver.get("https://sharkvision.ru/litecart");
        List<WebElement> productCards = driver.findElements(By.cssSelector(".listing-wrapper.products li"));

        for (WebElement productCard : productCards) {
            String textCard = productCard.findElement(By.cssSelector(".name")).getText();
            List<WebElement> stickers = productCard.findElements(By.cssSelector("div[class*=sticker]"));
            assertEquals(1, stickers.size(), textCard + " has more than 1 sticker");
        }
    }

}
