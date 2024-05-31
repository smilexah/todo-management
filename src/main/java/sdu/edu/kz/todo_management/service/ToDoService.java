package sdu.edu.kz.todo_management.service;

import sdu.edu.kz.todo_management.dto.ToDoDTO;

import java.util.List;

public interface ToDoService {
    void addToToDo(ToDoDTO toDoDto);
    List<ToDoDTO> getAllToDos();
    void deleteToDo(Long id);
    void updateToDo(Long id, ToDoDTO toDoDTO);
    ToDoDTO getToDoById(Long id);
    void completeTodo(Long id);
    void inCompleteTodo(Long id);
}
