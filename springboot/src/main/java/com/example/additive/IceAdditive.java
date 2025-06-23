package com.example.additive;

import com.example.beverage.Beverage;

public class IceAdditive extends Additive { 
    
    Beverage beverage; 
    Integer num;
    Double price = 0.5;
    
    public IceAdditive(Beverage beverage, Integer num){ 
        this.beverage = beverage; 
        this.num = num;
    } 
    
    public String getDescription() { 
        return beverage.getDescription() + String.format(" 和 Ice %d份", num);
    } 
    
    public double getCost() { 
        return beverage.getCost() + price * num;   
    } 
}