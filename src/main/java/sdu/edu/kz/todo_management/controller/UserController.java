package sdu.edu.kz.todo_management.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import sdu.edu.kz.todo_management.dto.UserDTO;
import sdu.edu.kz.todo_management.entity.User;
import sdu.edu.kz.todo_management.repository.UserRepository;
import sdu.edu.kz.todo_management.service.UserService;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/login")
    public ResponseEntity<Void> getUser(@RequestBody UserDTO userDTO) {
        User user = userService.getUserByUsernameAndPassword(
                userDTO.getUsername(),
                new BCryptPasswordEncoder().encode(userDTO.getPassword())
        );
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
