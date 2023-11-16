package com.taskmanagement.taskmanagerproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.taskmanagement.taskmanagerproject.dao.Dao;
import com.taskmanagement.taskmanagerproject.entity.Task;
import com.taskmanagement.taskmanagerproject.exception.CustomNotFoundException;
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
    public Task update(int id, Task o) {
        return taskRepository.findById(id).map((task) -> {
            task.setLabel(o.getLabel());
            task.setStatus(o.getStatus());
            task.setDate_debut(o.getDate_debut());
            task.setDate_fin(o.getDate_fin());
            task.setStatus(o.getStatus());
            return taskRepository.save(task);
        }).orElseThrow(() -> new CustomNotFoundException("Task Not found with this ID : " + id));
    }

    @Override
    public Task delete(int id) {
        Task temp = taskRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Task not found with ID: " + id));
        ;
        taskRepository.deleteById(id);
        return temp;
    }

    @Override
    public Task findById(int id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Task not found with ID: " + id));
    }

    @Override
    public List<Task> findAll() {
        if (!taskRepository.findAll().isEmpty()) {
            return taskRepository.findAll();
        }
        throw new CustomNotFoundException("Tasks list is empty for now");
    }

}