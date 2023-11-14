package com.taskmanagement.taskmanagerproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.taskmanagement.taskmanagerproject.dao.Dao;
import com.taskmanagement.taskmanagerproject.entity.User;
import com.taskmanagement.taskmanagerproject.repository.UserRepository;

@Service
public class UserService implements Dao<User> {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User add(User o) {
        return userRepository.save(o);
    }

    @Override
    public Optional<User> update(int id, User o) {
        return userRepository.findById(id).map((user) -> {
            user.setNom(o.getNom());
            user.setPrenom(o.getPrenom());
            user.setEmail(o.getEmail());
            user.setUsername(o.getUsername());
            user.setPassword(o.getPassword());
            return userRepository.save(user);
        });
    }

    @Override
    public User delete(int id) {
        User temp = userRepository.findById(id).get();
        userRepository.deleteById(id);
        return temp;
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

}