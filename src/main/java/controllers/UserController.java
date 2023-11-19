package main.java.controllers;

import java.util.ArrayList;
import java.util.List;

import main.java.dao.DAO;
import main.java.models.User;
import main.java.models.TrelloModel;

public class UserController {

public static int userIDCounter = 0;
	
	private List<User> listOfUser = new ArrayList<>();

	public UserController() {
		super();
		DAO.loadData(listOfUser, DAO.USER_FILE_PATH, new User());
	}

	public UserController(List<User> listOfUser) {
		super();
		this.listOfUser = listOfUser;
	}

	public List<User> getListOfUser() {
		return listOfUser;
	}

	public void setListOfUser(List<User> listOfUser) {
		this.listOfUser = listOfUser;
	}

	public boolean addUser(String info) {
		String newID = TrelloModel.generateID("user", ++userIDCounter);
		User user = new User(newID);
		return DataManipulation.addElement(listOfUser, user, info);
	}
	
	public boolean editUser(String id, String info) {
		User user = new User(id);
		return DataManipulation.editElement(listOfUser, user, info);
	}
	
	public boolean deleteUser(String id) {
		return DataManipulation.deleteElement(listOfUser, id);
	}
}
