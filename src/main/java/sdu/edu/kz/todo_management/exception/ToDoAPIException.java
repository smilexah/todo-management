package sdu.edu.kz.todo_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ToDoAPIException extends RuntimeException {
    public ToDoAPIException(String message) {
        super(message);
    }
}
