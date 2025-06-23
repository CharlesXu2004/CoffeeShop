package com.example.beverage;

public class NoBeverage extends Beverage { 

    String description = "没有您所点的饮料，请重新点(coca或coffee)。";

    public String getDescription(){ 
        return description; 
    }

    public double getCost() { 
        return 0; 
    }
} 