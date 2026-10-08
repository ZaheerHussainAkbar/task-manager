package com.simple.task.manager.repo;

import com.simple.task.manager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Long> {
}
