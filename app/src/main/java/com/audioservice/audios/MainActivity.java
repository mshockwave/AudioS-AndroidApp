package com.audioservice.audios;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        RecyclerView dashGrid = (RecyclerView)findViewById(R.id.main_dashboard_grid);
        dashGrid.setLayoutManager(new GridLayoutManager(this, 2));
        dashGrid.setAdapter(mDashBoardAdapter);
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
    private final RecyclerView.Adapter<DashBoardVH> mDashBoardAdapter = new RecyclerView.Adapter<DashBoardVH>() {
        @Override
        public DashBoardVH onCreateViewHolder(ViewGroup parent, int viewType) {
            View rootView = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.view_main_nav_cell, parent, false);
            return new DashBoardVH(rootView);
        }

        @Override
        public void onBindViewHolder(DashBoardVH holder, int position) {
            switch (position){
                case 0:{
                    holder.TextTitle.setText(R.string.nav_recorder);
                    holder.ImageIcon.setImageResource(R.drawable.ic_mic_black_48dp);
                    //TODO: onClick callback
                    break;
                }

                case 1:{
                    holder.TextTitle.setText(R.string.nav_public_map);
                    holder.ImageIcon.setImageResource(R.drawable.ic_map_black_48dp);
                    break;
                }

                case 2:{
                    holder.TextTitle.setText(R.string.nav_upload);
                    holder.ImageIcon.setImageResource(R.drawable.ic_file_upload_black_48dp);
                    break;
                }

                case 3:{
                    holder.TextTitle.setText(R.string.nav_viewer);
                    holder.ImageIcon.setImageResource(R.drawable.ic_list_black_48dp);
                    break;
                }
            }
        }
        @Override
        public int getItemCount() { return 4; }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
