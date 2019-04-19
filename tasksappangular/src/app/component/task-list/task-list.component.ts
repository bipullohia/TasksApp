import { Component, OnInit } from '@angular/core';
import { Task } from '../../model/task';
import { TaskService } from '../../service/task.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {

  tasks: Task[];
  checkedLogo: string;
  sirenLogo: string;
  garbageLogo: string;
  urgentLogo: string;
  isEditing: boolean;
  isedit:boolean;

  constructor(
    private taskService: TaskService,
    private activatedRoute: ActivatedRoute
  ) {
    this.checkedLogo = "/assets/images/checked1.png";
    this.sirenLogo = "assets/images/pencil3.png";
    this.garbageLogo = "assets/images/garbage5.png";
    this.urgentLogo = "assets/images/star.png";
  }

  ngOnInit() {


    this.isEditing = false;

    this.activatedRoute.params.subscribe(
      (params) => {
        let category = params['category'];
        if (category) {
          console.log("Category Tasks available");
          this.taskService.searchTasks("taskCategory", category).subscribe(
            (data) => {
              this.tasks = data;
              //console.log(this.tasks + "activated");
              this.isEditing = true;
            }
          );
        }else{
          console.log("All Tasks");
          this.taskService.getAllTasks().subscribe(
          (data) => {
            this.tasks = data;
            //console.log(this.tasks + "all tasks");
          }
      );
        }
      }
    );
  }
}
