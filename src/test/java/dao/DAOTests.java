package test.java.dao;

import java.util.ArrayList;
import java.util.List;

import main.java.controllers.CommentController;
import main.java.dao.DAO;
import main.java.models.Comment;

public class DAOTests {

	public static void main(String[] args) {
		List<Comment> list = new ArrayList<>();
		Comment obj = new Comment();
		list.add(new Comment("text1", "date1", "task-1", "user-9"));
		list.add(new Comment("text2", "date2", "task-8", "user-2"));
		list.add(new Comment("text4", "date3", "task-3", "user-3"));
		CommentController.commentIDCounter = 7;
		
		DAO.saveData(list, DAO.COMMENT_FILE_PATH, CommentController.commentIDCounter);
		
		List<Comment> listLoader = new ArrayList<>();
		DAO.loadData(listLoader, DAO.COMMENT_FILE_PATH, new Comment());
		for (Comment element : listLoader) {
			System.out.println(element.toString(new Comment()));
		}
	}
}
