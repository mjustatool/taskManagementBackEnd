package com.taskmanagement.taskmanagerproject.mapper;

public interface Mapper<A, B> {
    A mapToEntity(B b);

    B mapFromEntity(A a);
}