package me.coding.sample.elasticjob;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegisterCenterConfig {

    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter regCenter() {
        return new ZookeeperRegistryCenter(new ZookeeperConfiguration("192.168.66.41:2181", "default"));
    }
}
