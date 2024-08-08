package sdu.edu.kz.todo_management.service;

import sdu.edu.kz.todo_management.dto.LoginDTO;
import sdu.edu.kz.todo_management.dto.RegisterDTO;

public interface AuthService {
    String register(RegisterDTO registerDTO);
    String login(LoginDTO loginDTO);
}
