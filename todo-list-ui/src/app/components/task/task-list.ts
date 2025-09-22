import { Component, inject, OnInit } from '@angular/core';
import { TaskService } from '../../services/task.service';
import { Router } from '@angular/router';
import { TaskResponse } from '../../models/task.response';
import {
  FormBuilder,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { TaskRequest } from '../../models/task.request';
import { Task } from '../../models/task';

@Component({
  selector: 'app-task',
  imports: [FormsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './task-list.html',
  styleUrl: './task-list.css',
})
export class TaskList implements OnInit {
  tasks: TaskResponse[] = [];
  loading = true;
  error = '';
  fb = inject(FormBuilder);

  taskService = inject(TaskService);
  router = inject(Router);

  form = this.fb.group({
    title: ['', Validators.required],
    description: [''],
  });

  getAll() {
    this.taskService.getAll().subscribe({
      next: (data) => {
        this.tasks = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'error finding tasks';
        this.loading = false;
      },
    });
  }

  ngOnInit(): void {
    this.getAll();
  }

  onSubmit(): void {
    if (this.form.invalid) return;

    const task = this.form.value as TaskRequest;

    this.taskService.create(task).subscribe({
      next: (newTask) => {
        this.tasks.push(newTask);
        this.form.reset();
      },
      error: (err) => {
        this.error = err;
      },
    });
  }

  toggleTask(task: Task): void {
    this.taskService.toggleCompleted(task.id).subscribe({
      next: (updated) => {
        task.completed = updated.completed;
      },
      error: (err) => {
        this.error = err;
      },
    });
  }

  deleteTask(id: number): void {
    if (!confirm('are you sure?')) return;

    this.taskService.delete(id).subscribe({
      next: () => {
        this.tasks = this.tasks.filter((t) => t.id != id);
      },
      error: (err) => {
        this.error = err;
      },
    });
  }
}
