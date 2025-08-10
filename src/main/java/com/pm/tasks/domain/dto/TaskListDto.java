package com.pm.tasks.domain.dto;

import java.util.List;
import java.util.UUID;

public record TaskListDto(
        UUID id,
        String title,
        String description,
        Integer count,
        Double progress , // 0-1 how many task completed
        List<TaskDto> tasks
) {
}
