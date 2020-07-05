package me.coding.seata.storage.mapper;

import me.coding.seata.storage.entity.Storage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StorageMapper {

    Storage getById(Integer id);
}
