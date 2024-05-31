package sdu.edu.kz.todo_management.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorDTO {
    private LocalDateTime timeStamp;
    private String message;
    private String details;
    private String errorCode;
}
