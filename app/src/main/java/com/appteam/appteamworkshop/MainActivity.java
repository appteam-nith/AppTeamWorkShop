package com.appteam.appteamworkshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PostAdapter adapter ;
    private ArrayList<Post> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swiperefreshlayout);

        progressBar.setVisibility(View.VISIBLE);
//        list.add(new Post("title", "message"));
//        list.add(new Post("title", "message"));
//        list.add(new Post("title", "message"));
//        list.add(new Post("title", "message"));
//        list.add(new Post("title", "message"));
//        list.add(new Post("title", "message"));
//        adapter = new PostAdapter(list);
//        recyclerView.setAdapter(adapter);
        getList();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                recyclerView.setAdapter(adapter);
                getList();
                swipeRefreshLayout.setRefreshing(false);

            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PostActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void getList(){
        AndroidNetworking.get("https://appteamworkshop.herokuapp.com/api/confessions")
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(PostList.class, new ParsedRequestListener<PostList>() {
                    @Override
                    public void onResponse(PostList response) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Log.d("hello", String.valueOf(response.getConfessions()));
                        if(response.isSuccess() && response.getConfessions().size()>0)
                        {
                            list = (ArrayList<Post>) response.getConfessions();
                            adapter = new PostAdapter(list);
                            recyclerView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(MainActivity.this, "Check Connection", Toast.LENGTH_SHORT).show();
                    }
                });



    }
}
