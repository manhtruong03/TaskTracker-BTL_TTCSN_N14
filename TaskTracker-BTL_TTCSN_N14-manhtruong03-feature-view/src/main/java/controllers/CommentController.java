package main.java.controllers;

import java.util.ArrayList;
import java.util.List;

import main.java.dao.DAO;
import main.java.models.Comment;
import main.java.models.TrelloModel;

public class CommentController {
	
	public static int commentIDCounter = 0;
	
	private List<Comment> listOfComment = new ArrayList<>();

	public CommentController() {
		super();
		DAO.loadData(listOfComment, DAO.COMMENT_FILE_PATH, new Comment());
	}

	public CommentController(List<Comment> listOfComment) {
		super();
		this.listOfComment = listOfComment;
	}

	public List<Comment> getListOfComment() {
		return listOfComment;
	}

	public void setListOfComment(List<Comment> listOfComment) {
		this.listOfComment = listOfComment;
	}

	public boolean addComment(String info) {
		String newID = TrelloModel.generateID("cmt", ++commentIDCounter);
		Comment cmt = new Comment(newID);
		return DataManipulation.addElement(listOfComment, cmt, info);
	}
	
	public boolean editComment(String id, String info) {
		Comment cmt = new Comment(id);
		return DataManipulation.editElement(listOfComment, cmt, info);
	}
	
	public boolean deleteComment(String id) {
		return DataManipulation.deleteElement(listOfComment, id);
	}
	
}
