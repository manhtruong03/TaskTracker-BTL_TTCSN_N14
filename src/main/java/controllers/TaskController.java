package main.java.controllers;


import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;


import main.java.dao.DAO;
import main.java.models.Task;
import main.java.models.TrelloModel;


public class TaskController extends TrelloController{
	
	private List<Task> listOfTask = new ArrayList<>();
	
	public TaskController(){
		super();
		DAO.loadData(listOfTask, DAO.TASK_FILE_PATH, new Task());
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
		String newID = TrelloModel.generateID("task", ++IDCounter);
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
	
	public void viewTask() {
		for(Task i : listOfTask) {
			System.out.println(i.toString(i));
		}
	}
	
	public void updateTaskStatus(String id, String info) {
		for (Task i : listOfTask) {
			if(i.getId().equals(id)) {
				i.setStatus(info);
				return;
			}
		}
	}
	
	public void changeTaskPosition(String id, String info){
		for (Task i : listOfTask) {
			if(i.getId().equals(id)) {
				i.setPosition(info);
				return;
			}
		}
	}
	
	public void saveTaskController() {
		DAO.saveData(listOfTask, DAO.TASK_FILE_PATH, IDCounter);
	}
	
	public void updateTaskController() {
		DAO.loadData(listOfTask, DAO.TASK_FILE_PATH, new Task());
	}
}
