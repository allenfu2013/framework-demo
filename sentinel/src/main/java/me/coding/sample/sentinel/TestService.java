package me.coding.sample.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestService {


    @SentinelResource(value = "flow-rule-1")
    public void test1() {
        log.info("flow rules:{}", FlowRuleManager.getRules());
    }
}
