package main.java.models;

public class TaskMember extends TrelloModel {

	private String projectID;
	private String userID;
	private String taskID;

	public TaskMember() {
		super();
	}

	public TaskMember(String projectMemeberID) {
		super(projectMemeberID);
	}

	public TaskMember(String projectID, String userID) {
		super();
	}

	public TaskMember(String projectID, String userID, String taskID) {
		super();
		this.projectID = projectID;
		this.userID = userID;
		this.taskID = taskID;
	}

	public String getProjectID() {
		return projectID;
	}

	public String getUserID() {
		return userID;
	}

	public String getTaskID() {
		return taskID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	@Override
	public String toString() {
		return "TaskMember [projectID=" + projectID + ", userID=" + userID + ", taskID=" + taskID + "]";
	}

	
}
