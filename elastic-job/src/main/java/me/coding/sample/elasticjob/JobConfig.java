package me.coding.sample.elasticjob;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {

    private final String cron = "0/5 * * * * ?";
    private final int shardingTotalCount = 3;
    private final String shardingItemParameters = "0=A,1=B,2=C";
    private final String jobParameters = "parameter";

    @Autowired
    private ZookeeperRegistryCenter regCenter;

    @Bean
    public SimpleJob stockJob() {
        return new TestJob();
    }

    @Bean(initMethod = "init")
    public JobScheduler simpleJobScheduler(final SimpleJob simpleJob) {
        return new SpringJobScheduler(simpleJob, regCenter, getLiteJobConfiguration(simpleJob.getClass(),
                cron, shardingTotalCount, shardingItemParameters, jobParameters));
    }

    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends SimpleJob> jobClass,
                                                         final String cron,
                                                         final int shardingTotalCount,
                                                         final String shardingItemParameters,
                                                         final String jobParameters) {
        // 定义作业核心配置
        JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration.
                newBuilder(jobClass.getName(), cron, shardingTotalCount)
                .description("测试任务1描述")
                .shardingItemParameters(shardingItemParameters)
                .jobParameter(jobParameters)
                .build();

        // 定义SIMPLE类型配置
        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig, jobClass.getCanonicalName());

        // 定义Lite作业根配置
        LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig)
                // 本地配置是否可覆盖注册中心配置, 默认false
                .overwrite(false)
                // 设置作业是否启动时禁止.
                // 可用于部署作业时, 先在启动时禁止, 部署结束后统一启动
                .disabled(true)
                .build();
        return simpleJobRootConfig;

    }
}
