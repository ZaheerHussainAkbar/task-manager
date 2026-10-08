package com.simple.task.manager.dto;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TaskDto {

    private Long id;

    @Nonnull
    private String title;

    private String description;

    private Date createdOn;


}
