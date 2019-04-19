package com.myproject.bipullohia.tasks.service;

import java.util.List;

import com.myproject.bipullohia.tasks.model.Task;

public interface TaskService {

	Task getTaskById(int taskId);
	List<Task> getAllTasks();
	Task addTask(Task task);
	Task updateTask(Task task);
	boolean deleteTask(int taskId);
	
	boolean existsById(int taskId);
	List<Task> findAllByTaskCategory(String taskCategory);
	List<Task> findAllByUrgent(boolean isUrgent);
}
