package com.example.ApiCrud.controller;

import com.example.ApiCrud.dto.TasksDTORequest;
import com.example.ApiCrud.dto.TasksDTOResponse;
import com.example.ApiCrud.entity.TaskEntity;
import com.example.ApiCrud.service.TasksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@CrossOrigin(allowedHeaders = {"Authorization", "Origin"}, allowCredentials = "true", origins = "*")
@RestController
@RequestMapping(value = "/tasks")
public class TasksController {

    private final TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TasksDTOResponse> createTask(@RequestBody TaskEntity taskEntity) {
        TasksDTOResponse dtoResponse = tasksService.createTask(taskEntity);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(taskEntity.getId()).toUri();

        return ResponseEntity.created(uri).body(dtoResponse);
    }

    @GetMapping()
    public ResponseEntity<Collection<TasksDTOResponse>> readAllTasks() {
        Collection<TasksDTOResponse> dtoResponses = tasksService.readAllTask();

        return ResponseEntity.ok(dtoResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TasksDTOResponse> readTaskById(@PathVariable Integer id) {
        TasksDTOResponse dtoResponse = tasksService.readTaskById(id);

        return ResponseEntity.ok(dtoResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TasksDTOResponse> updateTask(@PathVariable Integer id, @RequestBody TasksDTORequest dtoRequest) {
        TasksDTOResponse dtoResponse = tasksService.updateTask(id, dtoRequest);

        return ResponseEntity.ok(dtoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {
        tasksService.deleteTask(id);

        return ResponseEntity.noContent().build();
    }

}
