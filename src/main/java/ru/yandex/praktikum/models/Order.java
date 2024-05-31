package ru.yandex.praktikum.models;

import lombok.Data;

import java.util.Arrays;

@Data
public class Order {
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private Arrays[] color;

    public String setColor(String color) {
        return Arrays.toString(this.color);
    }
}
