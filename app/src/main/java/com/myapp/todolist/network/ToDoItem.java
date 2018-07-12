package com.myapp.todolist.network;

public class ToDoItem {
    public int userId;
    public int id;
    public String title;
    public boolean completed;

    public ToDoItem(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }
}
