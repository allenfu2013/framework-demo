package me.coding.seata.business.controller;

import lombok.extern.slf4j.Slf4j;
import me.coding.seata.business.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @PostMapping("/purchase")
    public void purchase() {
        businessService.purchase("1", "2", 3);
    }
}
