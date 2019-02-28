package com.muhardin.endy.belajar.selenium.controller;

import com.muhardin.endy.belajar.selenium.dao.CustomerDao;
import com.muhardin.endy.belajar.selenium.entity.Customer;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    
    @Autowired private CustomerDao customerDao;
    
    @GetMapping("/list")
    public ModelMap dataCustomer(Pageable page){
        return new ModelMap().addAttribute("dataCustomer", customerDao.findAll(page));
    }
    
    @GetMapping("/form")
    public ModelMap displayFormEdit(){
        return new ModelMap().addAttribute(new Customer());
    }
    
    @PostMapping("/form")
    public String prosesFormEdit(@ModelAttribute @Valid Customer customer, BindingResult errors, SessionStatus status){
        System.out.println("Birthdate " + customer.getBirthdate());
        
        if(errors.hasErrors()){
            return "customer/form";
        }
        
        customerDao.save(customer);
        status.setComplete();
        return "redirect:list";
    }
    
}
