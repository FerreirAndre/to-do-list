import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import {
  FormBuilder,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { UserInterface } from '../../models/user.interface';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  fb = inject(FormBuilder);
  http = inject(HttpClient);
  authService = inject(AuthService);
  router = inject(Router);

  form = this.fb.nonNullable.group({
    login: ['', Validators.required],
    password: ['', Validators.required],
  });

  onSubmit(): void {
    this.http
      .post<UserInterface>(
        'http://localhost:8080/auth/login',
        this.form.getRawValue()
      )
      .subscribe((response) => {
        console.log('response: ', response);
        localStorage.setItem('token', response.token);
        this.authService.currentUserSig.set(response);
        this.router.navigateByUrl('/');
      });
  }
}
