package com.taskmanagement.taskmanagerproject.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.taskmanagement.taskmanagerproject.dto.UserDto;
import com.taskmanagement.taskmanagerproject.entity.User;

@Component
public class UserMapper implements Mapper<User, UserDto> {
    private ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public User mapToEntity(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return user;
    }

    @Override
    public UserDto mapFromEntity(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }
}