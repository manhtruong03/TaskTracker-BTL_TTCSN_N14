package main.java.utils;

import java.util.Comparator;

import main.java.models.Task;

public class CompareByPosition implements Comparator<Task> {

	@Override
	public int compare(Task o1, Task o2) {
		return o1.getPosition() - o2.getPosition();
	}

}
