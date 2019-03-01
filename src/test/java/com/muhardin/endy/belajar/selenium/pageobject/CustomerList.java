package com.muhardin.endy.belajar.selenium.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerList {
    
    @FindBy(xpath = "/html/body/div[1]/div/main/div/section/h1")
    private WebElement judulHalaman;
    
    @FindBy(xpath = "/html/body/div[1]/div/main/div/section/div[2]/div/div[1]/div")
    private WebElement pagingInfo;
    
    @FindBy(xpath = "/html/body/div[1]/div/main/div/section/div[2]/table/tbody/tr[1]/td[1]")
    private WebElement namaCustomerBaru;
    
    public CustomerList(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }
    
    public Boolean periksaTitle(String title){
        return title.equals(judulHalaman.getText());
    }
    
    public Boolean jumlahRecord(Integer jumlah){
        return pagingInfo.getText().contains(jumlah + " entries");
    }
    
    public Boolean cekNamaCustomerBaru(String nama){
        return nama.equals(namaCustomerBaru.getText())
    }
}
