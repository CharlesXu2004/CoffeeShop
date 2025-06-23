package com.example.entity;

import lombok.Data;

@Data
public class Order {
    private Integer id;
    private String userId;
    private String description;
    private Double cost;
}
