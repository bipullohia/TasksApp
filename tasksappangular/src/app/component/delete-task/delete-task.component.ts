import { Component, OnInit } from '@angular/core';
import { Task } from '../../model/task';
import { TaskService } from '../../service/task.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-delete-task',
  templateUrl: './delete-task.component.html',
  styleUrls: ['./delete-task.component.css']
})
export class DeleteTaskComponent implements OnInit {

  task : Task;

  constructor(private taskService: TaskService,
    private activatedRoute : ActivatedRoute,
    private router : Router
    ) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(
      (params) => {
        let taskId = params['id'];
        if(taskId){
          this.taskService.getTaskById(taskId).subscribe(
            (data) => this.task = data
          );
        }
      }
    );
  }

  doDelete(isConfirmed : boolean){
    if(isConfirmed){
      this.taskService.deleteTaskById(this.task.taskId).subscribe(
        (resp) => {
          if(resp.ok){
            this.router.navigateByUrl("/?opt=d&id="+this.task.taskId);
          }
        }
      );
    }else{
      this.router.navigateByUrl("/listTasks/all");
    }
  }
}
