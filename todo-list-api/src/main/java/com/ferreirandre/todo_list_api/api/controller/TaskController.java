package com.ferreirandre.todo_list_api.api.controller;

import com.ferreirandre.todo_list_api.api.dto.TaskResponse;
import com.ferreirandre.todo_list_api.application.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    @Autowired
    private final TaskService service;

    @GetMapping
    public ResponseEntity<List<TaskResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
}
