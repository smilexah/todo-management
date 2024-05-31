package sdu.edu.kz.todo_management.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sdu.edu.kz.todo_management.dto.ToDoDTO;
import sdu.edu.kz.todo_management.service.ToDoService;

import java.util.List;

@CrossOrigin("*")
@Controller
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class ToDoController {
    private final ToDoService toDoService;

    @PostMapping("/")
    public ResponseEntity<Void> addToToDo(@RequestBody ToDoDTO toDoDto) {
        toDoService.addToToDo(toDoDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateToDo(@PathVariable Long id, @RequestBody ToDoDTO toDoDto) {
        try {
            toDoService.updateToDo(id, toDoDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDo(@PathVariable Long id) {
        try {
            toDoService.deleteToDo(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoDTO> getToDo(@PathVariable Long id) {
        return new ResponseEntity<>(toDoService.getToDoById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<ToDoDTO>> getAllToDos() {
        return new ResponseEntity<>(toDoService.getAllToDos(), HttpStatus.OK);
    }

    @PatchMapping("{id}/complete")
    public ResponseEntity<Void> completeTodo(@PathVariable Long id) {
        toDoService.completeTodo(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("{id}/in-complete")
    public ResponseEntity<Void> inCompleteTodo(@PathVariable Long id) {
        toDoService.inCompleteTodo(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
