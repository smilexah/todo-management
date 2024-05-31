package sdu.edu.kz.todo_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sdu.edu.kz.todo_management.entity.ToDo;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
