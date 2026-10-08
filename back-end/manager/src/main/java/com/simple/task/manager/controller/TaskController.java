package com.simple.task.manager.controller;

import com.simple.task.manager.dto.TaskDto;
import com.simple.task.manager.service.TaskServiceInf;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/manager/api")
@Slf4j
public class TaskController {

    Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskServiceInf taskServiceInf;


    @PostMapping("/tasks")
    public ResponseEntity<TaskDto> saveTask(@Valid @RequestBody TaskDto taskDto) {

        logger.debug("Save task details");
        TaskDto taskResponse = taskServiceInf.saveTask(taskDto);
        HttpStatusCode status = null;
        if (Objects.nonNull(taskResponse)) {
            status = HttpStatus.CREATED;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(taskResponse, status);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getTaskList() {

        logger.debug("Fetch Task list");
        List<TaskDto> taskDtoListResponse = taskServiceInf.getTaskList();
        HttpStatusCode status = null;
        if (Objects.nonNull(taskDtoListResponse)) {
            status = HttpStatus.FOUND;
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(taskDtoListResponse, status);
    }

}
