package sdu.edu.kz.todo_management.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import sdu.edu.kz.todo_management.dto.ErrorDTO;
import sdu.edu.kz.todo_management.exception.ResourceNotFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String RESOURCE_NOT_FOUND = "RESOURCE_NOT_FOUND";
    private static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";

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
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDTO> handleGenericException(Exception exception,
                                                               WebRequest webRequest) {
        ErrorDTO errorDetails = new ErrorDTO();
        errorDetails.setTimeStamp(LocalDateTime.now());
        errorDetails.setMessage(exception.getMessage());
        errorDetails.setDetails(webRequest.getDescription(false));
        errorDetails.setErrorCode(INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
