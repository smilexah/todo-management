package sdu.edu.kz.todo_management.mapper;

public interface BaseMapper<E, D> {
    E toEntity(D dto);

    D toDto(E entity);
}