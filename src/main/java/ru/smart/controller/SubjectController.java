package ru.smart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.smart.service.SubjectService;
import ru.smart.service.dto.SubjectDTO;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(final SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/subject")
    public ResponseEntity<List<SubjectDTO>> findAll() {
        return new ResponseEntity<>(
            this.subjectService.findAll(),
            HttpStatus.OK
        );
    }
}
