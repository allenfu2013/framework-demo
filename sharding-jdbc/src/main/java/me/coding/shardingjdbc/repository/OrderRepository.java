package me.coding.shardingjdbc.repository;

import me.coding.shardingjdbc.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);

    @Query(nativeQuery = true, value = "select o.order_id, o.order_name, o.user_id, i.item_name, d.province, d.city from t_order o inner join t_order_item i on o.order_id = i.order_id inner join t_district d on o.district_id = d.id  where o.user_id = ?1 and o.order_id = ?2")
    Object findByUserIdAndOrderId(Long userId, Long orderId);

    List<Order> findByUserIdAndOrderIdIn(Long userId, List<Long> orderIds);

    Page<Order> findAll(Pageable pageable);
}
