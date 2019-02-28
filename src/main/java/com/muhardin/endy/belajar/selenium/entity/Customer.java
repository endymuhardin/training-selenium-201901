package com.muhardin.endy.belajar.selenium.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity @Data
public class Customer {
    
    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String fullname;
    private String email;
    private String mobilePhone;
    
    private LocalDate birthdate;
    
    @Enumerated(EnumType.STRING)
    private Gender gender; 
    
    @Enumerated(EnumType.STRING)
    private Education education;
}
