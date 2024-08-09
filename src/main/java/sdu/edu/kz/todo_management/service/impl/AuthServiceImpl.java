package sdu.edu.kz.todo_management.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sdu.edu.kz.todo_management.dto.LoginDTO;
import sdu.edu.kz.todo_management.dto.RegisterDTO;
import sdu.edu.kz.todo_management.entity.Role;
import sdu.edu.kz.todo_management.entity.User;
import sdu.edu.kz.todo_management.exception.ToDoAPIException;
import sdu.edu.kz.todo_management.repository.RoleRepository;
import sdu.edu.kz.todo_management.repository.UserRepository;
import sdu.edu.kz.todo_management.security.JwtTokenProvider;
import sdu.edu.kz.todo_management.service.AuthService;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    @Override
    public String register(RegisterDTO registerDTO) {
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            throw new ToDoAPIException("Username already exists!");
        }

        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            throw new ToDoAPIException("Email already exists!");
        }

        var user = new User();
        user.setFirstName(registerDTO.getFirstname());
        user.setLastName(registerDTO.getLastname());
        user.setEmail(registerDTO.getEmail());
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Set<Role> roles = new HashSet<>();
        var roleUser = roleRepository.findByName("ROLE_USER");
        roles.add(roleUser);

        user.setRoles(roles);

        userRepository.save(user);
        return "User registered successfully";
    }

    @Override
    public String login(LoginDTO loginDTO) {
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsernameOrEmail(),
                loginDTO.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenProvider.generateToken(authentication);
    }
}
