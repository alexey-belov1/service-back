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
import ru.smart.service.UserService;
import ru.smart.service.dto.UserDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDTO>> findAll() {
        return new ResponseEntity<>(
            this.userService.findAll(),
            HttpStatus.OK
        );
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable int id) {
        Optional<UserDTO> dto = this.userService.findById(id);
        return new ResponseEntity<>(
                dto.orElseGet(UserDTO::new),
                HttpStatus.OK
        );
    }

    @PostMapping("/user/sign-up")
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(
                this.userService.save(userDTO),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/user")
    public ResponseEntity<Void> update(@RequestBody UserDTO userDTO) {
        this.userService.update(userDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        this.userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
