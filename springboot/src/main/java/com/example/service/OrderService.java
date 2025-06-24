package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.additive.NoAdditive;
import com.example.beverage.NoBeverage;
import com.example.beverage.Beverage;
import com.example.entity.Order;
import com.example.entity.OrderAdditive;
import com.example.factory.AdditiveFactory;
import com.example.factory.BeverageFactory;
import com.example.mapper.OrderMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import com.example.entity.OrderRequest;
import com.example.entity.OrderAdditive;

import java.util.List;
import java.util.Base64.Decoder;

/**
 * 业务层方法
 */
@Service
@Slf4j
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

    public Order processOrder(OrderRequest orderRequest) {

        Order order = new Order();
        String description;
        Double cost;

        String beverageName = orderRequest.getBeverageName();

        Beverage beverage = beverageFactory.getBeverage(beverageName);
        description = beverage.getDescription();
        cost = beverage.getCost();

        if (!(beverage instanceof NoBeverage)){

            OrderAdditive good = new OrderAdditive();
            good.setName(beverageName);
            good.setNum(1);
            good.setPrice(cost);
            order.getGoods().add(good);

            for (OrderAdditive additive : orderRequest.getAdditives()) {

                Double tempCost = cost;

                String additiveName = additive.getName();
                Integer additiveNum = additive.getNum();

                beverage = additiveFactory.getAdditive(additiveName, beverage, additiveNum);
                description = beverage.getDescription();
                cost = beverage.getCost();

                if (beverage instanceof NoAdditive) break;

                good = new OrderAdditive();
                good.setName(additiveName);
                good.setNum(additiveNum);
                good.setPrice((cost - tempCost) / additiveNum);
                order.getGoods().add(good);
            }
        }

        order.setDescription(description);
        order.setCost(cost);

        return order;
    }

}
