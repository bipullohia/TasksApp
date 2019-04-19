import { Component, OnInit } from '@angular/core';
import { Task } from '../../model/task';
import { TaskService } from '../../service/task.service';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  tasks: Task[];
  urgentLogo : string;
  checkedLogo : string;

  constructor(private taskService: TaskService) {
    this.urgentLogo = "assets/images/star.png";
    this.checkedLogo = "assets/images/checked1.png";
   }

  ngOnInit() {
    this.taskService.getAllTasks().subscribe(
      data => this.tasks=data
    );

    console.log("Task length: " + this.tasks);
  }

}
