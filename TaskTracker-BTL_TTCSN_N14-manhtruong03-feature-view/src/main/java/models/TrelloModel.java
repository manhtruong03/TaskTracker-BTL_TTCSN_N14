package main.java.models;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class TrelloModel {
	
	private String id;

	public TrelloModel() {
		super();
	}

	public TrelloModel(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public static String generateID(String type, int counter) {
		return type + "-" + counter;
	}
	
	public <T extends TrelloModel> String toString(T obj) {
		String str = id;
		for (Field field : obj.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			try {
				str += "|" + field.get(this);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return str;
	}
	
	@Override
	public String toString() {
		return this.getId();
	}
	
}
