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
		memIDCounter = DAO.loadData(listOfProjectMember, DAO.PROJECT_MEMBER_FILE_PATH, new ProjectMember());
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
	
	public static void main(String[] args) {
		ProjectMemberController pmc = new ProjectMemberController();
		
		String info = "user-1|proj-2|2";
//		pmc.addProjectMember(info);
//		pmc.addProjectMember(info);//
//		pmc.addProjectMember(info);
//		pmc.addProjectMember(info);
//		pmc.addProjectMember(info);
		
		for (ProjectMember pmMember : pmc.getListOfProjectMember()) {
			System.out.println(pmMember.toString(new ProjectMember()));
		}
		
		pmc.deleteProjectMember("mem-14");
		System.out.println("XOA");
		
		for (ProjectMember pmMember : pmc.getListOfProjectMember()) {
			System.out.println(pmMember.toString(new ProjectMember()));
		}
		
		DAO.saveData(pmc.getListOfProjectMember(), DAO.PROJECT_MEMBER_FILE_PATH, pmc.memIDCounter);
	}
}
