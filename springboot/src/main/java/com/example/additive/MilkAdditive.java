package com.example.additive;

import com.example.beverage.Beverage;

public class MilkAdditive extends Additive { 
    
    Beverage beverage; 
    Integer num;
    Double price = 1.0;
    
    public MilkAdditive(Beverage beverage, Integer num){ 
        this.beverage = beverage; 
        this.num = num;
    } 
    
    public String getDescription() { 
        return beverage.getDescription() + String.format(" 和 Milk %d份", num);
    } 
    
    public double getCost() { 
        return beverage.getCost() + price * num;   
    } 
}