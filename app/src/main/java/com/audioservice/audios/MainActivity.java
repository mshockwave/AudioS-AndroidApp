package com.audioservice.audios;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*--------------------------------------------*/

        final DashBoardAdapter adapter = new DashBoardAdapter();
        if(intent.getBooleanExtra(Public.Constants.EXTRA_BOOL_NOT_LOGIN, false)){
            adapter.addItem(R.string.nav_recorder,
                        R.drawable.ic_mic_black_48dp, null);
            adapter.addItem(R.string.nav_public_map,
                        R.drawable.ic_map_black_48dp, null);
            adapter.addItem(R.string.nav_viewer,
                        R.drawable.ic_list_black_48dp,
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MainActivity.this, ViewerActivity.class);
                                intent.putExtra(Public.Constants.EXTRA_BOOL_SHOW_LOCAL_ONLY, true);
                                startActivity(intent);
                            }
                        });
            setDrawerState(drawer, toggle, false);
        }else{
            adapter.addItem(R.string.nav_recorder,
                    R.drawable.ic_mic_black_48dp, null);
            adapter.addItem(R.string.nav_public_map,
                    R.drawable.ic_map_black_48dp, null);
            adapter.addItem(R.string.nav_upload,
                    R.drawable.ic_file_upload_black_48dp, null);
            adapter.addItem(R.string.nav_viewer,
                    R.drawable.ic_list_black_48dp,
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(MainActivity.this, ViewerActivity.class));
                        }
                    });
            setDrawerState(drawer, toggle, true);
        }

        RecyclerView dashGrid = (RecyclerView)findViewById(R.id.main_dashboard_grid);
        dashGrid.setLayoutManager(new GridLayoutManager(this, 2));
        dashGrid.setAdapter(adapter);
    }

    private void setDrawerState(DrawerLayout drawer, ActionBarDrawerToggle toggle, boolean enable){
        drawer.setDrawerLockMode((enable)?DrawerLayout.LOCK_MODE_UNLOCKED : DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        toggle.setDrawerIndicatorEnabled(enable);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private final class DashBoardEntry {
        public int TextRes;
        public int IconRes;
        public View.OnClickListener OnClickCallback;
        public DashBoardEntry(int t, int i, View.OnClickListener c){
            TextRes = t;
            IconRes = i;
            OnClickCallback = c;
        }
    }
    private final class DashBoardVH extends RecyclerView.ViewHolder {

        public TextView TextTitle;
        public ImageView ImageIcon;
        public View Container;

        public DashBoardVH(View container) {
            super(container);
            Container = container;
            TextTitle = (TextView)container.findViewById(R.id.text_description);
            ImageIcon = (ImageView)container.findViewById(R.id.image_icon);
        }
    }
    private final class DashBoardAdapter extends RecyclerView.Adapter<DashBoardVH> {

        private final List<DashBoardEntry> mItemList = new ArrayList<>();

        @Override
        public DashBoardVH onCreateViewHolder(ViewGroup parent, int viewType) {
            View rootView = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.view_main_nav_cell, parent, false);
            return new DashBoardVH(rootView);
        }

        public void addItem(int textRes, int imgRes, View.OnClickListener clickCallback){
            mItemList.add(new DashBoardEntry(textRes, imgRes, clickCallback));
        }

        @Override
        public void onBindViewHolder(DashBoardVH holder, int position) {
            DashBoardEntry item = mItemList.get(position);
            holder.TextTitle.setText(item.TextRes);
            holder.ImageIcon.setImageResource(item.IconRes);
            if(item.OnClickCallback != null) holder.Container.setOnClickListener(item.OnClickCallback);
        }

        @Override
        public int getItemCount() { return mItemList.size(); }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(getIntent().getBooleanExtra(Public.Constants.EXTRA_BOOL_NOT_LOGIN, false)){
            getMenuInflater().inflate(R.menu.main_not_login, menu);
        }else{
            getMenuInflater().inflate(R.menu.main, menu);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.action_settings:{
                break;
            }

            case R.id.action_login: {
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
