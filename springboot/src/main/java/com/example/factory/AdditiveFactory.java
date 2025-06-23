package com.example.factory;

import com.example.additive.IceAdditive;
import com.example.additive.MilkAdditive;
import com.example.additive.NoAdditive;
import com.example.beverage.Beverage;
import org.springframework.stereotype.Component;


@Component
public class AdditiveFactory implements Factory { 
    
    private String additive; 
    private Beverage beverage; 

    public Beverage getAdditive(String additive, Beverage beverage){ 
        this.additive = additive; 
        this.beverage = beverage; 
        
        if(additive.equals("milk")) beverage = new MilkAdditive(beverage); 
        else if(additive.equals("ice")) beverage = new IceAdditive(beverage); 
        else beverage = new NoAdditive(); 
        
        return beverage; 
    } 
}    