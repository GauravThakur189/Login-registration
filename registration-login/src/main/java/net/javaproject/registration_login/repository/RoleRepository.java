package net.javaproject.registration_login.repository;

import net.javaproject.registration_login.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role ,Long> {

    Role findByName(String name);
}
