package me.coding.sample.dubboapi;

import lombok.Data;

import java.io.Serializable;

@Data
public class Address implements Serializable {

    private Long id;

    private String home;
}
