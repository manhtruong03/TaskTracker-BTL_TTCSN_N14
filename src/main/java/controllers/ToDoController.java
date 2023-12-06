package main.java.controllers;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.java.dao.TodoDao;
import main.java.models.Todo;
import main.java.models.TrelloModel;

public class ToDoController {

	Scanner sc = new Scanner(System.in);

	private List<Todo> listOfToDo = new ArrayList<>();

	public ToDoController() {
		super();
		this.listOfToDo = TodoDao.loadTodos(TodoDao.TODO_FILE_PATH);
	}

	public ToDoController(List<Todo> listOfToDo) {
		super();
		this.listOfToDo = listOfToDo;
	}

	public List<Todo> getListOfToDo() {
		return listOfToDo;
	}

	public void setListOfToDo(List<Todo> listOfToDo) {
		this.listOfToDo = listOfToDo;
	}

	@Override
	public String toString() {
		return "ToDoController [listOfToDo=" + listOfToDo + "]";
	}

//	public boolean addToDo(String info) {
//		String newID = TrelloModel.generateID("todo", ++todoIDCounter);
//		Todo todo = new Todo(newID);
//		return DataManipulation.addElement(listOfToDo, todo, info);
//	}
//	
//	public boolean editToDo(String id, String info) {
//		Todo todo = new Todo(id);
//		return DataManipulation.editElement(listOfToDo, todo, info);
//	}
//	
//	public boolean deleteToDo(String id) {
//		return DataManipulation.deleteElement(listOfToDo, id);
//	}

//	// Output Todo
//	public void viewTodo() {
//		List<Todo> listTodo = this.getListOfToDo();
//		for (Todo todo : listTodo) {
//			System.out.println(todo);
//		}
//	}
//
//	// Create Todo
//	public Todo CreateTodo() {
//		List<String> listUserID = new ArrayList<>();
//		String nUserID = "";
//		byte temp = 0;
//		System.out.println("Enter a list of user IDs:");
//		while (true) {
//			System.out.print("\tEnter 1 if continue, enter 0 if finished:");
//			temp = sc.nextByte();
//			sc.nextLine();
//			if (temp == (byte) 0)
//				break;
//			System.out.print("\t\tEnter user ID: ");
//			nUserID = sc.nextLine();
//			listUserID.add(nUserID);
//		}
//		System.out.print("Enter taskID: ");
//		String taskID = sc.nextLine();
//		System.out.print("Enter title: ");
//		String title = sc.nextLine();
//		System.out.print("Enter status: ");
//		String status = sc.nextLine();
//		System.out.print("Enter deadline: ");
//		String deadline = sc.nextLine();
//		CounterID c = new CounterID();
//		Todo newTodo = new Todo(deadline, taskID, title, status, listUserID);
//		newTodo.setId(TrelloModel.generateID("TODO", c.getCount()));
//		listOfToDo.add(newTodo);
//		return newTodo;
//	}
//
//	// Assign Todo
//	public void assignTodo() {
//		System.out.print("Enter the task ID you want to assign: ");
//		String taskID = sc.nextLine();
//		Todo todo = findTodoByTaskID(taskID);
//		if (todo == null) {
//			System.out.println("Task not found!");
//			return;
//		}
//		List<String> assignees = new ArrayList<>();
//		String assignee = "";
//		byte temp = 0;
//		System.out.println("Enter the user ID you want to assign:");
//		while (true) {
//			System.out.print("\tEnter 1 if continue, enter 0 if finished:");
//			temp = sc.nextByte();
//			sc.nextLine();
//			if (temp == (byte) 0)
//				break;
//			System.out.print("\t\tEnter user ID: ");
//			assignee = sc.nextLine();
//			assignees.add(assignee);
//		}
//		todo.setUserID(assignees);
//		System.out.println("Task assigned to: " + assignees);
//		saveToFile(listOfToDo);
//	}
//
//	// Tìm Todo theo taskID
//	private Todo findTodoByTaskID(String taskID) {
//		for (Todo todo : listOfToDo) {
//			if (todo.getTaskID().equals(taskID)) {
//				return todo;
//			}
//		}
//		return null;
//	}
//
//	// Edit Todo
//	public void editTodo(String taskID, String newTitle, String newStatus, String newDeadline) {
//		for (Todo todo : listOfToDo) {
//			if (todo.getTaskID().equals(taskID)) {
//				// Edit task details
//				todo.setTitle(newTitle);
//				todo.setStatus(newStatus);
//				todo.setDeadLine(newDeadline);
//				System.out.println("Task updated successfully.");
//				return;
//			}
//		}
//		System.out.println("Task not found.");
//	}
//
//	// Delete Todo
//	public void deleteTodo(String taskID) {
//		listOfToDo.removeIf(todo -> todo.getTaskID().equals(taskID));
//		System.out.println("Task deleted successfully.");
//	}
//
//	// Updated Todo Status
//	public void updateTodoStatus(String taskID, String newStatus) {
//		for (Todo todo : listOfToDo) {
//			if (todo.getTaskID().equals(taskID)) {
//				// Update task status
//				todo.setStatus(newStatus);
//				System.out.println("Task status updated to: " + newStatus);
//				return;
//			}
//		}
//		System.out.println("Task not found.");
//	}
//
//	public void loadTodo() {
//		// Load danh sách Todo từ file khi tạo đối tượng todoController
//		System.out.println("Enter the file path you want to load:");
//		String filepath = sc.nextLine();
//		this.listOfToDo = TodoDao.loadTodos(filepath);
//	}
//
//	private void saveToFile(List<Todo> todoList) {
//		System.out.println("Enter the file path you want to save: ");
//		String filepath = sc.nextLine();
//		boolean saved = TodoDao.saveTodos(todoList, filepath);
//		if (saved) {
//			System.out.println("Todo list saved to file successfully.");
//		} else {
//			System.out.println("Error saving todo list to file.");
//		}
//	}
//
//	public static void main(String[] args) {
//		try {
//			// Sử dụng UTF-8 cho console
//			System.setOut(new PrintStream(System.out, true, "UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//
//		ToDoController todoController = new ToDoController();
//
//		todoController.loadTodo();
//
//		// Hiển thị danh sách Todo từ file khi khởi tạo
//		System.out.println("Initial Todo List:");
//		todoController.viewTodo();
//
//		// Thêm một Todo mới
//		Todo todo = todoController.CreateTodo();
//
//		// Hiển thị danh sách Todo sau khi thêm mới
//		System.out.println("Todo List after adding new todo:");
//		todoController.viewTodo();
//
//		// Lưu danh sách Todo vào file
//		todoController.saveToFile(todoController.getListOfToDo());
//
//		// Gán Todo cho một hoặc nhiều người
//		todoController.assignTodo();
//
//		// Hiển thị danh sách Todo sau khi gán
//		System.out.println("Todo List after assigning todo:");
//		todoController.viewTodo();
//
//		// Lưu danh sách Todo vào file sau khi gán
//		todoController.saveToFile(todoController.getListOfToDo());
//
//		// ... Tiếp tục với các bước kiểm thử khác
//
//		// Hiển thị danh sách Todo sau khi đã thực hiện các thay đổi
//		System.out.println("Updated Todo List:");
//		todoController.viewTodo();
//	}
}
