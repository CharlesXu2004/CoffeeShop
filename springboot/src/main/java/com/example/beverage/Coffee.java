package com.example.beverage;

public class Coffee extends Beverage { 

    //这里是Coffee本身的描述和价格 
    public Coffee(){ 
        description = "Coffee Beverage"; 
    } 

    public double getCost() { 
        return 9.9; 
    } 
} 