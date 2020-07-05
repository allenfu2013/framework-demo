package me.coding.shardingjdbc.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "t_order_relation")
@EntityListeners(AuditingEntityListener.class)
public class OrderRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    private String orderName;

    @CreatedDate
    private Date createdTime;
}
