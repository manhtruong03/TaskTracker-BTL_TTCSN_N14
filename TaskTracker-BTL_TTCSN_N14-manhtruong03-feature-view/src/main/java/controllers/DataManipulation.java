package main.java.controllers;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

import main.java.models.Comment;
import main.java.models.TrelloModel;
import main.java.utils.CompareById;

public class DataManipulation {
	
	public static <T extends TrelloModel> int getPositionById(List<T> list, String id) {
		return Collections.binarySearch(list, new TrelloModel(id), new CompareById());
	}
	
	public static <T extends TrelloModel> T createElement(T instance, String message) {
		String[] info = message.split("\\|");
		Field[] fields = instance.getClass().getDeclaredFields();
		
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
		    field.setAccessible(true);
		    try {
		        if (field.getType().equals(int.class)) {
		            field.set(instance, Integer.parseInt(info[i]));
		        } else if (field.getType().equals(double.class)) {
		            field.set(instance, Double.parseDouble(info[i]));
		        } else if (field.getType().equals(boolean.class)) {
		            field.set(instance, Boolean.parseBoolean(info[i]));
		        } else {
		            field.set(instance, info[i]);
		        }
		    } catch (IllegalArgumentException | IllegalAccessException e) {
		        e.printStackTrace();
		    }
		}
		return instance;
	}

	public static <T extends TrelloModel> boolean addElement(List<T> list, T instance, String info) {
		instance = createElement(instance, info);
		return list.add(instance);
	}
	
	public static <T extends TrelloModel> boolean editElement(List<T> list, T instance, String info) {
		int index = getPositionById(list, instance.getId());
		instance = createElement(instance, info);
		list.set(index, instance);
		return true;
	}
	
	public static <T extends TrelloModel> boolean deleteElement(List<T> list, String id) {
		int index = getPositionById(list, id);
		list.remove(index);
		return true;
	}
}
