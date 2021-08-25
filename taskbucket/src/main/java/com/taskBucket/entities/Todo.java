package com.taskBucket.entities;

public class Todo {

private	String todo_id;
private	String title;
@Override
public String toString() {
	return "Todo [todo_id=" + todo_id + ", title=" + title + ", userId=" + userId + "]";
}
public Todo(String todo_id, String title, String userId) {
	super();
	this.todo_id = todo_id;
	this.title = title;
	this.userId = userId;
}
public Todo() {
	super();
	// TODO Auto-generated constructor stub
}
public String getTodo_id() {
	return todo_id;
}
public void setTodo_id(String todo_id) {
	this.todo_id = todo_id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
private	String userId;
	
	
}
