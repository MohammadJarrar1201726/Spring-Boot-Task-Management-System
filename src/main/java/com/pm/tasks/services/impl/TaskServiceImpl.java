package com.pm.tasks.services.impl;

import com.pm.tasks.domain.entities.Task;
import com.pm.tasks.domain.entities.TaskList;
import com.pm.tasks.domain.entities.TaskPriority;
import com.pm.tasks.domain.entities.TaskStatus;
import com.pm.tasks.repositories.TaskListRepository;
import com.pm.tasks.repositories.TaskRepository;
import com.pm.tasks.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;
    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository , TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }


    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Transactional
    @Override
    public Task createTask(UUID taskListId ,Task task) {
        if(task.getId() != null){
            throw new IllegalArgumentException("Task id is already set");
        }
        if(task.getTitle() == null || task.getTitle().isBlank()){
            throw new IllegalArgumentException("Task must have a title!");
        }
        //priority
        TaskPriority taskPriority = Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);
        //status
        TaskStatus taskStatus = TaskStatus.OPEN;

        TaskList taskList = taskListRepository.findById(taskListId).orElseThrow(() -> new IllegalArgumentException("Invalid Task List ID provided!"));
        LocalDateTime now = LocalDateTime.now();
        Task taskToSave = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPriority,
                taskList,
                now,
                now


        );
        return taskRepository.save(taskToSave);
    }

    @Override
    public Optional<Task> listTask(UUID taskListId, UUID taskId){
        return taskRepository.findByTaskListIdAndId(taskListId, taskId);
    }
    @Transactional
    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if(task.getId() == null){
            throw new IllegalArgumentException("Task must have an ID!");
        }
        if(!Objects.equals(taskId, task.getId())){
            throw new IllegalArgumentException("Task ID's do not match!");
        }
        if(task.getPriority() == null){
            throw new IllegalArgumentException("Task must have a priority!");
        }
        if(task.getStatus() == null){
            throw new IllegalArgumentException("Task must have a status!");
        }

        Task taskToUpdate = taskRepository.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Task ID provided!"));
        taskToUpdate.setTitle(task.getTitle());
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setDueDate(task.getDueDate());
        taskToUpdate.setStatus(task.getStatus());
        taskToUpdate.setPriority(task.getPriority());
        taskToUpdate.setUpdated(LocalDateTime.now());

        return taskRepository.save(taskToUpdate);
    }
    @Transactional
    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {

        taskRepository.deleteByTaskListIdAndId(taskListId , taskId);
    }
}
