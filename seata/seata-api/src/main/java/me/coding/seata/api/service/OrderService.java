package me.coding.seata.api.service;

import me.coding.seata.api.vo.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    Order create(String userId, String commodityCode, int orderCount);
}
