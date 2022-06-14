package ru.smart.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DealDTO {

    @EqualsAndHashCode.Include
    private int id;

    @NotNull
    private SubjectDTO subject;

    @NotNull
    private String fio;

    @NotNull
    private String email;

    private Calendar created;

    private Calendar provided;
}
