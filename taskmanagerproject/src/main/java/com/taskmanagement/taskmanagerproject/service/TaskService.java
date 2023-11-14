package com.taskmanagement.taskmanagerproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.taskmanagement.taskmanagerproject.dao.Dao;
import com.taskmanagement.taskmanagerproject.entity.Task;
import com.taskmanagement.taskmanagerproject.repository.TaskRepository;

@Service
public class TaskService implements Dao<Task> {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task add(Task o) {
        return taskRepository.save(o);
    }

    @Override
    public Optional<Task> update(int id, Task o) {
        return taskRepository.findById(id).map((task) -> {
            task.setLabel(o.getLabel());
            task.setStatus(o.getStatus());
            task.setDate_debut(o.getDate_debut());
            task.setDate_fin(o.getDate_fin());
            task.setStatus(o.getStatus());
            return taskRepository.save(task);

        });
    }

    @Override
    public Task delete(int id) {
        Task temp = taskRepository.findById(id).get();
        taskRepository.deleteById(id);
        return temp;
    }

    @Override
    public Task findById(int id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

}