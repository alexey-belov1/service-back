package ru.smart.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Calendar;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserDTO {

    @EqualsAndHashCode.Include
    private int id;

    private String login;

    private String password;

    private Calendar created;
}
