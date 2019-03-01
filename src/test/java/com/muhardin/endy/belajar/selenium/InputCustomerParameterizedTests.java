package com.muhardin.endy.belajar.selenium;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.muhardin.endy.belajar.selenium.entity.Customer;
import com.muhardin.endy.belajar.selenium.entity.Education;
import com.muhardin.endy.belajar.selenium.entity.Gender;
import com.muhardin.endy.belajar.selenium.pageobject.CustomerForm;
import com.muhardin.endy.belajar.selenium.pageobject.CustomerList;
import java.text.SimpleDateFormat;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.web.client.RestTemplate;

@RunWith(JUnitParamsRunner.class)
public class InputCustomerParameterizedTests {
    
    private static final String SERVER_BASE_URL = "https://training-selenium.herokuapp.com";
    private static Customer[] sampleDataCustomer;
    
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    
    private static WebDriver webDriver;
    
    @BeforeClass
    public static void bukaBrowser() throws Exception {
        webDriver = new FirefoxDriver();
        System.out.println("Membaca sample data");
        ObjectMapper objectMapper = new ObjectMapper();
        sampleDataCustomer = objectMapper
                .readValue(
                        InputCustomerParameterizedTests.class
                                .getResourceAsStream("/sample-customer.json"), 
                        Customer[].class);
    }
    
    @AfterClass
    public static void tutupBrowser(){
        webDriver.quit();
    }
    
    @Before
    public void resetDatabase() throws Exception {
        System.out.println("Reset database sesuai sample data");
        RestTemplate restClient = new RestTemplate();
        restClient.postForObject(SERVER_BASE_URL+"/devtools/db/customer", 
                sampleDataCustomer, Object.class);
        System.out.println("Selesai");
    }
    
    //@Test
    public void testHelloWorld(){
        System.out.println("Hello World");
    }
    
    
    @Test
    @FileParameters("src/test/resources/test-form-customer.csv")
    public void testInputCustomer(String nama, 
            String email, String hp, String tanggal, 
            String gender, String pendidikan, 
            Boolean sukses, String fieldError, 
            String pesanError) throws Exception {
        
        webDriver.get(SERVER_BASE_URL + "/customer/form");
        
        CustomerForm form = new CustomerForm(webDriver);
        form.isiNama(nama);
        form.isiEmail(email);
        form.isiNoHp(hp);
        form.isiTanggalLahir(tanggal);
        form.pilihGender(Gender.valueOf(gender));
        form.pilihPendidikan(Education.valueOf(pendidikan));
        
        //Thread.sleep(10 * 1000);
        form.simpanData();
        
        (new WebDriverWait(webDriver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().equalsIgnoreCase("Data Customer");
            }
        });
        
        
        // sort by name
        webDriver.get(SERVER_BASE_URL + "/customer/list?sort=fullname,desc");
        
        CustomerList halamanDataCustomer = new CustomerList(webDriver);
        Assert.assertTrue("Judul halaman seharusnya Data Customer", 
                halamanDataCustomer.periksaTitle("Data Customer"));
        
        Assert.assertTrue("Jumlah record harusnya 22", 
                halamanDataCustomer.jumlahRecord(22));
        
        Assert.assertTrue("Yang barusan diinsert harusnya muncul paling atas",
                halamanDataCustomer.cekNamaCustomerBaru(nama));
        
    }
}
