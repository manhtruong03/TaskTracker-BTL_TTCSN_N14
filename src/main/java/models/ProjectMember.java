package main.java.models;

public class ProjectMember extends TrelloModel {

	private String role;
	private String projectID;
	private String userID;
	
	public ProjectMember() {
		super();
	}
	
	public ProjectMember(String projectMemeberID) {
		super(projectMemeberID);
	}
	
	public ProjectMember(String projectID, String userID) {
		super();
	}

	public ProjectMember(String role, String projectID, String userID) {
		super();
		this.role = role;
		this.projectID = projectID;
		this.userID = userID;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getProjectID() {
		return projectID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
}
