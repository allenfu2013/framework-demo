package me.coding.shardingjdbc.algorithm;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;

public class MyComplexKeysShardingAlgorithm implements ComplexKeysShardingAlgorithm<Long> {


    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Long> shardingValue) {
        return null;
    }
}
