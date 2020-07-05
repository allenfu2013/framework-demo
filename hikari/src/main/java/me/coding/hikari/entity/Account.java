package me.coding.hikari.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "t_account")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userId;

    private Double amount;

    private String comment;
}
