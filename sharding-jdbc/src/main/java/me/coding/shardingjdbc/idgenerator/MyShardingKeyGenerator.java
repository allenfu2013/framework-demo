package me.coding.shardingjdbc.idgenerator;

import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

public class MyShardingKeyGenerator implements ShardingKeyGenerator {

    private AtomicLong atomic = new AtomicLong(0);
    private Properties properties = new Properties();

    @Override
    public Comparable<?> generateKey() {
        return atomic.incrementAndGet();
    }

    @Override
    public String getType() {
        return "SIMPLE";
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
