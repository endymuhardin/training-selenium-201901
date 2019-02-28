package com.muhardin.endy.belajar.selenium.dao;

import com.muhardin.endy.belajar.selenium.entity.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerDao extends PagingAndSortingRepository<Customer, String> {
    
}
