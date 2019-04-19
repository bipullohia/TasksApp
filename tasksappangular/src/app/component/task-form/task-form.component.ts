import { Component, OnInit } from '@angular/core';
import { Task } from '../../model/task';
import { TaskService } from '../../service/task.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-task-form',
  templateUrl: './task-form.component.html',
  styleUrls: ['./task-form.component.css']
})
export class TaskFormComponent implements OnInit {

  task : Task;
  isEditing : boolean;
  addLogo : string;

  constructor(
    private activatedRoute : ActivatedRoute,
    private taskService : TaskService,
    private router : Router
  ) { 
    this.addLogo = "assets/images/add.png";
  }

  ngOnInit() {
    this.task = new Task();
    this.isEditing=false;

    this.activatedRoute.params.subscribe(
      (params) => {
        let taskId = params['id'];
        if(taskId){
          this.taskService.getTaskById(taskId).subscribe(
            (data) => {
              this.task = data;
              this.isEditing = true;
            }
          );
        }
      }
    );
  }

  save(){
    if(this.isEditing){
      this.taskService.updateTask(this.task).subscribe(
        (data)=>{
          this.router.navigateByUrl("/?opt=u&id="+this.task.taskId);
        },
        (error)=>{alert("Some error occured while updating the task. Please fuck off!");}      
      );
      
    }else{
      this.taskService.addTask(this.task).subscribe(
        (data)=>{
          this.router.navigateByUrl("/?opt=a&id="+data.taskId);
        },
        (error)=>{alert("Some error occured while adding the task. Please fuck off!");}
      );
    }
  }

  doCancel(){
    this.router.navigateByUrl("/listTasks/all");
  }
}
