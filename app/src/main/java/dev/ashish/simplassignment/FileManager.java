package dev.ashish.simplassignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.robertlevonyan.views.customfloatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.Random;

import jp.wasabeef.recyclerview.animators.FadeInAnimator;

public class FileManager extends AppCompatActivity {

    RecyclerView mRecyclerView;
    FileManagerCustomAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    boolean firstTimeClicked = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_manager);
        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        final File[] temp = (File[]) intent.getSerializableExtra("list");

        Random r = new Random();
        for (int i = temp.length - 1; i > 0; i--) {
            int j = r.nextInt(i);
            File t = temp[i];
            temp[i] = temp[j];
            temp[j] = t;
        }

        mRecyclerView = findViewById(R.id.recycler1);
        mLayoutManager = new LinearLayoutManager(FileManager.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        set(temp);
        mRecyclerView.getAdapter().notifyDataSetChanged();
        final FloatingActionButton fab = findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {

            File[] list = temp;
            File[] origList = list.clone();

            @Override
            public void onClick(View view) {

                if (firstTimeClicked) {
                    fab.setFabText("Reset");
                    firstTimeClicked = false;
                    new QuickSort().quickSort(list, mRecyclerView);

                } else {
                    fab.setFabText("Sort by filename");
                    firstTimeClicked = true;
                    list = origList;
                    set(origList);
                }

            }
        });


        //////////////////////////////////////////////////////
        //////////////////////////////////////////////////////
        //////////////////////////////////////////////////////

        final android.support.design.widget.FloatingActionButton back = findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    void set(Comparable[] x) {

        mAdapter = new FileManagerCustomAdapter(x, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);

        long l = Long.parseLong("700");
        FadeInAnimator animator = new FadeInAnimator();
        animator.setMoveDuration(l);
        mRecyclerView.setItemAnimator(animator);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}

