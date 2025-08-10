package com.pm.tasks.controllers;

import com.pm.tasks.domain.dto.TaskDto;
import com.pm.tasks.domain.entities.Task;
import com.pm.tasks.mappers.TaskMapper;
import com.pm.tasks.services.TaskService;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/task-lists/{task_list_id}/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }
    @GetMapping
    public List<TaskDto> listTasks(@PathVariable("task_list_id") UUID taskListId) {
        return taskService.listTasks(taskListId).stream().map(taskMapper::toDto).toList();
    }

    @PostMapping
    public TaskDto createTask(@PathVariable("task_list_id") UUID taskListId, @RequestBody TaskDto task) {
        Task savedTask = taskService.createTask(taskListId , taskMapper.fromDto(task));
        return taskMapper.toDto(savedTask);
    }

    @GetMapping(path="/{task_id}")
    public Optional<TaskDto> getTask(@PathVariable("task_list_id") UUID taskListId
            , @PathVariable("task_id") UUID taskId){
        return taskService.listTask(taskListId, taskId).map(taskMapper::toDto);
    }

    @PutMapping(path = "/{task_id}")
    public TaskDto updateTask(@PathVariable("task_list_id") UUID taskListId,
                              @PathVariable("task_id") UUID taskId ,
                              @RequestBody TaskDto taskDto){

        Task updatedTask = taskService.updateTask(taskListId ,taskId, taskMapper.fromDto(taskDto));
        return taskMapper.toDto(updatedTask);
    }

    @DeleteMapping(path = "/{task_id}")
    public void deleteTask(@PathVariable("task_list_id") UUID taskListId,
                           @PathVariable("task_id") UUID taskId ){
        taskService.deleteTask(taskListId, taskId);

    }

}
