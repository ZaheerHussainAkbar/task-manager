package com.simple.task.manager.service;

import com.simple.task.manager.dto.TaskDto;

import java.util.List;

public interface TaskServiceInf {

    TaskDto saveTask(TaskDto taskDto);

    List<TaskDto> getTaskList();
}
