package main.java.controllers;

import java.util.ArrayList;
import java.util.List;

import main.java.dao.DAO;
import main.java.models.*;

public class ProjectMemberController {
public static int memIDCounter = 0;
	
	private List<ProjectMember> listOfProjectMember = new ArrayList<>();

	public ProjectMemberController() {
		super();
		DAO.loadData(listOfProjectMember, DAO.PROJECT_MEMBER_FILE_PATH, new ProjectMember());
	}

	public ProjectMemberController(List<ProjectMember> listOfProjectMember) {
		super();
		this.listOfProjectMember = listOfProjectMember;
	}

	public List<ProjectMember> getListOfProjectMember() {
		return listOfProjectMember;
	}

	public void setListOfProjectMember(List<ProjectMember> listOfProjectMember) {
		this.listOfProjectMember = listOfProjectMember;
	}

	public boolean addProjectMember(String info) {
		String newID = TrelloModel.generateID("mem", ++memIDCounter);
		ProjectMember mem = new ProjectMember(newID);
		return DataManipulation.addElement(listOfProjectMember, mem, info);
	}
	
	public boolean editProjectMember(String id, String info) {
		ProjectMember mem = new ProjectMember(id);
		return DataManipulation.editElement(listOfProjectMember, mem, info);
	}
	
	public boolean deleteProjectMember(String id) {
		return DataManipulation.deleteElement(listOfProjectMember, id);
	}
}
