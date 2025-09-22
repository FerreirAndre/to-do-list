package com.ferreirandre.todo_list_api.api.controller.task;

import com.ferreirandre.todo_list_api.api.dto.task.TaskRequest;
import com.ferreirandre.todo_list_api.api.dto.task.TaskResponse;
import com.ferreirandre.todo_list_api.application.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    @Autowired
    private final TaskService service;

    @GetMapping
    public ResponseEntity<List<TaskResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody TaskRequest task) {
        return ResponseEntity.ok(service.create(task));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/completed")
    public ResponseEntity<TaskResponse> toggleCompleted(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.toggleCompleted(id));
    }
}
