package com.muhardin.endy.belajar.selenium.controller;

import com.muhardin.endy.belajar.selenium.entity.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    
    @GetMapping("/list")
    public void dataCustomer(){
        
    }
    
    @GetMapping("/form")
    public ModelMap displayFormEdit(){
        return new ModelMap().addAttribute(new Customer());
    }
    
    @PostMapping("/form")
    public String prosesFormEdit(){
        return "redirect:list";
    }
    
}
