package com.example.additive;

import com.example.beverage.Beverage;

public class MilkAdditive extends Additive { 
    
    Beverage beverage; 
    
    public MilkAdditive(Beverage beverage){ 
        this.beverage = beverage; 
    } 
    
    public String getDescription() { 
        return beverage.getDescription()+" 和 Milk";  
    } 
    
    public double getCost() { 
        return beverage.getCost() + 1;   
    } 
}