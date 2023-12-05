package main.java.models;

public class Comment extends TrelloModel {

	private String text;
	private String dateAdded;
	private String taskID;
	private String userID;
	
	public Comment() {
		super();
	}
	
	public Comment(String commentID) {
		super(commentID);
	}
	
	public Comment(String commentID, String text, String dateAdded, String taskID, String userID) {
		super(commentID);
		this.text = text;
		this.dateAdded = dateAdded;
		this.taskID = taskID;
		this.userID = userID;
	}
	
	public Comment(String text, String dateAdded, String taskID, String userID) {
		super();
		this.text = text;
		this.dateAdded = dateAdded;
		this.taskID = taskID;
		this.userID = userID;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getDateAdded() {
		return dateAdded;
	}
	
	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
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