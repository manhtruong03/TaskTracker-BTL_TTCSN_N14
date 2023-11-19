package main.java.utils;

import java.util.Comparator;

import main.java.models.TrelloModel;

public class CompareById<T extends TrelloModel> implements Comparator<T> {

	@Override
	public int compare(T o1, T o2) {
		// TODO Auto-generated method stub
		return o1.getId().compareToIgnoreCase(o2.getId());
	}

}
