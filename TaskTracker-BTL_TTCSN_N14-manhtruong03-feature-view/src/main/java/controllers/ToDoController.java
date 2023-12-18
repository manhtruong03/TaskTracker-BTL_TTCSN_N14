package main.java.controllers;

import java.util.ArrayList;
import java.util.List;

import main.java.dao.DAO;
import main.java.models.*;

public class ToDoController {

public static int todoIDCounter = 0;
	
	private List<ToDo> listOfToDo = new ArrayList<>();

	public ToDoController() {
		super();
		DAO.loadData(listOfToDo, DAO.TODO_FILE_PATH, new ToDo());
	}

	public ToDoController(List<ToDo> listOfToDo) {
		super();
		this.listOfToDo = listOfToDo;
	}

	public List<ToDo> getListOfToDo() {
		return listOfToDo;
	}

	public void setListOfToDo(List<ToDo> listOfToDo) {
		this.listOfToDo = listOfToDo;
	}

	public boolean addToDo(String info) {
		String newID = TrelloModel.generateID("todo", ++todoIDCounter);
		ToDo todo = new ToDo(newID);
		return DataManipulation.addElement(listOfToDo, todo, info);
	}
	
	public boolean editToDo(String id, String info) {
		ToDo todo = new ToDo(id);
		return DataManipulation.editElement(listOfToDo, todo, info);
	}
	
	public boolean deleteToDo(String id) {
		return DataManipulation.deleteElement(listOfToDo, id);
	}
}
