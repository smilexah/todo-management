package sdu.edu.kz.todo_management.mapper;

import org.mapstruct.Mapper;
import sdu.edu.kz.todo_management.dto.ToDoDTO;
import sdu.edu.kz.todo_management.entity.ToDo;

@Mapper(componentModel = "spring")
public interface ToDoMapper extends BaseMapper<ToDo, ToDoDTO>{
    @Override
    default ToDo toEntity(ToDoDTO toDoDTO) {
        ToDo todo = new ToDo();
        todo.setId(toDoDTO.getId());
        todo.setTitle(toDoDTO.getTitle());
        todo.setDescription(toDoDTO.getDescription());
        todo.setIsCompleted(toDoDTO.getIsCompleted());

        return todo;
    }

    @Override
    default ToDoDTO toDto(ToDo toDo) {
        ToDoDTO toDoDTO = new ToDoDTO();
        toDoDTO.setId(toDo.getId());
        toDoDTO.setTitle(toDo.getTitle());
        toDoDTO.setDescription(toDo.getDescription());
        toDoDTO.setIsCompleted(toDo.getIsCompleted());

        return toDoDTO;
    }

    default ToDo mapToUpdateEntity(ToDoDTO toDoDTO, ToDo existingToDo) {
        if (toDoDTO == null || existingToDo == null) {
            return null;
        }

        existingToDo.setTitle(toDoDTO.getTitle());
        existingToDo.setDescription(toDoDTO.getDescription());
        existingToDo.setIsCompleted(toDoDTO.getIsCompleted());

        return existingToDo;
    }
}
