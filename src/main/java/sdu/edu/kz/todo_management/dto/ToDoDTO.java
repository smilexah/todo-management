package sdu.edu.kz.todo_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoDTO {
    private Long id;
    private String title;
    private String description;
    private Boolean isCompleted;
}
