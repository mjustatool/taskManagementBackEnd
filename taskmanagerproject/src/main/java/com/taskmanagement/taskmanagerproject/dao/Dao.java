package com.taskmanagement.taskmanagerproject.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    public T add(T o);

    public Optional<T> update(int id, T o);

    public T delete(int id);

    public T findById(int id);

    public List<T> findAll();
}