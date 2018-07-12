package com.myapp.todolist.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.myapp.todolist.R;
import com.myapp.todolist.adapter.CustomAdapter;
import com.myapp.todolist.network.IService;
import com.myapp.todolist.network.RetrofitInstance;
import com.myapp.todolist.network.ToDoItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "ListActivity";
    RecyclerView rvItems;
    CustomAdapter adapter;
    List<ToDoItem> itemList;
    public static View.OnClickListener myOnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        rvItems = findViewById(R.id.rv_to_do_list);
        rvItems.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvItems.setLayoutManager(layoutManager);

        IService service = RetrofitInstance.getRetrofitInstance().create(IService.class);
        service.getToDoItems().enqueue(new Callback<List<ToDoItem>>() {
            @Override
            public void onResponse(Call<List<ToDoItem>> call, Response<List<ToDoItem>> response) {
                itemList = response.body();
                adapter = new CustomAdapter((ArrayList<ToDoItem>) itemList);
                rvItems.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ToDoItem>> call, Throwable t) {
                Toast.makeText(ListActivity.this, "Network Failure! See log", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        myOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = rvItems.getChildLayoutPosition(v);
                String output = String.format("ID: %s\nUserID: %s\nTitle: %s\nCompleted: %s",
                        itemList.get(position).id,
                        itemList.get(position).userId,
                        itemList.get(position).title,
                        itemList.get(position).completed);
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(ListActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(ListActivity.this);
                }
                builder.setTitle("To Do Item Info")
                        .setMessage(output)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        };

    }

    @Override
    public void onClick(View v) {

    }
}
