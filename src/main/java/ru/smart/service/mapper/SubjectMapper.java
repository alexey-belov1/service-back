package ru.smart.service.mapper;

import org.mapstruct.Mapper;
import ru.smart.domain.Subject;
import ru.smart.service.dto.SubjectDTO;

@Mapper(componentModel = "spring")
public interface SubjectMapper extends EntityMapper<SubjectDTO, Subject> {

    default Subject fromId(Integer id) {
        if (id == null) {
            return null;
        }
        Subject subject = new Subject();
        subject.setId(id);
        return subject;
    }
}
