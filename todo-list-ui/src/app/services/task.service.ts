import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TaskResponse } from '../models/task.response';
import { TaskRequest } from '../models/task.request';

@Injectable({ providedIn: 'root' })
export class TaskService {
  private apiURL = 'http://localhost:8080/task';

  constructor(private http: HttpClient) {}

  getAll(): Observable<TaskResponse[]> {
    return this.http.get<TaskResponse[]>(this.apiURL);
  }

  create(task: TaskRequest): Observable<TaskResponse> {
    return this.http.post<TaskResponse>(this.apiURL, task);
  }

  toggleCompleted(id: number): Observable<TaskResponse> {
    return this.http.patch<TaskResponse>(`${this.apiURL}/${id}/completed`, {});
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiURL}/${id}`);
  }
}
