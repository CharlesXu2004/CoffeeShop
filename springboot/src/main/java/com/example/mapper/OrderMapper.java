package com.example.mapper;

import com.example.entity.Order;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper {

    int insert(Order order);

    void updateById(Order order);

    void deleteById(Integer id);

    @Select("select * from `order` where id = #{id}")
    Order selectById(Integer id);

    List<Order> selectAll(Order order);
}
