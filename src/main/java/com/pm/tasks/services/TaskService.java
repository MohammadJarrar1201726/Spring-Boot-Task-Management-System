package com.pm.tasks.services;

import com.pm.tasks.domain.entities.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {

    List<Task> listTasks(UUID taskListId);
    Task createTask(UUID taskListId , Task task);

    Optional<Task> listTask(UUID taskListId , UUID taskId);

    Task updateTask(UUID taskListId , UUID taskId , Task task);

    void deleteTask(UUID taskListId , UUID taskId);
}
