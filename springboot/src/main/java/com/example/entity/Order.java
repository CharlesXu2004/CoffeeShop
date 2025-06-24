package com.example.entity;

import java.util.List;
import java.util.ArrayList;

import lombok.Data;

@Data
public class Order {
    private Integer id;
    private String description;
    private Double cost;
    private List<OrderAdditive> goods = new ArrayList<>();
}
