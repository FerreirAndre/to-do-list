import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Task } from '../models/task';

@Injectable({ providedIn: 'root' })
export class TaskService {
  private apiURL = 'http://localhost:8080/task';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Task[]> {
    return this.http.get<Task[]>(this.apiURL);
  }

  create(task: Task): Observable<Task> {
    return this.http.post<Task>(this.apiURL, task);
  }

  update(id: number, task: Task): Observable<void> {
    return this.http.put<void>(`${this.apiURL}/${id}`, task);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiURL}/${id}`);
  }
}
