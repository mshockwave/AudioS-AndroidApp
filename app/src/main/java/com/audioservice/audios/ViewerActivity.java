package com.audioservice.audios;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);

        Intent intent = getIntent();

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager)findViewById(R.id.pager_fragment);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayout);

        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        if(intent.getBooleanExtra(Public.Constants.EXTRA_BOOL_SHOW_LOCAL_ONLY, false)){
            //Show only local tabs
            adapter.addFragment(getString(R.string.viewer_audio),
                        AudioFragment.newInstance("",""));
        }else{
            adapter.addFragment(getString(R.string.viewer_resources),
                        ResourcesFragment.newInstance("",""));
            adapter.addFragment(getString(R.string.viewer_local_resources),
                        LocalResourcesFragment.newInstance("",""));
            adapter.addFragment(getString(R.string.viewer_audio),
                        AudioFragment.newInstance("",""));
        }

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        String msg;
        if((msg = intent.getStringExtra(Public.Constants.EXTRA_STRING_SHOW_TOAST_MESSAGE)) != null){
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }
    }

    private final class TabAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mTitleList = new ArrayList<>();

        public TabAdapter(FragmentManager fm) { super(fm); }

        public void addFragment(String name, Fragment fragment){
            mFragmentList.add(fragment);
            mTitleList.add(name);
        }

        @Override
        public Fragment getItem(int position) { return mFragmentList.get(position); }

        @Override
        public int getCount() { return mTitleList.size(); }
        @Override
        public CharSequence getPageTitle(int position) { return mTitleList.get(position); }
    }
}
