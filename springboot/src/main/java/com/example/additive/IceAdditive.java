package com.example.additive;

import com.example.beverage.Beverage;

public class IceAdditive extends Additive { 
    
    Beverage beverage; 
    
    public IceAdditive(Beverage beverage){ 
        this.beverage = beverage; 
    } 
    
    public String getDescription() { 
        return beverage.getDescription()+" 和 Ice";  
    } 
    
    public double getCost() { 
        return beverage.getCost() + 0.5;   
    } 
}