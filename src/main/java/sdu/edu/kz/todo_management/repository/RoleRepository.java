package sdu.edu.kz.todo_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sdu.edu.kz.todo_management.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
