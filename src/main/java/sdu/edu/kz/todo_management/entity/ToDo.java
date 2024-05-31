package sdu.edu.kz.todo_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "todos")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "todos_title", nullable = false)
    private String title;

    @Column(name = "todos_description", nullable = false)
    private String description;

    @Column(name = "todos_completed", nullable = false)
    private Boolean isCompleted;
}
