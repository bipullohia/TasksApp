import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators'
import { Task } from '../model/task';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  baseUrl : string;

  constructor(private http : Http) {
    this.baseUrl = "http://localhost:7777/tasks";
   }

   getBaseUrlById(id:number): string{
     return this.baseUrl + "/" + id;
   }

   getSearchUrl(field:string, value:string):string{
     return this.baseUrl + "/" + field + "/" + value; 
   }

   getJsonContentTypeHeader(): RequestOptions{
     const headers = new Headers();
     headers.append('Content-Type', 'application/json');
     return new RequestOptions({headers: headers});
   }

   getAllTasks(): Observable<Task[]>{
     return this.http.get(this.baseUrl).pipe(
       map(data => data.json())
     );
   }

   searchTasks(field:string, value:string): Observable<Task[]>{
     return this.http.get(this.getSearchUrl(field, value)).pipe(
       map(data => data.json())
     );
   }

   getTaskById(id:number):Observable<Task>{
     return this.http.get(this.getBaseUrlById(id)).pipe(
       map(data => data.json())
     );
   }

   deleteTaskById(id:number): Observable<Response>{
     return this.http.delete(this.getBaseUrlById(id));
   }

   addTask(task: Task):Observable<Task>{
     return this.http.post(this.baseUrl, JSON.stringify(task),
      this.getJsonContentTypeHeader()).pipe(
        map(data => data.json())
      );
   }

   updateTask(task: Task): Observable<Task>{
     return this.http.put(this.baseUrl, JSON.stringify(task),
     this.getJsonContentTypeHeader()).pipe(
       map(data => data.json())
     );
   }
}
