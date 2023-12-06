package test.java.dao;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import main.java.dao.TodoDao;
import main.java.models.Todo;

public class DAOTodoTest {
	public static void main(String[] args) {
		try {
			// Sử dụng UTF-8 cho console
			System.setOut(new PrintStream(System.out, true, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		List<Todo> ltest = new ArrayList<>();
		TodoDao tDao = new TodoDao();
		ltest = tDao.loadTodos("D:\\ThucTapCoSoNganh\\BTL\\TaskTracker-BTL_TTCSN_N14-main\\src\\assets\\data\\todotestload.txt");
		for (Todo todo : ltest) {
			System.out.println(todo);
		}
		System.out.println(tDao.saveTodos(ltest,
				"D:\\ThucTapCoSoNganh\\BTL\\TaskTracker-BTL_TTCSN_N14-main\\src\\assets\\data\\todotestsave.txt"));
	}
}
