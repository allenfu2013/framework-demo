package me.coding.shardingjdbc.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "t_district")
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String province;

    @Column
    private String city;
}
