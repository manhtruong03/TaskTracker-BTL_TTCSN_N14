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
}
