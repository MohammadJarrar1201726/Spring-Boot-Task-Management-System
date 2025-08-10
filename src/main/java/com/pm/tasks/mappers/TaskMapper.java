package com.pm.tasks.mappers;

import com.pm.tasks.domain.dto.TaskDto;
import com.pm.tasks.domain.entities.Task;

public interface TaskMapper {
    Task fromDto(TaskDto taskDto);
    TaskDto toDto(Task task);
}
