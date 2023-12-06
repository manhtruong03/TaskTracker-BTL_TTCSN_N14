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
import main.java.models.TrelloModel;

public class TodoDao {
	public static final String TODO_FILE_PATH = "src/assets/data/todotestload.txt";
	
	public static boolean saveTodos(List<Todo> listOfTodo, String filepath) {
		try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filepath)))) {
			for (Todo todo : listOfTodo) {
				String userIDs = String.join(",", todo.getUserID());
				writer.print(todo.getId() + "|" + userIDs + "|" + todo.getTaskID() + "|" + todo.getTitle() + "|"
						+ todo.getStatus() + "|" + todo.getDeadLine());
				if (!listOfTodo.get(listOfTodo.size() - 1).equals(todo)) {
					writer.println();
				}
			}
			return true;
		} catch (IOException e) {
			System.out.println("An error occurred while saving todos.");
			e.printStackTrace();
			return false;
		}
	}

	public static List<Todo> loadTodos(String filepath) {
		List<Todo> listOfTodo = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
			String line;
			Counter c = new Counter();
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("\\|");
//				String id = parts[0];
//				List<String> userID = Arrays.asList(parts[1].split(","));
//				String taskID = parts[2];
//				String title = parts[3];
//				String status = parts[4];
//				String deadLine = parts[5];
				List<String> userID = Arrays.asList(parts[0].split(","));
				String taskID = parts[1];
				String title = parts[2];
				String status = parts[3];
				String deadLine = parts[4];
				Todo todo = new Todo(title, status, deadLine, taskID, userID);
				todo.setId(TrelloModel.generateID("TODO", c.getCount())); // Set the ID for the Todo object
				listOfTodo.add(todo);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error: File not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error: IOException occurred");
			e.printStackTrace();
		}
		return listOfTodo;
	}
}

class Counter {
	private static int count;

	public Counter() {
		Counter.count = 0;
	}

	public static int getCount() {
		return Counter.count++;
	}
}
