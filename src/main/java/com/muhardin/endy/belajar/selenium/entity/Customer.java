package com.muhardin.endy.belajar.selenium.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity @Data
public class Customer {
    
    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @NotEmpty @Size(min = 3)
    private String fullname;
    
    @NotEmpty @Size(min = 6) @Email
    private String email;
    
    @NotEmpty @Size(min = 5, max = 13)
    private String mobilePhone;
    
    @NotNull @Past
    private LocalDate birthdate;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender; 
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private Education education;
}
