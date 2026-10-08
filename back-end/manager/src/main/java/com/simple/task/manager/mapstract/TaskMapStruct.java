package com.simple.task.manager.mapstract;

import com.simple.task.manager.dto.TaskDto;
import com.simple.task.manager.entity.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapStruct {

    TaskDto fromTask(Task task);


    Task fromTaskDto(TaskDto taskDto);

    List<TaskDto> fromTaskList(List<Task> taskList);
}
