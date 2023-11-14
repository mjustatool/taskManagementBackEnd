package com.taskmanagement.taskmanagerproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskmanagement.taskmanagerproject.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}