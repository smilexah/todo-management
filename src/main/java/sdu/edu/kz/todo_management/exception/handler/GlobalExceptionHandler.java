package sdu.edu.kz.todo_management.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import sdu.edu.kz.todo_management.dto.ErrorDTO;
import sdu.edu.kz.todo_management.exception.ResourceNotFoundException;
import sdu.edu.kz.todo_management.exception.ToDoAPIException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String RESOURCE_NOT_FOUND = "RESOURCE_NOT_FOUND";
    private static final String FORBIDDEN = "FORBIDDEN";
    private static final String BAD_REQUEST = "BAD_REQUEST";

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest) {
        ErrorDTO errorDetails = new ErrorDTO();
        errorDetails.setTimeStamp(LocalDateTime.now());
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setDetails(webRequest.getDescription(false));
        errorDetails.setErrorCode(RESOURCE_NOT_FOUND);

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
//    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorDTO> handleGenericException(Exception exception,
                                                           WebRequest webRequest) {
        ErrorDTO errorDetails = new ErrorDTO();
        errorDetails.setTimeStamp(LocalDateTime.now());
        errorDetails.setMessage(exception.getMessage());
        errorDetails.setDetails(webRequest.getDescription(false));
        errorDetails.setErrorCode(FORBIDDEN);

        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ToDoAPIException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleToDoAPIException(ToDoAPIException ex, WebRequest webRequest) {
        ErrorDTO errorDetails = new ErrorDTO();
        errorDetails.setTimeStamp(LocalDateTime.now());
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setDetails(webRequest.getDescription(false));
        errorDetails.setErrorCode(BAD_REQUEST);

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
