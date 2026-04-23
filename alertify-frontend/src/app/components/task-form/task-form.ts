import { Component, EventEmitter, Input, OnChanges, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Task, TaskService } from '../../services/task';

@Component({
  selector: 'app-task-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './task-form.html',
  styleUrl: './task-form.css'
})
export class TaskFormComponent implements OnChanges {
  @Input() taskToEdit: Task | null = null;
  @Input() userEmail: string | null = null;
  @Output() taskSaved = new EventEmitter<void>();

  task: Task = {
    title: '',
    annotation: '',
    priority: 'MEDIUM',
    alertTime: '',
    done: false
  };

  constructor(private taskService: TaskService) {}

  ngOnChanges() {
    if (this.taskToEdit) {
      this.task = { ...this.taskToEdit };
    } else {
      this.resetForm();
    }
  }

  save() {
    const taskToSend = {
      ...this.task,
      userEmail: this.userEmail,
      alertTime: this.task.alertTime ? new Date(this.task.alertTime).toISOString().slice(0, 19) : null
    };
    if (this.task.id) {
      this.taskService.updateTask(this.task.id, taskToSend).subscribe(() => {
        this.taskSaved.emit();
        this.resetForm();
      });
    } else {
      this.taskService.createTask(taskToSend).subscribe(() => {
        this.taskSaved.emit();
        this.resetForm();
      });
    }
  }

  resetForm() {
    this.task = { title: '', annotation: '', priority: 'MEDIUM', alertTime: '', done: false };
  }
}
