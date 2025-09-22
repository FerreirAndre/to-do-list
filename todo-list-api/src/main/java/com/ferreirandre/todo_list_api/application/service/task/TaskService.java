package com.ferreirandre.todo_list_api.application.service.task;

import com.ferreirandre.todo_list_api.api.dto.task.TaskRequest;
import com.ferreirandre.todo_list_api.api.dto.task.TaskResponse;
import com.ferreirandre.todo_list_api.domain.model.task.Task;
import com.ferreirandre.todo_list_api.infrastructure.repository.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    @Autowired
    private final TaskRepository repository;

    public TaskResponse create(TaskRequest request) {
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .completed(false)
                .build();

        return toResponse(repository.save(task));
    }

    public List<TaskResponse> findAll() {
        return repository.findAll().stream()
                .map(this::toResponse).toList();
    }

    public TaskResponse toggleCompleted(Long id) {
        var task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("task not found"));

        task.setCompleted(!task.isCompleted());

        repository.saveAndFlush(task);

        return toResponse(task);
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(()-> new RuntimeException("task not found"));

        repository.deleteById(id);
    }

    private TaskResponse toResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .completed(task.isCompleted())
                .build();
    }
}
