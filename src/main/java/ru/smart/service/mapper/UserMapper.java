package ru.smart.service.mapper;

import org.mapstruct.Mapper;
import ru.smart.domain.User;
import ru.smart.service.dto.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User> {

    default User fromId(Integer id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }
}
