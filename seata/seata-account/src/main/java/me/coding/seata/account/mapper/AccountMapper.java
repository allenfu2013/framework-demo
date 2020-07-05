package me.coding.seata.account.mapper;

import me.coding.seata.account.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AccountMapper {

    Account getById(Integer id);
}
