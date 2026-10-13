import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { finalize } from 'rxjs';
import {
  Task,
  TaskService
} from '../services/task';

@Component({
  selector: 'app-tasks',
  standalone: true,
  imports: [CommonModule, FormsModule],
  styleUrl: './tasks.css',
  templateUrl: './tasks.html'
})
export class TasksComponent implements OnInit {

  tasks: Task[] = [];

  title = '';
  description = '';

  loading = false;
  saving = false;
  errorMessage = '';
  successMessage = '';

  constructor(private taskService: TaskService) {}

  ngOnInit(): void {
    this.loadTasks();
  }

  loadTasks(): void {
    this.loading = true;
    this.errorMessage = '';

    this.taskService.getTasks()
    .pipe(finalize(() => this.loading = false))
    .subscribe({
      next: (data: Task[]) => {
      console.log('GET response:', data);
      this.tasks = data;
      this.loading = false;
    },
    error: (error) => {
      this.errorMessage = error.message || 'Unable to load tasks.';
      this.loading = false;
    }
    });
  }

  addTask(): void {
    if (!this.title.trim()) {
      this.errorMessage =
        'Please enter title.';
      return;
    }

    this.saving = true;
    this.errorMessage = '';
    this.successMessage = '';

    const request = {
      title: this.title.trim(),
      description: this.description.trim()
    };

    this.taskService.createTask(request).subscribe({
      next: (response) => {
      console.log('POST response:', response);

      this.saving = false;
      this.successMessage = 'Task created successfully!';
      this.title = '';
      this.description = '';

      this.loadTasks();
    },
    error: (error) => {
      console.error('POST failed:', error);

      this.saving = false;
      this.errorMessage =
        error.error?.message ||
        error.message ||
        'Unable to create task.';
    }
    });
  }
}

