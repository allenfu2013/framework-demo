package me.coding.sample.sentinel;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.nacos.api.PropertyKeyConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Properties;

@Slf4j
@Component
public class NacosConfigInit implements CommandLineRunner {

    @Value("${nacos.server-address}")
    private String serverAddress;

    @Value("${nacos.namespace}")
    private String namespace;

    @Override
    public void run(String... args) throws Exception {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddress);
        properties.put(PropertyKeyConst.NAMESPACE, namespace);

        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(properties, "DEFAULT_GROUP", "sentinel-demo-sentinel-flow-rule",
                source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
                }));
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());

        ReadableDataSource<String, List<DegradeRule>> degradeRuleDataSource = new NacosDataSource<>(properties, "DEFAULT_GROUP", "sentinel-demo-sentinel-degrade-rule",
                source -> JSON.parseObject(source, new TypeReference<List<DegradeRule>>() {
                }));
        DegradeRuleManager.register2Property(degradeRuleDataSource.getProperty());
    }
}
