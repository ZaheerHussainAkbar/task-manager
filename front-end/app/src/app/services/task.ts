import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';

export interface Task {
  id: number;
  title: string;
  description: string;
  createdOn: string;
}

export interface CreateTaskRequest {
  title: string;
  description: string;
}

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private readonly apiUrl =
    'http://localhost:8080/manager/api/tasks';

  constructor(private http: HttpClient) {}

  getTasks(): Observable<Task[]> {
    return this.http.get<Task[]>(this.apiUrl).pipe(
      catchError(error => this.handleError(error))
    );
  }

  createTask(task: CreateTaskRequest): Observable<Task> {
    return this.http.post<Task>(this.apiUrl, task).pipe(
      catchError(error => this.handleError(error))
    );
  }

  private handleError(error: HttpErrorResponse):
      Observable<never> {
 
    let message = 'Something went wrong. Please try again.';

    if (error.status === 0) {
      message = 'Cannot connect to the backend server.';
    } else if (error.status === 400) {
      message = 'Invalid task details.';
    } else if (error.status === 404) {
      message = 'Task API endpoint not found.';
    } else if (error.status >= 500) {
      message = 'Server error. Please try again later.';
    }

    return throwError(() => new Error(message));
  }
}

