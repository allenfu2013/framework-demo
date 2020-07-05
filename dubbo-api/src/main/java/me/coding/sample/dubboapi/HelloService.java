package me.coding.sample.dubboapi;

public interface HelloService {

    String hello(String name);

    Address getAddress(User user);
}
