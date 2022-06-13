package ru.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.smart.dao.DealDAO;
import ru.smart.domain.Deal;
import ru.smart.service.dto.DealDTO;
import ru.smart.service.mapper.DealMapper;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DealService {

    private final DealDAO dealDAO;

    private final DealMapper dealMapper;

    private final UserService userService;

    public DealService(final DealDAO dealDAO, DealMapper dealMapper, UserService userService) {
        this.dealDAO = dealDAO;
        this.dealMapper = dealMapper;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public List<DealDTO> findAllWithSubject() {
        return dealDAO.findAllWithSubject().stream()
            .map(dealMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Transactional(readOnly = true)
    public Optional<DealDTO> findById(int id) {
        return this.dealDAO.findOne(id)
            .map(this.dealMapper::toDto);
    }

    @Transactional
    public DealDTO save(DealDTO dealDTO) {
        Deal deal = this.dealMapper.toEntity(dealDTO);
        deal.setCreated(GregorianCalendar.getInstance());
        deal.setProvided(GregorianCalendar.getInstance());
        deal.setUser(userService.getCurrentUser().orElseThrow(
                () -> new RuntimeException("User not found in context")
        ));
        this.dealDAO.create(deal);
        return this.dealMapper.toDto(deal);
    }

    @Transactional
    public void update(DealDTO dealDTO) {
        Deal deal = this.dealMapper.toEntity(dealDTO);
        this.dealDAO.delete(deal);
    }

    @Transactional
    public void deleteById(int id) {
        this.dealDAO.deleteById(id);
    }
}
