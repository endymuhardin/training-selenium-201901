package com.muhardin.endy.belajar.selenium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junitparams.mappers.IdentityMapper;

public class CustomerMapper extends IdentityMapper {

    @Override
    public Object[] map(Reader reader) {
        List<Object> customers = new ArrayList<>();
            
        try {
            
            BufferedReader lineReader = new BufferedReader(reader);
            String data;
            
            while((data = lineReader.readLine()) != null){
                String[] field = data.split(",");
                System.out.println("Jumlah field : "+field.length);
                for (String s : field) {
                    System.out.println("Field : "+s);
                }
                customers.add(field);
            }
        } catch (IOException ex) {
            Logger.getLogger(CustomerMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return customers.toArray();
    }
    
}