package me.coding.shardingjdbc.service;

import me.coding.shardingjdbc.entity.Order;
import me.coding.shardingjdbc.repository.DistrictRepository;
import me.coding.shardingjdbc.repository.OrderItemRepository;
import me.coding.shardingjdbc.repository.OrderRelationRepository;
import me.coding.shardingjdbc.repository.OrderRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private OrderRelationRepository orderRelationRepository;

    @Override
    @Transactional
    @ShardingTransactionType
    public void batchSave() {
//        for (int i = 0; i < 100; i++) {
        Order order = new Order();
        order.setUserId(RandomUtils.nextLong());
        order.setOrderName(RandomStringUtils.randomAlphabetic(5));
        order.setDistrictId(1L);
        orderRepository.save(order);

//            if (i == 32) {
//                System.out.println("==================== " + order.getOrderId());
//                throw new RuntimeException();
//            }

        throw new RuntimeException();

//            OrderItem orderItem = new OrderItem();
//            orderItem.setOrderId(order.getOrderId());
//            orderItem.setUserId(order.getUserId());
//            orderItem.setItemName(RandomStringUtils.randomAlphabetic(10));
//            orderItemRepository.save(orderItem);
//        }
    }
}
