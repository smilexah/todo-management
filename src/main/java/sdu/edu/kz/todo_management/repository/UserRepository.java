package sdu.edu.kz.todo_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sdu.edu.kz.todo_management.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
    User findUserByUsernameAndPassword(String username, String password);
    Optional<User> findUserByPassword(String password);
}
