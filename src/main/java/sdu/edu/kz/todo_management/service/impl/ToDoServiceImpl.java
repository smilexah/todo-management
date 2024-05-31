package sdu.edu.kz.todo_management.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sdu.edu.kz.todo_management.dto.ToDoDTO;
import sdu.edu.kz.todo_management.entity.ToDo;
import sdu.edu.kz.todo_management.exception.ResourceNotFoundException;
import sdu.edu.kz.todo_management.mapper.ToDoMapper;
import sdu.edu.kz.todo_management.repository.ToDoRepository;
import sdu.edu.kz.todo_management.service.ToDoService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ToDoServiceImpl implements ToDoService {
    private final ToDoRepository toDoRepository;
    private final ToDoMapper toDoMapper;

    @Override
    public void addToToDo(ToDoDTO toDoDto) {
        ToDo toDo = toDoMapper.toEntity(toDoDto);

//        ToDo todo = modelmapper.map(toDoDto, ToDo.class);
//        ToDo savedToDo = toDoRepository.save(toDo);
//        ToDoDTO savedToDoDTO = modelmapper.map(savedToDo, ToDoDTO.class);

//        if (toDo.getIsCompleted() != null) {
//            toDo.setIsCompleted(false);
//        }

        toDoRepository.save(toDo);
    }

    @Override
    public List<ToDoDTO> getAllToDos() {
        List<ToDo> toDoList = toDoRepository.findAll();
        return toDoList
                .stream()
                .map(toDoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteToDo(Long id) {
        ToDo toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find todo for id " + id));

        toDoRepository.delete(toDo);
    }

    @Override
    public void updateToDo(Long id, ToDoDTO toDoDTO) {
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);
        if (!optionalToDo.isPresent()) {
            throw new ResourceNotFoundException("Could not find todo for id " + id);
        }

        ToDo existingToDo = optionalToDo.get();
        toDoMapper.mapToUpdateEntity(toDoDTO, existingToDo);

        toDoRepository.save(existingToDo);
    }

    @Override
    public ToDoDTO getToDoById(Long id) {
        ToDo toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find todo for id " + id));

        return toDoMapper.toDto(toDo);
    }

    @Override
    public void completeTodo(Long id) {
        ToDo todo = toDoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));
        todo.setIsCompleted(Boolean.TRUE);

        toDoRepository.save(todo);
    }

    @Override
    public void inCompleteTodo(Long id) {
        ToDo todo = toDoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));
        todo.setIsCompleted(Boolean.FALSE);

        toDoRepository.save(todo);
    }
}
