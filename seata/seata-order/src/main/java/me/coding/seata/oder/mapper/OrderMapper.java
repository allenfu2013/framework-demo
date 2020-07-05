package me.coding.seata.oder.mapper;

import me.coding.seata.oder.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderMapper {

    Order getById(Integer id);
}
