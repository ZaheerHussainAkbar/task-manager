package com.simple.task.manager.service;

import com.simple.task.manager.dto.TaskDto;
import com.simple.task.manager.entity.Task;
import com.simple.task.manager.mapstract.TaskMapStruct;
import com.simple.task.manager.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TaskServiceImpl implements TaskServiceInf{

    @Autowired
    private TaskMapStruct mapper;

    @Autowired
    private TaskRepo repo;

    @Override
    public TaskDto saveTask(TaskDto taskDto) {

        if (Objects.nonNull(taskDto)) {
            Task task = mapper.fromTaskDto(taskDto);
            task = repo.save(task);
            taskDto = mapper.fromTask(task);
        }

        return taskDto;
    }

    @Override
    public List<TaskDto> getTaskList() {
        List<Task> taskList = repo.findAll();
        return mapper.fromTaskList(taskList);
    }
}
