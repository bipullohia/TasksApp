package com.myproject.bipullohia.tasks.model;

import java.util.Date;
import java.text.SimpleDateFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tasks")
public class Task{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int taskId;
	
	@NotEmpty(message="createdAt variable cannot empty")
	private String createdAt;
	
	@NotEmpty(message="updatedAt variable cannot empty")
	private String updatedAt;
	
	@NotEmpty(message="Task Title cannot be empty")
	private String taskTitle;
	
	@NotEmpty(message="Task Body cannot be empty")
	private String taskBody;
	
	@NotNull(message="Please mention if the task is urgent")
	private boolean urgent;
	
	@NotNull(message="Please mention if task is finished")
	private boolean finished;
	
	@NotEmpty(message="Task category cannot be empty")
	private String taskCategory;
	
	
	@PrePersist
	void createdAt() {		
		Date d = new Date();
		//SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a");
		SimpleDateFormat formatter = new SimpleDateFormat("EEE dd-MMM-YYYY hh:mm:ss a");
		String date = formatter.format(d);
		this.createdAt = this.updatedAt = date;
	}
	
	@PreUpdate
	void updatedAt() {
		Date d = new Date();
		//SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a");
		SimpleDateFormat formatter = new SimpleDateFormat("EEE dd-MMM-YYYY hh:mm:ss a");
		String date = formatter.format(d);
		this.updatedAt = date;
	}
	

	public int getTaskId() {
		return taskId;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getTaskBody() {
		return taskBody;
	}

	public void setTaskBody(String taskBody) {
		this.taskBody = taskBody;
	}

	public boolean isUrgent() {
		return urgent;
	}

	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}

	public String getTaskCategory() {
		return taskCategory;
	}

	public void setTaskCategory(String taskCategory) {
		this.taskCategory = taskCategory;
	}
	
}
