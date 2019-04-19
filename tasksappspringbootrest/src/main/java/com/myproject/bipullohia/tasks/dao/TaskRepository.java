package com.myproject.bipullohia.tasks.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myproject.bipullohia.tasks.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{

	boolean existsById(int id);
	
	List<Task> findAllByTaskCategory(String taskCategory);
	List<Task> findAllByUrgent(boolean urgent);
}
