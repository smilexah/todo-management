package sdu.edu.kz.todo_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sdu.edu.kz.todo_management.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
}
