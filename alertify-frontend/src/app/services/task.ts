import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Task {
  id?: number;
  title: string;
  annotation: string;
  priority: 'HIGH' | 'MEDIUM' | 'LOW';
  alertTime: string | null;
  done: boolean;
  userEmail?: string | null;
}

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private apiUrl = 'https://alertify-production.up.railway.app/api/tasks';
  constructor(private http: HttpClient) {}

  getAllTasks(email?: string): Observable<Task[]> {
    const url = email
      ? `${this.apiUrl}/getAllTasks?email=${email}`
      : `${this.apiUrl}/getAllTasks`;
    return this.http.get<Task[]>(url);
  }

  createTask(task: Task): Observable<Task> {
    return this.http.post<Task>(`${this.apiUrl}/createTask`, task);
  }

  updateTask(id: number, task: Task): Observable<Task> {
    return this.http.put<Task>(`${this.apiUrl}/updateTask/${id}`, task);
  }

  deleteTask(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/deleteTask/${id}`);
  }
}
