package sdu.edu.kz.todo_management.service;

import sdu.edu.kz.todo_management.entity.User;

public interface UserService {
    User getUserByUsernameAndPassword(String username, String password);
}
