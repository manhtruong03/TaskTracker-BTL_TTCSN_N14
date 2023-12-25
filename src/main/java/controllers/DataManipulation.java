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
		int index = 0;
		try {
			index = Collections.binarySearch(list, new TrelloModel(id), new CompareById());
//			Collections.bi
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("lỗi ở đây");
		}
		return index;
	}
	
	public static <T extends TrelloModel> T createElement(T instance, String message) {
		String[] info = message.split("\\|");
		Field[] fields = instance.getClass().getDeclaredFields();
		
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			try {
				fields[i].set(instance, info[i]);
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
		System.out.println("Pos: " + index);
		list.remove(index);
		return true;
	}
}
