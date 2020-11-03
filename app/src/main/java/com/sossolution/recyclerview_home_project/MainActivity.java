package com.sossolution.recyclerview_home_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<String>list_item;
    LinearLayoutManager manager;
    My_adapter adapter;
    boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerview_item);
        recyclerView.setHasFixedSize(true);
        manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        list_item= new ArrayList<String>();
        populateData();
        initAdapter();
        initScrollListener();


       /* List<String>list_item=new ArrayList<>();
       *//* list_item.add("Apple1");
        list_item.add("Apple2");
        list_item.add("Apple3");
        list_item.add("Apple4");
        list_item.add("Apple5");
        list_item.add("Apple7");*//*

      *//*  for (int i = 0; i <=50; i++)
        {
            list_item.add("Item " + (i + 1));
        }*//*

        *//*adapter= new My_adapter(list_item);
        recyclerView.setAdapter(adapter);*//*
        initScrollListener();*/


    }

    private void initAdapter()
    {

        adapter = new My_adapter(list_item);
        recyclerView.setAdapter(adapter);
    }

    private void populateData()
    {

        for(int i=0;i<=10;i++)
        {
            list_item.add("My_item"+i);
        }

    }
    private void initScrollListener()
    {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager manager= (LinearLayoutManager) recyclerView.getLayoutManager();

                if(list_item!=null)
                {
                    if(!isLoading)
                    {
                        if(manager!=null && manager.findLastCompletelyVisibleItemPosition()==list_item.size()-1)
                        {
                            loadmore();
                            isLoading=true;

                        }
                    }
                }else
                {
                    Toast.makeText(MainActivity.this, "list null", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }

    private void loadmore()
    {

        list_item.add(null);
        adapter.notifyItemInserted(list_item.size()-1);

        Handler handler= new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {

                list_item.remove(list_item.size()-1);
                int scroll_position= list_item.size();
                adapter.notifyItemRemoved(scroll_position);
                int current_size=scroll_position;
                int next_limit=current_size+10;

                while (current_size-1<next_limit)
                {
                    list_item.add("apple"+current_size);
                    current_size++;
                }
                adapter.notifyDataSetChanged();
                isLoading=false;


            }
        },2000);

    }
}