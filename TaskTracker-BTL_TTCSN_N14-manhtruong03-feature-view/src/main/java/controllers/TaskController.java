package main.java.controllers;

import java.util.ArrayList;
import java.util.List;

import main.java.dao.DAO;
import main.java.models.Task;
import main.java.models.TrelloModel;

public class TaskController {
public static int taskIDCounter = 0;
	
	private List<Task> listOfTask = new ArrayList<>();

	public TaskController() {
		super();
		taskIDCounter = DAO.loadData(listOfTask, DAO.TASK_FILE_PATH, new Task());
	}

	public TaskController(List<Task> listOfTask) {
		super();
		this.listOfTask = listOfTask;
	}

	public List<Task> getListOfTask() {
		return listOfTask;
	}

	public void setListOfTask(List<Task> listOfTask) {
		this.listOfTask = listOfTask;
	}

	public boolean addTask(String info) {
		String newID = TrelloModel.generateID("task", ++taskIDCounter);
		Task task = new Task(newID);
		return DataManipulation.addElement(listOfTask, task, info);
	}
	
	public boolean editTask(String id, String info) {
		Task task = new Task(id);
		return DataManipulation.editElement(listOfTask, task, info);
	}
	
	public boolean deleteTask(String id) {
		return DataManipulation.deleteElement(listOfTask, id);
	}
	
	public void changeDes(String id, String infor) {
		for(Task i : listOfTask) {
			if(i.getId().equals(id)) {
				i.setDescription(infor);
			}
		}
	}
	
	public static void main(String[] args) {
		TaskController tc = new TaskController();
//		String t1 = "TaskName1|Des1|CreateDate1|StartDate1|DueDate1|To do|1|proj-1";
//		String t2 = "TaskName2|Des2|CreateDate1|StartDate1|DueDate1|Doing|2|proj-2";
//		String t3 = "TaskName3|Des3|CreateDate1|StartDate1|DueDate1|Done|3|proj-1";
//		String t4 = "TaskName4|Des4|CreateDate1|StartDate1|DueDate1|To do|4|proj-3";
//		
//		tc.addTask(t1);
//		tc.addTask(t2);
//		tc.addTask(t3);
//		tc.addTask(t4);
////		
//		DAO.saveData(tc.getListOfTask(), DAO.TASK_FILE_PATH, taskIDCounter);
		
		for (Task t: tc.getListOfTask()) {
			System.out.println(t.toString());
		}
		
	}
}
