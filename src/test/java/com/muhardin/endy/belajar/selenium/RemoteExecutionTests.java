package com.muhardin.endy.belajar.selenium;

import com.muhardin.endy.belajar.selenium.entity.Customer;
import com.muhardin.endy.belajar.selenium.entity.Education;
import com.muhardin.endy.belajar.selenium.entity.Gender;
import com.muhardin.endy.belajar.selenium.pageobject.CustomerList;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;
import org.junit.AfterClass;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;

public class RemoteExecutionTests {
    
    private static final String SELENIUM_HUB = "http://192.168.1.187:4444/wd/hub";
    private static final String SERVER_BASE_URL = "https://training-selenium.herokuapp.com";
    
    private static WebDriver webDriver;
    private static Customer[] sampleDataCustomer;
    
    @BeforeClass
    public static void inisialisasi() throws MalformedURLException{
        DesiredCapabilities capabilities = new DesiredCapabilities(new SafariOptions());
        //DesiredCapabilities capabilities = new DesiredCapabilities(new ChromeOptions());
        webDriver = new RemoteWebDriver(new URL(SELENIUM_HUB), capabilities);
        
        sampleDataCustomer = new Customer[]{
            new Customer(null, "Customer 990", "cust990@contoh.com", "0812345678990", LocalDate.of(2010, Month.MARCH, 10), Gender.MALE, Education.SMUSMK),
            new Customer(null, "Customer 991", "cust991@contoh.com", "0812345678991", LocalDate.of(2010, Month.APRIL, 22), Gender.FEMALE, Education.Doktor)
        };
    }
    
    @AfterClass
    public static void cleanup(){
        webDriver.quit();
    }
    
    @Before
    public void resetDatabase(){
        System.out.println("Reset database sesuai sample data");
        RestTemplate restClient = new RestTemplate();
        restClient.postForObject(SERVER_BASE_URL+"/devtools/db/customer", 
                sampleDataCustomer, Object.class);
        System.out.println("Selesai");
    }
    
    @Test
    public void testCustomerList() throws IOException{
        webDriver.get(SERVER_BASE_URL + "/customer/list");
        
        CustomerList screenDaftarCustomer = new CustomerList(webDriver);
        Assert.assertTrue(screenDaftarCustomer.jumlahRecord(2));
        
        WebDriver augmentedDriver = new Augmenter().augment(webDriver);
        File screenshot = ((TakesScreenshot)augmentedDriver).
                            getScreenshotAs(OutputType.FILE);
        
        String lokasiScreenshot = System.getProperty("user.home") 
                + File.separator 
                + UUID.randomUUID().toString() + ".png";
        System.out.println("Lokasi screenshot : "+lokasiScreenshot);
        
        File hasil = new File(lokasiScreenshot);
        FileCopyUtils.copy(screenshot, hasil);
    }
}
