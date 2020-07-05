package me.coding.seata.business.service.impl;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import me.coding.seata.api.service.OrderService;
import me.coding.seata.api.service.StorageService;
import me.coding.seata.business.service.BusinessService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BusinessServiceImpl implements BusinessService {

    @Reference
    private StorageService storageService;

    @Reference
    private OrderService orderService;

    /**
     * 采购
     */
    @Override
    @GlobalTransactional(timeoutMills = 300000, name = "dubbo-gts-seata-example")
    public void purchase(String userId, String commodityCode, int orderCount) {

        storageService.deduct(commodityCode, orderCount);

        orderService.create(userId, commodityCode, orderCount);
    }
}
