package net.javaproject.registration_login.service;

import net.javaproject.registration_login.dto.UserDto;
import net.javaproject.registration_login.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);
    List<UserDto> getUsers();
}
