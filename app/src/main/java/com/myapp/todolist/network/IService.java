package com.myapp.todolist.network;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IService {
    @GET("todos")
    Call<List<ToDoItem>> getToDoItems();
}
