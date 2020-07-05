package me.coding.sample.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping(value = "/test1")
    public Map<String, Object> test1() {
        log.info("flow rules:{}", FlowRuleManager.getRules());
        Map<String, Object> res = new HashMap<>();
        try (Entry entry = SphU.entry("test1")) {
            log.info("test1 looks good");
            res.put("test1", true);
        } catch (BlockException ex) {
            log.error("test1 blocked", ex);
            res.put("test1", false);
        }
        return res;
    }

    @SentinelResource(value = "test2", blockHandler = "handleTest2", blockHandlerClass = {ExceptionUtil.class})
    @GetMapping("/test2")
    public Map<String, Object> test2() {
        log.info("flow rules:{}", FlowRuleManager.getRules());
        Map<String, Object> res = new HashMap<>();
        res.put("test2", true);
        return res;
    }

    @SentinelResource(value = "test3", fallback = "test3Fallback")
    @GetMapping("/test3")
    public Map<String, Object> test3(@RequestParam int a) {
        log.info("degrade rules:{}", DegradeRuleManager.getRules());
        if (a < 0) {
            throw new IllegalArgumentException("invalid arg");
        }
        Map<String, Object> res = new HashMap<>();
        res.put("test3", true);
        return res;
    }

    @GetMapping("/test4")
    public Map<String, Object> test4() {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        testService.test1();
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                    }
                }
            });
            thread.start();
        }
        Map<String, Object> res = new HashMap<>();
        res.put("test3", true);
        return res;
    }

    public Map<String, Object> test3Fallback(int a, Throwable ex) {
        // Do some log here.
        log.warn("fallback start, a:{}", a);
        Map<String, Object> res = new HashMap<>();
        res.put("test3", false);
        return res;
    }
}
