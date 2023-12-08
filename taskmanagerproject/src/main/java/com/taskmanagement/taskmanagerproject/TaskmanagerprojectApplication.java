package com.taskmanagement.taskmanagerproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.taskmanagement.taskmanagerproject.entity.Role;
import com.taskmanagement.taskmanagerproject.repository.RoleRepository;

@SpringBootApplication
public class TaskmanagerprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagerprojectApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(RoleRepository repository) {
		return args -> {
			Role role = Role.builder().roleName("admin").build();
			if (repository.findAll().isEmpty()) {
				repository.save(role);
			}

		};

	}

}
