package test.java.controller;

import java.io.IOException;

import main.java.controllers.TaskController;
import main.java.models.Task;

public class TaskControllerTests {
	public static void main(String[] args) throws IOException {
		TaskController test = new TaskController();
		test.addTask("Text1|Date|TaskID|UserID|1|2|3|4");
		test.addTask("Text2|Date|TaskID|UserID");
		test.addTask("Text3|Date|TaskID|UserID");
		
		for (Task cmt : test.getListOfTask()) {
			System.out.println(cmt.getId());
			System.out.println(cmt.toString(cmt));
		}
		
		test.editTask("task-1", "Text-0|Date-0|TaskID-0|UserID-0");
		System.out.println("=========EDIT===========");
		for (Task cmt : test.getListOfTask()) {
			System.out.println(cmt.toString(cmt));
		}
		
		test.deleteTask("task-1");
		System.out.println("=========DELETE===========");
		test.viewTask();
		test.updateTaskStatus("task-2", "alabatrap");
		test.changeTaskPosition("task-4", "god");
		System.out.println("=========CHANGESTATUSPOSITION===========");
		for (Task cmt : test.getListOfTask()) {
			System.out.println(cmt.getId());
			System.out.println(cmt.toString(cmt));
		}
		test.saveTaskController();
		test = new TaskController();
	}
}