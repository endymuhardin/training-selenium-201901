package com.muhardin.endy.belajar.selenium;

import com.muhardin.endy.belajar.selenium.entity.Education;
import com.muhardin.endy.belajar.selenium.entity.Gender;
import com.muhardin.endy.belajar.selenium.pageobject.CustomerForm;
import java.text.SimpleDateFormat;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

@RunWith(JUnitParamsRunner.class)
public class InputCustomerParameterizedTests {
    
    private static final String SERVER_BASE_URL = "https://training-selenium.herokuapp.com";
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    
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
    }
}
