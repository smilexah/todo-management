package sdu.edu.kz.todo_management.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sdu.edu.kz.todo_management.entity.User;
import sdu.edu.kz.todo_management.repository.UserRepository;
import sdu.edu.kz.todo_management.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username, password);
    }
}
