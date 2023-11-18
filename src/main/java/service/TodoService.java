package main.java.service;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.java.dao.TodoDao;
import main.java.models.*;

public class TodoService {

	Scanner sc = new Scanner(System.in);

	// properties
	private List<Todo> listTodo;

	// Constructor
	public TodoService() {

	}

	public TodoService(List<Todo> listTodo) {
		this.listTodo = listTodo;
	}

	// Getter and Setter
	public List<Todo> getListTodo() {
		return listTodo;
	}

	public void setListTodo(List<Todo> listTodo) {
		this.listTodo = listTodo;
	}

	@Override
	public String toString() {
		return "TodoSevices [listTodo=" + listTodo + "]";
	}

	// Output Todo
	public void viewTodo() {
		List<Todo> listTodo = this.getListTodo();
		for (Todo todo : listTodo) {
			System.out.println(todo);
		}
	}

	// Create Todo
	public Todo CreateTodo() {
		List<String> listUserID = new ArrayList<>();
		String nUserID = "";
		byte temp = 0;
		System.out.println("Enter a list of user IDs:");
		while (true) {
			System.out.print("\tEnter 1 if continue, enter 0 if finished:");
			temp = sc.nextByte();
			sc.nextLine();
			if(temp == (byte)0) break;
			System.out.print("\t\tEnter user ID: ");
			nUserID = sc.nextLine();
			listUserID.add(nUserID);
		}
		System.out.print("Enter taskID: ");
		String taskID = sc.nextLine();
		System.out.print("Enter title: ");
		String title = sc.nextLine();
		System.out.print("Enter status: ");
		String status = sc.nextLine();
		System.out.print("Enter deadline: ");
		String deadline = sc.nextLine();
		Todo newTodo = new Todo(listUserID, taskID, title, status, deadline);
		listTodo.add(newTodo);
		return newTodo;
	}

	// Assign Todo
	public void assignTodo() {
		System.out.print("Enter the task ID you want to assign: ");
		String taskID = sc.nextLine();
		Todo todo = findTodoByTaskID(taskID);
		if(todo == null) {
			System.out.println("Task not found!");
			return;
		}
		List<String> assignees = new ArrayList<>();
		String assignee = "";
		byte temp = 0;
		System.out.println("Enter the user ID you want to assign:");
		while (true) {
			System.out.print("\tEnter 1 if continue, enter 0 if finished:");
			temp = sc.nextByte();
			sc.nextLine();
			if(temp == (byte)0) break;
			System.out.print("\t\tEnter user ID: ");
			assignee = sc.nextLine();
			assignees.add(assignee);
		}
		todo.setUserID(assignees);
		System.out.println("Task assigned to: " + assignees);
		saveToFile(listTodo);
	}

	// Tìm Todo theo taskID
	private Todo findTodoByTaskID(String taskID) {
		for (Todo todo : listTodo) {
			if (todo.getTaskID().equals(taskID)) {
				return todo;
			}
		}
		return null;
	}

	// Edit Todo
	public void editTodo(String taskID, String newTitle, String newStatus, String newDeadline) {
		for (Todo todo : listTodo) {
			if (todo.getTaskID().equals(taskID)) {
				// Edit task details
				todo.setTitle(newTitle);
				todo.setStatus(newStatus);
				todo.setDeadline(newDeadline);
				System.out.println("Task updated successfully.");
				return;
			}
		}
		System.out.println("Task not found.");
	}

	// Delete Todo
	public void deleteTodo(String taskID) {
		listTodo.removeIf(todo -> todo.getTaskID().equals(taskID));
		System.out.println("Task deleted successfully.");
	}

	// Updated Todo Status
	public void updateTodoStatus(String taskID, String newStatus) {
		for (Todo todo : listTodo) {
			if (todo.getTaskID().equals(taskID)) {
				// Update task status
				todo.setStatus(newStatus);
				System.out.println("Task status updated to: " + newStatus);
				return;
			}
		}
		System.out.println("Task not found.");
	}

	public void loadTodo() {
		// Load danh sách Todo từ file khi tạo đối tượng TodoService
		System.out.println("Enter the file path you want to load:");
		String filepath = sc.nextLine();
		this.listTodo = TodoDao.loadTodos(filepath);
	}

	private void saveToFile(List<Todo> todoList) {
		System.out.println("Enter the file path you want to save: ");
		String filepath = sc.nextLine();
		boolean saved = TodoDao.saveTodos(todoList, filepath);
		if (saved) {
			System.out.println("Todo list saved to file successfully.");
		} else {
			System.out.println("Error saving todo list to file.");
		}
	}

	public static void main(String[] args) {
		try {
			// Sử dụng UTF-8 cho console
			System.setOut(new PrintStream(System.out, true, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		TodoService todoService = new TodoService();

		todoService.loadTodo();

		// Hiển thị danh sách Todo từ file khi khởi tạo
		System.out.println("Initial Todo List:");
		todoService.viewTodo();

		// Thêm một Todo mới
		Todo todo = todoService.CreateTodo();

		// Hiển thị danh sách Todo sau khi thêm mới
		System.out.println("Todo List after adding new todo:");
		todoService.viewTodo();

		// Lưu danh sách Todo vào file
		todoService.saveToFile(todoService.getListTodo());

		// Gán Todo cho một hoặc nhiều người
		todoService.assignTodo();

		// Hiển thị danh sách Todo sau khi gán
		System.out.println("Todo List after assigning todo:");
		todoService.viewTodo();

		// Lưu danh sách Todo vào file sau khi gán
		todoService.saveToFile(todoService.getListTodo());

		// ... Tiếp tục với các bước kiểm thử khác

		// Hiển thị danh sách Todo sau khi đã thực hiện các thay đổi
		System.out.println("Updated Todo List:");
		todoService.viewTodo();
	}
}
