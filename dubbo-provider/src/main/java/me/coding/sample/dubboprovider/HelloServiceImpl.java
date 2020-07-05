package me.coding.sample.dubboprovider;

import lombok.extern.slf4j.Slf4j;
import me.coding.sample.dubboapi.Address;
import me.coding.sample.dubboapi.HelloService;
import me.coding.sample.dubboapi.User;
import org.apache.dubbo.config.annotation.Service;

@Slf4j
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        System.out.println("@@@@@@@@@@@@@@@ provider @@@@@@@@@@@@@@");
        return "hello " + name;
    }

    @Override
    public Address getAddress(User user) {
        Address address = new Address();
        address.setId(1L);
        address.setHome("wuhan");
        return address;
    }

}
