package com.taskmanagement.taskmanagerproject.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.taskmanagement.taskmanagerproject.dto.TaskDto;
import com.taskmanagement.taskmanagerproject.entity.Task;

@Component
public class TaskMapper implements Mapper<Task, TaskDto> {
    private ModelMapper modelMapper;

    public TaskMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Task mapToEntity(TaskDto taskDto) {
        Task user = modelMapper.map(taskDto, Task.class);
        return user;
    }

    @Override
    public TaskDto mapFromEntity(Task task) {
        TaskDto taskDto = modelMapper.map(task, TaskDto.class);
        return taskDto;
    }

}