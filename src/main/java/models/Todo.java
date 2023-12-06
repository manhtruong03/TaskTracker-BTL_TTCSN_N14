package main.java.models;

import java.util.List;

public class Todo extends TrelloModel {

	private String title;
	private String status;
	private String deadLine;
	private String taskID;
	private List<String> userID;

	public Todo() {
		super();
	}

	public Todo(String todoID) {
		super(todoID);
	}

	public Todo(String taskID, List<String> userID) {
		super();
	}

	public Todo(String title, String status, String deadLine, String taskID, List<String> userID) {
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

	public List<String> getUserID() {
		return userID;
	}

	public void setUserID(List<String> userID) {
		this.userID = userID;
	}

	@Override
	public String toString() {
		return "Todo [" + super.toString() + ", title=" + title + ", status=" + status + ", deadLine=" + deadLine
				+ ", taskID=" + taskID + ", userID=" + userID + "]";
	}

}
