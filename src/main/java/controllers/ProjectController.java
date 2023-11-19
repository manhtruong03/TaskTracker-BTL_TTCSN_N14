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
		DAO.loadData(listOfProject, DAO.PROJECT_FILE_PATH, new Project());
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
	
}
