package main.java.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

import main.java.controllers.*;
import main.java.models.*;

public class DAO {
	
	public static final String USER_FILE_PATH = "src/assets/data/users.txt";
	public static final String PROJECT_FILE_PATH = "src/assets/data/projects.txt";
	public static final String PROJECT_MEMBER_FILE_PATH = "src/assets/data/projectMembers.txt";
	public static final String TASK_FILE_PATH = "src/assets/data/tasks.txt";
	public static final String TODO_FILE_PATH = "src/assets/data/todos.txt";
	public static final String COMMENT_FILE_PATH = "src/assets/data/comments.txt";
	
	public static <T extends TrelloModel> void saveData(List<T> list, String filePath, int idCounter) {
		try {
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath)));
			writer.println(idCounter);
			
			for (T element : list) {
				writer.println(element.toString(element));
			}
			writer.close();
		} catch (Exception e) {
			System.out.println("Error: An unexpected exception occurred");
	        e.printStackTrace();
		}
	}
	
	public static <T extends TrelloModel> int loadData(List<T> list, String filePath, T obj) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
		    String line = reader.readLine();
		    int counter = Integer.parseInt(line);
		    
			Class<T> type = (Class<T>) obj.getClass();
			List<Field> fields = getAllFields(type);
			Constructor<T> constructor = type.getDeclaredConstructor();
			
		    while ((line = reader.readLine()) != null) {
		    	String[] parts = line.split("\\|");
		    	T instance = constructor.newInstance();
		    	
		    	for (int i = 0; i < fields.size(); ++i) {
		    		Field field = fields.get(i);
		    		field.setAccessible(true);
		    		// Check the type of the field and convert the string value accordingly
	                if (field.getType().equals(int.class)) {
	                    field.set(instance, Integer.parseInt(parts[i]));
	                } else if (field.getType().equals(double.class)) {
	                    field.set(instance, Double.parseDouble(parts[i]));
	                } else if (field.getType().equals(boolean.class)) {
	                    field.set(instance, Boolean.parseBoolean(parts[i]));
	                }
	                else {
	                    field.set(instance, parts[i]);
	                }
		    	}
		    	list.add(instance);    	
		    }
		    reader.close();
		    return counter;
		} catch (FileNotFoundException e) {
	        System.out.println("Error: File not found");
	        e.printStackTrace();
	    } catch (IOException e) {
	        System.out.println("Error: IOException occurred");
	        e.printStackTrace();
	    } catch (NumberFormatException e) {
	        System.out.println("Error: Invalid number format");
	        e.printStackTrace();
	    } catch (ReflectiveOperationException e) {
	        System.out.println("Error: Reflective operation failed");
	        e.printStackTrace();
	    } catch (Exception e) {
	        System.out.println("Error: An unexpected exception occurred");
	        e.printStackTrace();
	    }
		return 0;
	}
	
	public static List<Field> getAllFields(Class<?> type) {
		List<Field> fields = new ArrayList<>();

        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
        	List<Field> temp = new ArrayList<>();
            Field[] declaredFields = c.getDeclaredFields();
            for (Field field : declaredFields) {
            	temp.add(field);
            }
            fields.addAll(0, temp);
        }

        return fields;
    }

}
