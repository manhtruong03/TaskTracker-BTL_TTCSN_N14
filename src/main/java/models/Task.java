package main.java.models;

public class Task extends TrelloModel {
	
	private String taskName;
	private String description;
	private String creationDate;
	private String startDate;
	private String dueDate;
	private String status;
	private String position;
	private String projectID;
	
	public Task() {
		super();
	}
	
	public Task(String taskID) {
		super(taskID);
	}
	
	public Task(String taskID, String projectID) {
		super(taskID);
	}

	public Task(String taskName, String description, String creationDate, String startDate, String dueDate,
			String status, String position) {
		super();
		this.taskName = taskName;
		this.description = description;
		this.creationDate = creationDate;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.status = status;
		this.position = position;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getProjectID() {
		return projectID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	
	
	
}