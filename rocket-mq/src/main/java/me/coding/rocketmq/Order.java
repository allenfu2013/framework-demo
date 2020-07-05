package me.coding.rocketmq;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Order {

    private String orderNo;

    private String userId;

    private String commodityCode;

    private Integer count;

    private BigDecimal amount;
}
