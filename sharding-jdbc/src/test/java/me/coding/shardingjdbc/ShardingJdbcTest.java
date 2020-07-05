package me.coding.shardingjdbc;

import com.google.common.collect.Lists;
import me.coding.shardingjdbc.entity.District;
import me.coding.shardingjdbc.entity.Order;
import me.coding.shardingjdbc.entity.OrderItem;
import me.coding.shardingjdbc.entity.OrderRelation;
import me.coding.shardingjdbc.repository.DistrictRepository;
import me.coding.shardingjdbc.repository.OrderItemRepository;
import me.coding.shardingjdbc.repository.OrderRelationRepository;
import me.coding.shardingjdbc.repository.OrderRepository;
import me.coding.shardingjdbc.service.OrderService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJdbcTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private OrderRelationRepository orderRelationRepository;

    @Autowired
    private OrderService orderService;

    @Test
    public void test1() {
        List<Order> orderList = orderRepository.findByUserId(4670907639432674605L);
        System.out.println(orderList);

        System.out.println("==============================");

        Optional<Order> order = orderRepository.findById(476800202850697217L);
        if (order.isPresent()) {
            System.out.println(order);
        }
    }

    @Test
    public void test2() throws Exception {
        for (int i = 0; i < 100; i++) {
            Order order = new Order();
            order.setUserId(RandomUtils.nextLong());
            order.setOrderName(RandomStringUtils.randomAlphabetic(5));
            order.setDistrictId(1L);
            orderRepository.save(order);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getOrderId());
            orderItem.setUserId(order.getUserId());
            orderItem.setItemName(RandomStringUtils.randomAlphabetic(10));
            orderItemRepository.save(orderItem);
        }
    }

    @Test
    public void test3() {
        PageRequest pageRequest = PageRequest.of(10, 10, Sort.Direction.DESC, "orderId");
        Page<Order> page = orderRepository.findAll(pageRequest);
        System.out.println(page.getContent());
    }

    @Test
    @Transactional
    public void test4() {
        Order order = new Order();
        order.setUserId(RandomUtils.nextLong());
        order.setOrderName(RandomStringUtils.randomAlphabetic(5));
        orderRepository.save(order);

        Optional<Order> order1 = orderRepository.findById(1L);
        order1.ifPresent(o -> System.out.println(o));
    }

    @Test
    public void test5() {
//        Object o = orderRepository.findByUserIdAndOrderId(3192173255045992187L, 476814537744449536L);
//        System.out.println(JSON.toJSONString(o));

        List<Order> orders = orderRepository.findByUserIdAndOrderIdIn(6817046234994128033L, Lists.newArrayList(477047584439926784L, 477047585815658497L));
        System.out.println(orders);
    }

    @Test
    public void test6() {
        District district = new District();
        district.setProvince("Hu Bei");
        district.setCity("Wu Han");
        districtRepository.save(district);

//        Optional<District> optional = districtRepository.findById(1L);
//        if (optional.isPresent()) {
//            System.out.println(optional.get());
//        }
    }

    @Test
    public void test7() {
        OrderRelation or = new OrderRelation();
        or.setOrderId(1L);
        or.setOrderName("vjTQG");
        orderRelationRepository.save(or);
    }

    @Test
    public void test8() {
        OrderRelation or = orderRelationRepository.findOneByOrderName("vjTQG");
        System.out.println(or);

        Optional<Order> optional = orderRepository.findById(or.getOrderId());
        optional.ifPresent(o -> System.out.println(o));
    }

    @Test
    public void test9() {
        orderService.batchSave();
    }

}
