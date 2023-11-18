package main.java.models;

import java.util.List;

public class Todo {
	private List<String> UserID;
	private String TaskID;
	private String Title;
	private String Status;
	private String Deadline;

	public Todo() {

	}

	public Todo(List<String> userID, String taskID, String title, String status, String deadline) {
		this.UserID = userID;
		this.TaskID = taskID;
		this.Title = title;
		this.Status = status;
		this.Deadline = deadline;
	}

	public List<String> getUserID() {
		return UserID;
	}

	public String getTaskID() {
		return TaskID;
	}

	public String getTitle() {
		return Title;
	}

	public String getStatus() {
		return Status;
	}

	public String getDeadline() {
		return Deadline;
	}

	public void setUserID(List<String> userID) {
		UserID = userID;
	}

	public void setTaskID(String taskID) {
		TaskID = taskID;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public void setDeadline(String deadline) {
		Deadline = deadline;
	}

	@Override
	public String toString() {
		return "Todo [UserID: " + UserID + ", TaskID: " + TaskID + ", Title: " + Title + ", Status: " + Status
				+ ", Deadline: " + Deadline + "]";
	}

}
