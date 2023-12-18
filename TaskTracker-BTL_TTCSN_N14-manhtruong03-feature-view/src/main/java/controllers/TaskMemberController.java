package main.java.controllers;
import java.util.ArrayList;
import java.util.List;

import main.java.dao.DAO;
import main.java.models.*;

public class TaskMemberController {
public static int taskMemIDCounter = 0;
	
	private List<TaskMember> listOfTaskMember = new ArrayList<>();

	public TaskMemberController() {
		super();
		 taskMemIDCounter = DAO.loadData(listOfTaskMember, DAO.TASK_MEMBER_FILE_PATH, new TaskMember());
	}

	public TaskMemberController(List<TaskMember> listOfTaskMember) {
		super();
		this.listOfTaskMember = listOfTaskMember;
	}

	public List<TaskMember> getListOfTaskMembers() {
		return listOfTaskMember;
	}

	public void setListOfTaskMember(List<TaskMember> listOfTaskMember) {
		this.listOfTaskMember = listOfTaskMember;
	}

	public boolean addTaskMember(String info) {
		String newID = TrelloModel.generateID("taskmem", ++taskMemIDCounter);
		TaskMember taskmem = new TaskMember(newID);
		return DataManipulation.addElement(listOfTaskMember, taskmem, info);
	}
	
	public boolean editTaskMember(String id, String info) {
		TaskMember taskmem = new TaskMember(id);
		return DataManipulation.editElement(listOfTaskMember, taskmem, info);
	}
	
	public boolean deleteTaskMember(String id) {
		return DataManipulation.deleteElement(listOfTaskMember, id);
	}
	
	public static void main(String[] args) {
		TaskMemberController tmc = new TaskMemberController();
//		String add0 = "proj-1|user-1|task-1";
//		String add1 = "proj-1|user-2|task-1";
//		String add2 = "proj-1|user-3|task-2";
//		String add3 = "proj-2|user-3|task-2";
//		String add4 = "proj-2|user-4|task-3";
//		String add5 = "proj-3|user-2|task-4";
//		String add6 = "proj-3|user-3|task-3";
//		String add7 = "proj-4|user-5|task-4";
//		
//		tmc.addTaskMember(add0);
//		tmc.addTaskMember(add1);
//		tmc.addTaskMember(add2);
//		tmc.addTaskMember(add3);
//		tmc.addTaskMember(add4);
//		tmc.addTaskMember(add5);
//		tmc.addTaskMember(add6);
//		tmc.addTaskMember(add7);
//		
//		DAO.saveData(tmc.getListOfTaskMembers(), DAO.TASK_MEMBER_FILE_PATH, taskMemIDCounter);
		
		for (TaskMember tm: tmc.getListOfTaskMembers()) {
			System.out.println(tm.toString());
		}
		
	}
}
