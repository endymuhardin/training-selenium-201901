package com.muhardin.endy.belajar.selenium.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    
    @GetMapping("/list")
    public void dataCustomer(){
        
    }
    
    @GetMapping("/form")
    public void displayFormEdit(){}
    
}
