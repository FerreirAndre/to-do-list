package com.ferreirandre.todo_list_api.infrastructure.repository;

import com.ferreirandre.todo_list_api.domain.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}