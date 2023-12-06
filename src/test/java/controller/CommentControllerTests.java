package test.java.controller;

import main.java.controllers.CommentController;
import main.java.models.Comment;

public class CommentControllerTests {
	public static void main(String[] args) {
		CommentController test = new CommentController();
		test.addComment("Text1|Date|TaskID|UserID");
		test.addComment("Text2|Date|TaskID|UserID");
		test.addComment("Text3|Date|TaskID|UserID");
		
		for (Comment cmt : test.getListOfComment()) {
			System.out.println(cmt.toString(cmt));
		}
		
		test.editComment("cmt-9", "Text-0|Date-0|TaskID-0|UserID-0");
		System.out.println("=========EDIT===========");
		for (Comment cmt : test.getListOfComment()) {
			System.out.println(cmt.toString(cmt));
		}
		
		test.deleteComment("cmt-9");
		System.out.println("=========DELETE===========");
		for (Comment cmt : test.getListOfComment()) {
			System.out.println(cmt.toString(cmt));
		}
	}
}
