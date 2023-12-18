package main.java.controllers;

import java.util.ArrayList;
import java.util.List;

import main.java.dao.DAO;
import main.java.models.Project;
import main.java.models.TrelloModel;

public class ProjectController {
	
	public static int projectIDCounter = 0;
	
	private List<Project> listOfProject = new ArrayList<>();

	public ProjectController() {
		super();
		projectIDCounter = DAO.loadData(listOfProject, DAO.PROJECT_FILE_PATH, new Project());
	}

	public ProjectController(List<Project> listOfProject) {
		super();
		this.listOfProject = listOfProject;
	}

	public List<Project> getListOfProject() {
		return listOfProject;
	}

	public void setListOfProject(List<Project> listOfProject) {
		this.listOfProject = listOfProject;
	}

	public boolean addProject(String info) {
		String newID = TrelloModel.generateID("proj", ++projectIDCounter);
		Project proj = new Project(newID);
		return DataManipulation.addElement(listOfProject, proj, info);
	}
	
	public boolean editProject(String id, String info) {
		Project proj = new Project(id);
		return DataManipulation.editElement(listOfProject, proj, info);
	}
	
	public boolean deleteProject(String id) {
		return DataManipulation.deleteElement(listOfProject, id);
	}
	
	public static void main(String[] args) {
		ProjectController pc = new ProjectController();
//		String pc1 = "NameProject1|descrip1|startDate1|endDate|Status1|Avatar1";
//		String pc2 = "NameProject2|descrip1|startDate1|endDate|Status1|Avatar1";
//		String pc3 = "NameProject3|descrip1|startDate1|endDate|Status1|Avatar1";
//		String pc4 = "NameProject4|descrip1|startDate1|endDate|Status1|Avatar1";
//		String pc5 = "NameProject5|descrip1|startDate1|endDate|Status1|Avatar1";
//		String pc6 = "NameProject6|descrip1|startDate1|endDate|Status1|Avatar1";
//		pc.addProject(pc1);
//		pc.addProject(pc2);
//		pc.addProject(pc3);
//		pc.addProject(pc4);
//		pc.addProject(pc5);
//		pc.addProject(pc6);
		
//		DAO.saveData(pc.getListOfProject(), DAO.PROJECT_FILE_PATH, projectIDCounter);
		
		for (Project p : pc.getListOfProject()) {
			System.out.println(p);
		}
		
	}
	
}
