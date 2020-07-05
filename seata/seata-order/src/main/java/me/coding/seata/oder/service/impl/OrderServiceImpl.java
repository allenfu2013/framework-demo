package me.coding.seata.oder.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.coding.seata.api.service.AccountService;
import me.coding.seata.api.service.OrderService;
import me.coding.seata.api.vo.Order;
import me.coding.seata.oder.mapper.OrderMapper;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Reference
    private AccountService accountService;

    @PostConstruct
    private void init() {
        me.coding.seata.oder.entity.Order order = orderMapper.getById(1);
        System.out.println("===================" + order);
    }

    @Override
    public Order create(String userId, String commodityCode, int orderCount) {
        log.info("OrderService create");
        accountService.debit(userId, 0);
        return new Order();
    }
}
