package com.ferreirandre.todo_list_api.infrastructure.repository.task;

import com.ferreirandre.todo_list_api.domain.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}