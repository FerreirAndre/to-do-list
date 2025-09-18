import { Routes } from '@angular/router';
import { Login } from './pages/login/login';
import { Register } from './pages/register/register';
import { TaskList } from './components/task/task-list';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'task',
    pathMatch: 'full',
  },
  {
    path: 'task',
    component: TaskList,
  },
  {
    path: 'register',
    component: Register,
  },
  {
    path: 'login',
    component: Login,
  },
];
