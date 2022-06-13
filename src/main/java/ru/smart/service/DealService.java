package ru.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.smart.dao.DealDAO;
import ru.smart.dao.EmailTaskDAO;
import ru.smart.dao.SubjectDAO;
import ru.smart.domain.Deal;
import ru.smart.domain.EmailTask;
import ru.smart.domain.Subject;
import ru.smart.domain.User;
import ru.smart.service.dto.DealDTO;
import ru.smart.service.filter.FilterDB;
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
    private final EmailService emailService;
    private final EmailTaskDAO emailTaskDAO;
    private final SubjectDAO subjectDAO;

    public DealService(
            DealDAO dealDAO,
            DealMapper dealMapper,
            UserService userService,
            EmailService emailService,
            EmailTaskDAO emailTaskDAO,
            SubjectDAO subjectDAO) {
        this.dealDAO = dealDAO;
        this.dealMapper = dealMapper;
        this.userService = userService;
        this.emailService = emailService;
        this.emailTaskDAO = emailTaskDAO;
        this.subjectDAO = subjectDAO;
    }

    @Transactional(readOnly = true)
    public List<DealDTO> findAllWithSubjectAndFiltersForCurrentUser() {
        User user = userService.getCurrentUser().orElseThrow(
                () -> new RuntimeException("User not found in context")
        );
        FilterDB filter = new FilterDB("userIdEquals");
        filter.setParam("user_id", user.getId());
        List<FilterDB> filters = List.of(filter);
        return dealDAO.findAllWithSubjectAndFilters(filters).stream()
            .map(dealMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Transactional(readOnly = true)
    public Optional<DealDTO> findById(int id) {
        return this.dealDAO.findOne(id)
            .map(this.dealMapper::toDto);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public DealDTO save(DealDTO dealDTO) {

        Subject subject = this.subjectDAO.findOne(dealDTO.getSubject().getId()).orElseThrow(
                () -> new RuntimeException("Subject not found")
        );
        if(subject.getReservedCount() <= subject.getProvidedCount()) {
            return new DealDTO();
        }
        subject.incProvidedCount();
        this.subjectDAO.update(subject);

        Deal deal = this.dealMapper.toEntity(dealDTO);
        deal.setCreated(GregorianCalendar.getInstance());
        deal.setProvided(GregorianCalendar.getInstance());
        deal.setUser(userService.getCurrentUser().orElseThrow(
                () -> new RuntimeException("User not found in context")
        ));
        this.dealDAO.create(deal);

        EmailTask emailTask = EmailTask.builder()
                .recipient(dealDTO.getEmail())
                .theme("Регистрация услуги")
                .text("Услуга зарегистрирована под номером " + deal.getId())
                .build();
        this.emailTaskDAO.create(emailTask);
        this.emailService.send(emailTask);
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

    private boolean checkSubjectLimit(DealDTO dealDTO) {
        Subject subject = this.subjectDAO.findOne(dealDTO.getSubject().getId()).orElseThrow(
                () -> new RuntimeException("Subject not found")
        );
        return subject.getReservedCount() > subject.getProvidedCount();
    }
}
