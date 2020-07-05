package me.coding.sample.eventdriven.controller;

import lombok.extern.slf4j.Slf4j;
import me.coding.sample.eventdriven.event.NotifyPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private NotifyPublisher notifyPublisher;

    @GetMapping("/sayHello")
    public String sayHello() {
        log.info("/sayHello");
        notifyPublisher.publishEvent(1, "我发布了一个事件");
        return "Hello Word";
    }

    @GetMapping("/test")
    public Map test(@RequestParam String n) {
        System.out.println(n);
        Map<String, Object> ret = new HashMap<>();
        ret.put("status", 0);
        return ret;
    }
}
