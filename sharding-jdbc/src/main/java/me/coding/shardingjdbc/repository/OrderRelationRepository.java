package me.coding.shardingjdbc.repository;

import me.coding.shardingjdbc.entity.OrderRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRelationRepository extends JpaRepository<OrderRelation, Long> {

    OrderRelation findOneByOrderName(String orderName);
}
