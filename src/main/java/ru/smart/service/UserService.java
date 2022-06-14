package ru.smart.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.smart.dao.UserDAO;
import ru.smart.domain.User;
import ru.smart.errors.LoginAlreadyUsedException;
import ru.smart.security.SecurityUtils;
import ru.smart.service.dto.UserDTO;
import ru.smart.service.mapper.UserMapper;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserDAO userDAO;

    private final BCryptPasswordEncoder encoder;

    private final UserMapper userMapper;

    public UserService(final UserDAO userDAO, BCryptPasswordEncoder encoder, UserMapper userMapper) {
        this.userDAO = userDAO;
        this.encoder = encoder;
        this.userMapper = userMapper;
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        return userDAO.findAll().stream()
            .map(userMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Transactional(readOnly = true)
    public Optional<UserDTO> findById(int id) {
        return this.userDAO.findOne(id)
            .map(this.userMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<User> findByLogin(String login) {
        return this.userDAO.findByLogin(login);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public UserDTO save(UserDTO userDTO) {
        User user = this.userMapper.toEntity(userDTO);
        if (this.userDAO.findByLogin(user.getLogin()).isPresent()) {
            throw new LoginAlreadyUsedException();
        }
        user.setCreated(GregorianCalendar.getInstance());
        user.setPassword(this.encoder.encode(user.getPassword()));
        user = this.userDAO.create(user);
        return this.userMapper.toDto(user);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(UserDTO userDTO) {
        User user = this.userMapper.toEntity(userDTO);
        this.userDAO.delete(user);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deleteById(int id) {
        this.userDAO.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Optional<User> getCurrentUser() {
        return SecurityUtils.getCurrentUserLogin().flatMap(userDAO::findByLogin);
    }
}
