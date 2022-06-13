package ru.smart.service.mapper;

import org.mapstruct.Mapper;
import ru.smart.domain.Deal;
import ru.smart.service.dto.DealDTO;

@Mapper(componentModel = "spring", uses = SubjectMapper.class)
public interface DealMapper extends EntityMapper<DealDTO, Deal> {

    default Deal fromId(Integer id) {
        if (id == null) {
            return null;
        }
        Deal deal = new Deal();
        deal.setId(id);
        return deal;
    }
}
