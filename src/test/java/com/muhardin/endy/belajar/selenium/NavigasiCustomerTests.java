package com.muhardin.endy.belajar.selenium;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigasiCustomerTests {
    private static final String SERVER_BASE_URL = "http://localhost:8080";
    
    private static WebDriver webDriver;
    
    @BeforeClass
    public static void bukaBrowser(){
        webDriver = new FirefoxDriver();
    }
    
    @AfterClass
    public static void tutupBrowser(){
        webDriver.quit();
    }
    
    @Test
    public void testNavigasiSimpanCustomer() throws Exception {
        webDriver.get(SERVER_BASE_URL + "/customer/list");
        WebElement linkKeFormCustomer = webDriver.findElement(By.linkText("Customer Baru"));
        linkKeFormCustomer.click();
        
        (new WebDriverWait(webDriver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().equalsIgnoreCase("Edit Customer");
            }
        });
        
        // isi form
        WebElement inputNama = webDriver.findElement(By.name("fullname"));
        inputNama.sendKeys("Endy Muhardin");
        
        WebElement inputEmail = webDriver.findElement(By.name("email"));
        inputEmail.sendKeys("endy.muhardin@gmail.com");
        
        WebElement inputHp = webDriver.findElement(By.name("mobilePhone"));
        inputHp.sendKeys("+6281234567890");
        
        WebElement radioPria = webDriver.findElement(By.id("rbPria"));
        inputHp.sendKeys("+6281234567890");
        
        WebElement inputTanggalLahir = webDriver.findElement(By.name("birthdate"));
        inputHp.sendKeys("1945-08-17");
        
        WebElement tombolSimpan = webDriver.findElement(By.id("simpanCustomer"));
        tombolSimpan.click();
        
        
        (new WebDriverWait(webDriver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().equalsIgnoreCase("Data Customer");
            }
        });
        
        Thread.sleep(5 * 1000);
    }
}
