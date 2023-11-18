package main.java.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.java.models.Todo;

public class TodoDao {
	public static boolean saveTodos(List<Todo> listOfTodo, String filepath) {
		try {
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filepath)));
			for (Todo todo : listOfTodo) {
				String userIDs = String.join(",", todo.getUserID());
				writer.print(userIDs + "|");
				writer.print(todo.getTaskID() + "|");
				writer.print(todo.getTitle() + "|");
				writer.print(todo.getStatus() + "|");
				writer.print(todo.getDeadline() + "\n");
			}
			writer.close();
			return true;
		} catch (IOException e) {
			System.out.println("An error occurred while saving todo.");
			return false;
		}
	}

	public static List<Todo> loadTodos(String filepath) {
		List<Todo> listOfTodo = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filepath));
			String line = reader.readLine();
			while (line != null) {
				String[] parts = line.split("\\|");
				List<String> userID = new ArrayList<>(Arrays.asList(parts[0].split(",")));
				String taskID = parts[1];
				String title = parts[2];
				String status = parts[3];
				String deadline = parts[4];
				listOfTodo.add(new Todo(userID, taskID, title, status, deadline));
				line = reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error: File not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error: IOException occurred");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("Error: Invalid number format");
			e.printStackTrace();
		}
		return listOfTodo;
	}

//	public static void main(String[] args) {
//		
//		try {
//			// Sử dụng UTF-8 cho console
//			System.setOut(new PrintStream(System.out, true, "UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		
//		List<Todo> ltest = new ArrayList<>();
//		TodoDao tDao = new TodoDao();
//		ltest = tDao.loadTodos();
//		for (Todo todo : ltest) {
//			System.out.println(todo);
//		}
//		System.out.println(tDao.saveTodos(ltest));
//	}
}
