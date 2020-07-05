package me.coding.seata.storage.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.coding.seata.api.service.StorageService;
import me.coding.seata.storage.entity.Storage;
import me.coding.seata.storage.mapper.StorageMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageMapper storageMapper;

    @PostConstruct
    private void init() {
        Storage storage = storageMapper.getById(1);
        System.out.println("==========================" + storage);
    }

    @Override
    public void deduct(String commodityCode, int count) {
        log.info("StorageService deduct");
    }
}
