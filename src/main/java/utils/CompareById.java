package main.java.utils;

import java.util.Comparator;

import main.java.models.TrelloModel;

public class CompareById<T extends TrelloModel> implements Comparator<T> {

	@Override
	public int compare(T o1, T o2) {
		// TODO Auto-generated method stub
		if (o1.getId().length() > o2.getId().length()) {
			return 1;
		}
		
		if (o1.getId().length() < o2.getId().length()) {
			return -1;
		}
		
		return o1.getId().toLowerCase().compareTo(o2.getId().toLowerCase());
	}

}
