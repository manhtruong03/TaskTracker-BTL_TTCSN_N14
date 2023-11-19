package main.java.models;

public class ToDo extends TrelloModel {

	private String title;
	private String status;
	private String deadLine;
	private String taskID;
	private String userID;
	
	public ToDo() {
		super();
	}
	
	public ToDo(String todoID) {
		super(todoID);
	}
	
	public ToDo(String taskID, String userID) {
		super();
	}

	public ToDo(String title, String status, String deadLine, String taskID, String userID) {
		super();
		this.title = title;
		this.status = status;
		this.deadLine = deadLine;
		this.taskID = taskID;
		this.userID = userID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}

	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	
}
