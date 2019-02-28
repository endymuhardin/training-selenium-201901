package com.muhardin.endy.belajar.selenium;

import com.github.javafaker.Faker;
import com.muhardin.endy.belajar.selenium.entity.Education;
import com.muhardin.endy.belajar.selenium.entity.Gender;
import com.muhardin.endy.belajar.selenium.pageobject.CustomerForm;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class InputCustomerTests {
    private static final String SERVER_BASE_URL = "https://training-selenium.herokuapp.com";
    
    private static WebDriver webDriver;
    
    private Faker faker = new Faker();
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private Random random = new Random();
    
    @BeforeClass
    public static void bukaBrowser(){
        webDriver = new FirefoxDriver();
    }
    
    @AfterClass
    public static void tutupBrowser(){
        webDriver.quit();
    }
    
    @Test
    public void testInputNormal() throws Exception {
        webDriver.get(SERVER_BASE_URL + "/customer/form");
        
        CustomerForm form = new CustomerForm(webDriver);
        
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
        
        form.isiNama(firstname + " "+lastname);
        form.isiEmail(firstname+"@"+faker.internet().domainName());
        form.isiNoHp(faker.phoneNumber().cellPhone());
        form.isiTanggalLahir(formatter.format(faker.date().birthday()));
        form.pilihGender(Gender.values()[random.nextInt(Gender.values().length)]);
        form.pilihPendidikan(Education.values()[random.nextInt(Education.values().length)]);
        
        Thread.sleep(10 * 1000);
        form.simpanData();
    }
}
