package com.myproject.bipullohia.tasks.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.bipullohia.tasks.model.Task;
import com.myproject.bipullohia.tasks.service.TaskService;

@RestController
@CrossOrigin
@RequestMapping(value="/tasks")
public class TaskApi {

	@Autowired
	private TaskService service;
	
	@GetMapping
	public ResponseEntity<List<Task>> getAllTasks(){
		return new ResponseEntity<>(
			service.getAllTasks(), HttpStatus.OK
			);
	}
	
	@GetMapping("/{taskId}")
	public ResponseEntity<Task> getTaskById(@PathVariable("taskId") int taskId){
		
		ResponseEntity<Task> resp;
		Task task = service.getTaskById(taskId);
		
		if(task==null)
			resp=new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			resp=new ResponseEntity<>(task, HttpStatus.OK);
		
		return resp;
	}
	
	@GetMapping("/{field}/{srhValue}")
	public ResponseEntity<List<Task>> getAllTasks(
			@PathVariable("field") String fieldBy,
			@PathVariable("srhValue") String searchValue
			){
		ResponseEntity<List<Task>> resp = null;
		
		switch(fieldBy) {
		
		case "taskCategory":
			List<Task> results = service.findAllByTaskCategory(searchValue);
			
			if(results != null && results.size() != 0)
				resp = new ResponseEntity<>(results, HttpStatus.OK);
			else
				resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
			break;
			
		case "urgent":
			List<Task> result = service.findAllByUrgent(Boolean.parseBoolean(searchValue));
			
			if(result != null && result.size() != 0)
				resp = new ResponseEntity<>(result, HttpStatus.OK);
			else
				resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
			break;
		
		default:
			System.out.println("Default Switch argument passed");
			resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	
		return resp;
	}
	
	@PostMapping
	public ResponseEntity<Task> addTask(@RequestBody Task task){
		
		ResponseEntity<Task> resp = null;		
		
		if(resp==null) {
			Task t = service.addTask(task);
			if(t == null)
				resp = new ResponseEntity<Task>(HttpStatus.BAD_REQUEST);
			else
				resp = new ResponseEntity<Task>(t, HttpStatus.OK);
		}
		return resp;
	}
	
	@PutMapping
	public ResponseEntity<Task> updateTask(@RequestBody Task task){
		
		ResponseEntity<Task> resp = null;		
		Task t = service.getTaskById(task.getTaskId());
		
		if(resp==null) {
			t = service.updateTask(task);
			if(t == null)
				resp = new ResponseEntity<Task>(HttpStatus.BAD_REQUEST);
			else
				resp = new ResponseEntity<Task>(t, HttpStatus.OK);
		}
		return resp;
	}
	
	@DeleteMapping("/{taskId}")
	public ResponseEntity<Void> deleteTask(@PathVariable("taskId") int taskId){
		
		ResponseEntity<Void> resp = null;
		
		if(service.deleteTask(taskId))
			resp = new ResponseEntity<>(HttpStatus.OK);
		else
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return resp;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
