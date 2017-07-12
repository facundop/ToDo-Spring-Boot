package com.facundoprecentado.repository;

import org.springframework.data.repository.CrudRepository;

import com.facundoprecentado.entity.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {

}