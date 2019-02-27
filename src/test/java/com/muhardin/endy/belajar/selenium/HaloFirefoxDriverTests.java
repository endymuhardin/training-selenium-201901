package com.muhardin.endy.belajar.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HaloFirefoxDriverTests {
    
    @Test
    public void testGooglingDenganFirefox() throws Exception {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.google.com");
        WebElement inputanSearch = driver.findElement(By.name("q"));
        
        final String yangDicari = "blog endy";
        
        inputanSearch.sendKeys(yangDicari);
        inputanSearch.submit();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith(yangDicari);
            }
        });
        
        System.out.println("Page title is: " + driver.getTitle());
        
        Thread.sleep(5 * 1000);
        
        driver.quit();
    }
}
