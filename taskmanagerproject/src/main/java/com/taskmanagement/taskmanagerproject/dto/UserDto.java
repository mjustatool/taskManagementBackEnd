package com.taskmanagement.taskmanagerproject.dto;

public record UserDto(
        int id,
        String nom,
        String prenom,
        String email,
        String password,
        String username) {

}