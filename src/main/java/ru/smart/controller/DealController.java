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
import ru.smart.service.DealService;
import ru.smart.service.dto.DealDTO;

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
    public ResponseEntity<List<DealDTO>> findAllWithSubject() {
        return new ResponseEntity<>(
            this.dealService.findAllWithSubject(),
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
    public ResponseEntity<DealDTO> create(@RequestBody DealDTO dealDTO) {
        return new ResponseEntity<>(
                dealService.save(dealDTO),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/deal")
    public ResponseEntity<Void> update(@RequestBody DealDTO dealDTO) {
        this.dealService.update(dealDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deal/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        this.dealService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
