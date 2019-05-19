package dev.ashish.simplassignment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.robertlevonyan.views.customfloatingactionbutton.FloatingActionButton;

import jp.wasabeef.recyclerview.animators.FadeInAnimator;

public class ScrollingActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    CustomAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    Comparable[] x = {"mallika_1.jpg", "dog005.jpg", "grandson_2018_01_01.png", "dog008.jpg", "mallika_6.jpg", "grandson_2018_5_23.png", "dog01.png", "mallika_11.jpg", "mallika2.jpg", "grandson_2018_02_5.png", "grandson_2019_08_23.jpg", "dog9.jpg", "mallika05.jpg"};
    boolean firstTimeClicked = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (firstTimeClicked) {
                    fab.setFabText("Reset");
                    firstTimeClicked = false;
                    new QuickSort().quickSort(x, mRecyclerView);
                } else {
                    x = new Comparable[]{"mallika_1.jpg", "dog005.jpg", "grandson_2018_01_01.png", "dog008.jpg", "mallika_6.jpg", "grandson_2018_5_23.png", "dog01.png", "mallika_11.jpg", "mallika2.jpg", "grandson_2018_02_5.png", "grandson_2019_08_23.jpg", "dog9.jpg", "mallika05.jpg"};
                    fab.setFabText("Sort by filename");
                    firstTimeClicked = true;
                    set();
                }
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });


        mRecyclerView = findViewById(R.id.recycler);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        set();
        mRecyclerView.getAdapter().notifyDataSetChanged();

    }


    void set() {

        // BEGIN_INCLUDE(initializeRecyclerView)
        mAdapter = new CustomAdapter(x);
        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);

        long l = Long.parseLong("1000");
        FadeInAnimator animator = new FadeInAnimator();
        animator.setMoveDuration(l);
        mRecyclerView.setItemAnimator(animator);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);


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
            Intent myIntent = new Intent(ScrollingActivity.this, FileManager.class);
            myIntent.putExtra("list", Environment.getRootDirectory().listFiles());
            ScrollingActivity.this.startActivity(myIntent);

        }
        return super.onOptionsItemSelected(item);
    }


}

