package com.pm.tasks.domain.dto;

import com.pm.tasks.domain.entities.TaskPriority;
import com.pm.tasks.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;


//record for constructors , getters , and mutability
public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status
) {
}
