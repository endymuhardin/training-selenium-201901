package com.muhardin.endy.belajar.selenium;

import com.github.javafaker.Faker;
import com.muhardin.endy.belajar.selenium.entity.Education;
import com.muhardin.endy.belajar.selenium.entity.Gender;
import com.muhardin.endy.belajar.selenium.pageobject.CustomerForm;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        
        //Thread.sleep(10 * 1000);
        form.simpanData();
        
        (new WebDriverWait(webDriver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().equalsIgnoreCase("Data Customer");
            }
        });
    }
    
    @Test
    public void testInputNamaKosong() throws Exception {
        webDriver.get(SERVER_BASE_URL + "/customer/form");
        
        CustomerForm form = new CustomerForm(webDriver);
        
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
      
        //form.isiNama("a");
        form.isiEmail(firstname+"@"+faker.internet().domainName());
        form.isiNoHp(faker.phoneNumber().cellPhone());
        form.isiTanggalLahir(formatter.format(faker.date().birthday()));
        form.pilihGender(Gender.values()[random.nextInt(Gender.values().length)]);
        form.pilihPendidikan(Education.values()[random.nextInt(Education.values().length)]);
        
        //Thread.sleep(5 * 1000);
        form.simpanData();
        
        (new WebDriverWait(webDriver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().equalsIgnoreCase("Edit Customer");
            }
        });
        
        Assert.assertEquals("Harusnya keluar pesan tidak boleh kosong", "Nama harus diisi", form.fieldError("fullname"));
        Assert.assertEquals("Harusnya keluar pesan panjang nama kurang", "size must be between 3", form.fieldError("fullname"));
        
    }
    
    @Test
    public void testInputNamaPendek() throws Exception {
        webDriver.get(SERVER_BASE_URL + "/customer/form");
        
        CustomerForm form = new CustomerForm(webDriver);
        
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
      
        form.isiNama("a");
        form.isiEmail(firstname+"@"+faker.internet().domainName());
        form.isiNoHp(faker.phoneNumber().cellPhone());
        form.isiTanggalLahir(formatter.format(faker.date().birthday()));
        form.pilihGender(Gender.values()[random.nextInt(Gender.values().length)]);
        form.pilihPendidikan(Education.values()[random.nextInt(Education.values().length)]);
        
        //Thread.sleep(5 * 1000);
        form.simpanData();
        
        (new WebDriverWait(webDriver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().equalsIgnoreCase("Edit Customer");
            }
        });
        
        Assert.assertEquals("Pesan error kosong harusnya tidak tampil, karena ada inputan 1 huruf", "Nama harus diisi", 
                form.fieldError("fullname"));
        Assert.assertEquals("size must be between 3", form.fieldError("fullname"));
        
    }
}
