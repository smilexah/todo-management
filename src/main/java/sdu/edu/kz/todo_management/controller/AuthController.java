package sdu.edu.kz.todo_management.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import sdu.edu.kz.todo_management.dto.JwtAuthResponse;
import sdu.edu.kz.todo_management.dto.LoginDTO;
import sdu.edu.kz.todo_management.dto.RegisterDTO;
import sdu.edu.kz.todo_management.service.AuthService;

@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        return new ResponseEntity<>(authService.register(registerDTO), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDTO loginDTO) {
        JwtAuthResponse response = new JwtAuthResponse();

        response.setAccessToken(authService.login(loginDTO));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
