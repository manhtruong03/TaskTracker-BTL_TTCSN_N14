package main.java.models;

public class Project extends TrelloModel {

	private String projectName;
	private String description;
	private String startDate;
	private String endDate;
	private String status;
	private String backGroundImage;
	
	public Project() {
		super();
	}
	
	public Project(String projectID) {
		super(projectID);
	}

	public Project(String projectName, String description, String startDate, 
			String endDate, String status, String backGroundImage) {
		super();
		this.projectName = projectName;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.backGroundImage = backGroundImage;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBackGroundImage() {
		return backGroundImage;
	}

	public void setBackGroundImage(String backGroundImage) {
		this.backGroundImage = backGroundImage;
	}
}
