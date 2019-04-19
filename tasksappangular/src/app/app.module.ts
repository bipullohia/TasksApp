import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { TaskListComponent } from './component/task-list/task-list.component';
import { TaskFormComponent } from './component/task-form/task-form.component';
import { AdjustLengthPipe } from './pipe/adjust-length.pipe';
import { DeleteTaskComponent } from './component/delete-task/delete-task.component';


const routes: Routes = [
  {path: '', component: DashboardComponent},
  {path: 'listTasks/all', component: TaskListComponent},
  {path: 'listTasks/:category', component: TaskListComponent},
  {path:'addTask',component:TaskFormComponent}, 
  {path:'editTask/:id',component:TaskFormComponent},
  {path:'deleteTask/:id',component:DeleteTaskComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    TaskListComponent,
    TaskFormComponent,
    AdjustLengthPipe,
    DeleteTaskComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
