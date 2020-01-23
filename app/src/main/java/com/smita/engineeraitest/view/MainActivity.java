package com.smita.engineeraitest.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.smita.engineeraitest.Models.Hits;
import com.smita.engineeraitest.R;
import com.smita.engineeraitest.view.adapters.TopicsAdapter;
import com.smita.engineeraitest.viewmodel.TopicViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  TopicsAdapter.ItemClick{

    private SwipeRefreshLayout swipeRefreshLayout;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private TextView counterTv;
    private TopicViewModel topicViewModel;
    private int pageNo=1;
    private TopicsAdapter topicsAdapter;
    private List<Hits> topicList;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initi();
        setSupportActionBar(toolbar);
        topicList=new ArrayList<>();



        topicViewModel= ViewModelProviders.of(MainActivity.this).get(TopicViewModel.class);
        progressBar.setVisibility(View.VISIBLE);
        topicViewModel.CallForTopicList(pageNo);
        setupRecyclerView();
        setUpLoadMore();

        updateData();


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNo=1;
                topicList.clear();
                topicsAdapter.resetCounter();
                updateCounterTv(0);
                topicViewModel.CallForTopicList(pageNo);
            }
        });



    }

    private void updateData() {

        topicViewModel.getTopicList().observe(this, new Observer<List<Hits>>() {
            @Override
            public void onChanged(List<Hits> hits) {
                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
                if(hits==null){
                    Toast.makeText(MainActivity.this,"Something went wrong, Please try again later",Toast.LENGTH_LONG).show();
                    return;
                }


                topicList.addAll(hits);
                topicsAdapter.notifyDataSetChanged();


            }
        });

    }

    private void setUpLoadMore() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(!recyclerView.canScrollVertically(1)){
                    progressBar.setVisibility(View.VISIBLE);
                    topicViewModel.CallForTopicList(pageNo++);
                }

            }
        });

    }

    private void setupRecyclerView() {
        topicsAdapter=new TopicsAdapter(MainActivity.this,topicList,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this);
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(recyclerView.getContext(),linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(topicsAdapter);
    }

    private void initi() {
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout);
        toolbar=findViewById(R.id.toolbar);
        counterTv=toolbar.findViewById(R.id.counterTv);
        recyclerView=findViewById(R.id.recyclerView);
        progressBar=findViewById(R.id.progressBar);
    }

    @Override
    public void ClickPost(int selectedCounter, Hits selectedPost) {

       updateCounterTv(selectedCounter);
    }

    private void updateCounterTv(int selectedCounter) {
        counterTv.setText(String.valueOf(selectedCounter));
    }
}
