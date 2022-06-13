package ru.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.smart.dao.SubjectDAO;
import ru.smart.service.dto.SubjectDTO;
import ru.smart.service.mapper.SubjectMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    private final SubjectDAO subjectDAO;

    private final SubjectMapper subjectMapper;

    public SubjectService(final SubjectDAO subjectDAO, SubjectMapper subjectMapper) {
        this.subjectDAO = subjectDAO;
        this.subjectMapper = subjectMapper;
    }

    @Transactional(readOnly = true)
    public List<SubjectDTO> findAll() {
        return subjectDAO.findAll().stream()
            .map(subjectMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }
}
