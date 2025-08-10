package com.pm.tasks.mappers;

import com.pm.tasks.domain.dto.TaskListDto;
import com.pm.tasks.domain.entities.TaskList;

public interface TaskListMapper {
    TaskList fromDto(TaskListDto taskListDto);
    TaskListDto toDto(TaskList taskList);
}
