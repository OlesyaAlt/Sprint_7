package ru.yandex.praktikum.models;

import lombok.Data;

@Data
public class Courier {
    private Integer id;
    private String login;
    private String password;
    private String firstName;

}
