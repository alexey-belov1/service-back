package ru.smart.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserDTO {

    @EqualsAndHashCode.Include
    private int id;

    @NotNull
    private String login;

    @NotNull
    private String password;

    @NotNull
    private Calendar created;
}
