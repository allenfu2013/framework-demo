package me.coding.shardingjdbc.controller;

import me.coding.shardingjdbc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/test1")
    public void test1() {
        orderService.batchSave();
    }
}
