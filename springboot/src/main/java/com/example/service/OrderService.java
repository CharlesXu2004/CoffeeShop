package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.additive.NoAdditive;
import com.example.beverage.NoBeverage;
import com.example.beverage.Beverage;
import com.example.entity.Order;
import com.example.factory.AdditiveFactory;
import com.example.factory.BeverageFactory;
import com.example.mapper.OrderMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Base64.Decoder;

/**
 * 业务层方法
 */
@Service
public class OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private BeverageFactory beverageFactory;

    @Resource
    private AdditiveFactory additiveFactory;

    public void add(Order order) {
        orderMapper.insert(order);
    }

    public void updateById(Order order) {
        orderMapper.updateById(order);
    }

    public void deleteById(Integer id) {
        orderMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            orderMapper.deleteById(id);
        }
    }

    public Order selectById(Integer id) {
        return orderMapper.selectById(id);
    }

    public List<Order> selectAll(Order order) {
        return orderMapper.selectAll(order);
    }

    public PageInfo<Order> selectPage(Order order, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Order> list = orderMapper.selectAll(order);
        return PageInfo.of(list);
    }

    public Order processOrder(String beverageName, String additiveName, Integer additiveNum) {

        String description;
        Double cost;

        Beverage beverage = beverageFactory.getBeverage(beverageName);
        description = beverage.getDescription();
        cost = beverage.getCost();

        if (!(beverage instanceof NoBeverage)){

            beverage = additiveFactory.getAdditive(additiveName, beverage);
            description = beverage.getDescription();
            cost = beverage.getCost();

            if (!(beverage instanceof NoAdditive)) {
                description += String.format(" %d份", additiveNum);
                for (int i = 1; i < additiveNum; i++) beverage = additiveFactory.getAdditive(additiveName, beverage);
                cost = beverage.getCost();
            } 

        }

        Order order = new Order();
        order.setDescription(description);
        order.setCost(cost);

        return order;
    }

}
