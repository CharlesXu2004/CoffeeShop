package com.example.additive;

public class NoAdditive extends Additive { 

    String description = "没有您所点的配料，请重新点(milk 或ice)。"; 
    
    public String getDescription() { 
        return this.description; 
    }
    public double getCost() { 
        return 0; 
    }
} 