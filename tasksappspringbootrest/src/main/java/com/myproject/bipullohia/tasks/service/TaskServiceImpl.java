package com.myproject.bipullohia.tasks.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.bipullohia.tasks.dao.TaskRepository;
import com.myproject.bipullohia.tasks.model.Task;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepo;
	
	@Override
	public Task getTaskById(int taskId) {
		
		Task task = null;
		
		Optional<Task> optTask = taskRepo.findById(taskId);
		if(optTask.isPresent()) {
			task = optTask.get();
		}
		return task;
	}

	@Override
	public List<Task> getAllTasks() {
		return taskRepo.findAll();
	}

	@Override
	public Task addTask(Task task) {
		return taskRepo.save(task);
	}

	@Override
	public Task updateTask(Task task) {
		return taskRepo.save(task);
	}

	@Override
	public boolean deleteTask(int taskId) {
		boolean isDeleted=false;
		if(taskRepo.existsById(taskId)) {
			taskRepo.deleteById(taskId);
			isDeleted=true;
		}
		return isDeleted;
	}

	@Override
	public boolean existsById(int taskId) {
		return taskRepo.existsById(taskId);
	}

	@Override
	public List<Task> findAllByTaskCategory(String taskCategory) {
		return taskRepo.findAllByTaskCategory(taskCategory);
	}

	@Override
	public List<Task> findAllByUrgent(boolean urgent) {
		return taskRepo.findAllByUrgent(urgent);
	}

}
