package ru.smart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.smart.controller.util.HeaderUtil;
import ru.smart.service.DealService;
import ru.smart.service.dto.DealDTO;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DealController {

    private final DealService dealService;

    public DealController(final DealService dealService) {
        this.dealService = dealService;
    }

    @GetMapping("/deal")
    public ResponseEntity<List<DealDTO>> findAllWithSubjectAndFiltersForCurrentUser() {
        return new ResponseEntity<>(
            this.dealService.findAllWithSubjectAndFiltersForCurrentUser(),
            HttpStatus.OK
        );
    }

    @GetMapping("/deal/{id}")
    public ResponseEntity<DealDTO> findById(@PathVariable int id) {
        Optional<DealDTO> dto = this.dealService.findById(id);
        return new ResponseEntity<>(
                dto.orElseGet(DealDTO::new),
                HttpStatus.OK
        );
    }

    @PostMapping("/deal")
    public ResponseEntity<DealDTO> create(@Valid @RequestBody DealDTO dealDTO) {
        DealDTO result = dealService.save(dealDTO);
        return new ResponseEntity<>(
                result,
                result.getId() != 0
                        ? HeaderUtil.createSuccessAlert("dealCreated", String.valueOf(result.getId()))
                        : HeaderUtil.createWarningAlert("dealRejected", ""),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/deal")
    public ResponseEntity<Void> update(@Valid @RequestBody DealDTO dealDTO) {
        this.dealService.update(dealDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deal/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        this.dealService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
