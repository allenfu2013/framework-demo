package me.coding.seata.business.service;

public interface BusinessService {

    void purchase(String userId, String commodityCode, int orderCount);
}
