package com.taskmanagement.taskmanagerproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.taskmanagement.taskmanagerproject.dto.UserDto;
import com.taskmanagement.taskmanagerproject.entity.Role;
import com.taskmanagement.taskmanagerproject.entity.User;
import com.taskmanagement.taskmanagerproject.exception.CustomNotFoundException;
import com.taskmanagement.taskmanagerproject.jwt.JwtUtils;
import com.taskmanagement.taskmanagerproject.mapper.UserMapper;
import com.taskmanagement.taskmanagerproject.repository.RoleRepository;
import com.taskmanagement.taskmanagerproject.repository.UserRepository;
import com.taskmanagement.taskmanagerproject.response_request.LoginRequest;
import com.taskmanagement.taskmanagerproject.response_request.Response;
import com.taskmanagement.taskmanagerproject.service.UserDetailService;
import com.taskmanagement.taskmanagerproject.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailService detailService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable int userId) throws CustomNotFoundException {
        return ResponseEntity.ok(userService.findById(userId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> userList = userService.findAll();
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (CustomNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody @Valid UserDto userDto) {
        User user = userMapper.mapToEntity(userDto);
        return new ResponseEntity<>(userService.add(user), HttpStatus.CREATED);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable int userId, @RequestBody @Valid User updatedUser) {
        userService.update(userId, updatedUser);
        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId) {
        try {
            userService.delete(userId);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } catch (CustomNotFoundException ex) {
            return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
        User user = detailService.loadUserByUsername(loginRequest.getUsername());
        if (encoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.ok()
                    .body(new Response(jwtUtils.generateToken(user.getUsername(), user.getRole().getRoleName()),
                            jwtUtils.generateRefreshToken(user.getUsername())));
        }
        return ResponseEntity.badRequest().body("Username or Password invalide check them please");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserDto dto) {
        Optional<User> user = userRepository.findByUsernameOrEmail(dto.getUsername(), dto.getEmail());
        if (user.isPresent()) {
            throw new EntityNotFoundException("founded");
        }
        Role role = roleRepository.findByRoleName("user")
                .orElseThrow(() -> new EntityNotFoundException());
        User newUser = User.builder()
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .password(encoder.encode(dto.getPassword()))
                .username(dto.getUsername())
                .email(dto.getEmail())
                .role(role).build();
        userService.add(newUser);

        return ResponseEntity.ok(newUser);
    }
}