package net.javaproject.registration_login.service.impl;

import net.javaproject.registration_login.dto.UserDto;
import net.javaproject.registration_login.entity.Role;
import net.javaproject.registration_login.entity.User;
import net.javaproject.registration_login.repository.RoleRepository;
import net.javaproject.registration_login.repository.UserRepository;
import net.javaproject.registration_login.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName()+" "+ userDto.getLastName());
        user.setEmail(userDto.getEmail());
        //encrypt the password using spring security
        user.setPassword(userDto.getPassword());

        Role role  = roleRepository.findByName("ROLE_ADMIN");
        if(role==null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);

    }
    private  Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
