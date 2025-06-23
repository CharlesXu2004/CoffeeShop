package com.example.controller;

import com.example.common.Result;
import com.example.entity.Order;
import com.example.service.OrderService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前端请求接口
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/processOrder")
    public Result processOrder(@RequestParam String beverageName, 
                               @RequestParam String additiveName,
                               @RequestParam Integer additiveNum) {
        Order order = orderService.processOrder(beverageName, additiveName, additiveNum);
        return Result.success(order);
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Order order) {
        orderService.add(order);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result update(@RequestBody Order order) {
        orderService.updateById(order);
        return Result.success();
    }

    /**
     * 单个删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        orderService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        orderService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 单个查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Order order = orderService.selectById(id);
        return Result.success(order);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Order order) {
        List<Order> list = orderService.selectAll(order);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Order order,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Order> pageInfo = orderService.selectPage(order, pageNum, pageSize);
        return Result.success(pageInfo);
    }

}
