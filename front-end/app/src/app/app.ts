import { Component, signal } from '@angular/core';
import { TasksComponent } from './tasks/tasks';

@Component({
  imports: [TasksComponent],
  selector: 'app-root',
  standalone: true,
  styleUrl: './app.css',
  templateUrl: './app.html',
})
export class App {
}
