package com.example.entity;

import java.util.List;
import lombok.Data;

@Data
public class OrderRequest {

    private String beverageName;
    private List<OrderAdditive> additives;
}