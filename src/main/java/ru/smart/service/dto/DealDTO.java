package ru.smart.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Calendar;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DealDTO {

    @EqualsAndHashCode.Include
    private int id;

    private SubjectDTO subject;

    private String fio;

    private String email;

    private Calendar created;

    private Calendar provided;
}
