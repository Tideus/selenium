package ru.sharkvision.lesson5;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.sharkvision.TestBase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CheckProductCardTest extends TestBase {

    @Test
    public void checkProductCard() {
        driver.get("https://sharkvision.ru/litecart/en/");

        // Main card
        WebElement productCard = driver.findElement(By.cssSelector("#box-campaigns li"));
        WebElement priceCard = productCard.findElement(By.className("regular-price"));
        WebElement salePriceCard = productCard.findElement(By.className("campaign-price"));

        String mainTitle = productCard.findElement(By.className("name")).getText();
        int mainPrice = Integer.parseInt(priceCard.getText().replace("$", ""));
        int mainSale = Integer.parseInt(salePriceCard.getText().replace("$", ""));

        String mainPriceColor = priceCard.getCssValue("color");
        String mainSaleColor = salePriceCard.getCssValue("color");

        productCard.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#breadcrumbs")));

        //Product card
        priceCard = driver.findElement(By.className("regular-price"));
        salePriceCard = driver.findElement(By.className("campaign-price"));

        String title = driver.findElement(By.tagName("h1")).getText();
        int price = Integer.parseInt(priceCard.getText().replace("$", ""));
        int salePrice = Integer.parseInt(salePriceCard.getText().replace("$", ""));

        String priceColor = priceCard.getCssValue("color");
        String salePriceColor = salePriceCard.getCssValue("color");

        //Asserts
        assertEquals(mainTitle, title, "На главной странице и на странице товара название не совпадает");
        assertEquals(mainPrice, price, "На главной странице и на странице товара цены не совпадает");
        assertTrue(mainPrice > mainSale, "На главной странице акционная цена крупнее, чем обычная");
        assertTrue(price > salePrice, "На странице товара акционная цена крупнее, чем обычная");

        assertTrue(checkGrayColor(mainPriceColor), "Цвет цены на главной страницы не серый");
        assertTrue(checkRedColor(mainSaleColor), "Цвет цены на главной страницы не красный");

        assertTrue(checkGrayColor(priceColor), "Цвет цены на странице товара не серый");
        assertTrue(checkRedColor(salePriceColor), "Цвет цены на странице товара не красный");
    }

    private boolean checkRedColor(String rgbaColor) {
        int[] rgbValues = getRGBValues(rgbaColor);
        int G = rgbValues[1];
        int B = rgbValues[2];

        return G == B && G == 0;
    }

    private boolean checkGrayColor(String rgbaColor) {
        int[] rgbValues = getRGBValues(rgbaColor);
        int R = rgbValues[0];
        int G = rgbValues[1];
        int B = rgbValues[2];

        return R == G && G == B;
    }

    private static int[] getRGBValues(String cssValue) {
        int[] rgbValues = new int[3];
        Pattern pattern = Pattern.compile("rgba?\\((\\d+),\\s*(\\d+),\\s*(\\d+)");
        Matcher matcher = pattern.matcher(cssValue);
        if (matcher.find()) {
            rgbValues[0] = Integer.parseInt(matcher.group(1));
            rgbValues[1] = Integer.parseInt(matcher.group(2));
            rgbValues[2] = Integer.parseInt(matcher.group(3));
        }
        return rgbValues;
    }


}
