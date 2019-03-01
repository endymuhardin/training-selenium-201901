package com.muhardin.endy.belajar.selenium.controller;

import com.muhardin.endy.belajar.selenium.dao.CustomerDao;
import com.muhardin.endy.belajar.selenium.entity.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("development")
@RestController
@RequestMapping("/devtools")
public class ResetDatabaseController {
    
    @Autowired
    private CustomerDao customerDao;
    
    @PostMapping("/db/customer")
    public void resetAndInsertSampleDataCustomer(@RequestBody List<Customer> dataCustomer){
        // delete semua customer
        customerDao.deleteAll();
        
        // insert sample data customer untuk ngetes duplicate dan delete
        customerDao.saveAll(dataCustomer);
    }
}
