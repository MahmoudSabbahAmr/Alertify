import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Task, TaskService } from '../../services/task';
import { TaskFormComponent } from '../task-form/task-form';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [CommonModule, TaskFormComponent],
  templateUrl: './task-list.html',
  styleUrl: './task-list.css'
})
export class TaskListComponent implements OnInit {
  tasks: Task[] = [];
  selectedTask: Task | null = null;
  userName = '';

  constructor(
    private taskService: TaskService,
    public authService: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    if (!this.authService.isLoggedIn()) {
      this.router.navigate(['/login']);
      return;
    }
    this.userName = this.authService.getName() || '';
    this.loadTasks();
  }

  loadTasks() {
    const email = this.authService.getEmail();
    if (email) {
      this.taskService.getAllTasks(email).subscribe(tasks => {
        this.tasks = tasks;
      });
    }
  }

  editTask(task: Task) {
    this.selectedTask = { ...task };
  }

  deleteTask(id: number) {
    this.taskService.deleteTask(id).subscribe(() => {
      this.loadTasks();
    });
  }

  onTaskSaved() {
    this.selectedTask = null;
    this.loadTasks();
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
