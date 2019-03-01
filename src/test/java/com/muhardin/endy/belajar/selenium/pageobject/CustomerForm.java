package com.muhardin.endy.belajar.selenium.pageobject;

import com.muhardin.endy.belajar.selenium.entity.Education;
import com.muhardin.endy.belajar.selenium.entity.Gender;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CustomerForm {
    
    @FindBy(xpath = "/html/body/div[1]/div/main/div/div/h1")
    private WebElement judulScreen;
    
    @FindBy(name = "fullname")
    private WebElement fullname;
    
    @FindBy(name = "email")
    private WebElement email;
    
    @FindBy(name = "mobilePhone")
    private WebElement mobilePhone;
    
    @FindBy(id = "gender1")
    private WebElement rbMale;
    
    @FindBy(id = "gender2")
    private WebElement rbFemale;
    
    @FindBy(name = "education")
    private WebElement education;
    
    @FindBy(name = "birthdate")
    private WebElement birthdate;
    
    @FindBy(id="simpanCustomer")
    private WebElement tombolSimpan;
    
    @FindBy(xpath = "/html/body/div[1]/div/main/div/div/div/div/form/div[1]/div/div")
    private WebElement pesanErrorNama;
    
    @FindBy(xpath = "/html/body/div[1]/div/main/div/div/div/div/form/div[2]/div/div")
    private WebElement pesanErrorEmail;
    
    public Boolean cekJudul(String judul){
        return judul.equals(judulScreen.getText());
    }
    
    public String fieldError(String namaField) {
        
        WebElement field = null;
        
        if("fullname".equals(namaField)) {
            field = pesanErrorNama;
        }
        
        if("email".equals(namaField)) {
            field = pesanErrorEmail;
        }
        
        if(field == null) {
            return null;
        }
        
        return field.getText();
    }
    
    public void isiNama(String nama){
        fullname.sendKeys(nama);
    }
    
    public void isiEmail(String x){
        email.sendKeys(x);
    }
    
    public void isiNoHp(String x){
        mobilePhone.sendKeys(x);
    }
    
    public void isiTanggalLahir(String x){
        birthdate.sendKeys(x);
    }
    
    public void pilihGender(Gender g){
        if(Gender.MALE.equals(g)){
            rbMale.click();
        }
        
        if(Gender.FEMALE.equals(g)){
            rbFemale.click();
        }
    }
    
    public void pilihPendidikan(Education edu){
        new Select(education).selectByValue(edu.name());
    }
        
    public void simpanData() {
        tombolSimpan.click();
    }
    
    public CustomerForm(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

}
