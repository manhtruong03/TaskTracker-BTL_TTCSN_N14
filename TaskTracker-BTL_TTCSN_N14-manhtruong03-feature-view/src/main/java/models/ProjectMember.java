package main.java.models;

public class ProjectMember extends TrelloModel {

	private String userID;
	private String projectID;
	private String role;
	
	public ProjectMember() {
		super();
	}
	
	public ProjectMember(String projectMemeberID) {
		super(projectMemeberID);
	}
	
	public ProjectMember(String projectID, String userID) {
		super();
	}

	public ProjectMember(String userID, String projectID, String role) {
		super();
		this.userID = userID;
		this.projectID = projectID;
		this.role = role;
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

	@Override
	public String toString() {
		return "ProjectMember [ " + super.toString() + " userID=" + userID + ", projectID=" + projectID + ", role=" + role + "]";
	}

	
	
}
