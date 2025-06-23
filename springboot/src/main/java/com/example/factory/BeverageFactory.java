package com.example.factory;

import com.example.beverage.Beverage;
import com.example.beverage.Coca;
import com.example.beverage.Coffee;
import com.example.beverage.NoBeverage;

import org.springframework.stereotype.Component;


@Component
public class BeverageFactory implements Factory{ 
  
    private String beverageName; 
    private Beverage beverage; 
    
    public Beverage getBeverage(String beverageName){ 
        this.beverageName = beverageName; 
        if(beverageName.equals("coca")) beverage = new Coca(); 
        else if(beverageName.equals("coffee")) beverage = new Coffee(); 
        else beverage = new NoBeverage(); 
        return beverage; 
    } 
} 