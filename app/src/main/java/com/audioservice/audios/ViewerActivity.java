package com.audioservice.audios;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class ViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager)findViewById(R.id.pager_fragment);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayout);

        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }


    private final class TabAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mTitleList = new ArrayList<>();

        public TabAdapter(FragmentManager fm) {
            super(fm);

            //Init
            mFragmentList.add(ResourcesFragment.newInstance("",""));
            mTitleList.add(getString(R.string.viewer_resources));

            mFragmentList.add(LocalResourcesFragment.newInstance("",""));
            mTitleList.add(getString(R.string.viewer_local_resources));

            mFragmentList.add(LocalAudioFragment.newInstance("",""));
            mTitleList.add(getString(R.string.viewer_local_audio));
        }

        @Override
        public Fragment getItem(int position) { return mFragmentList.get(position); }

        @Override
        public int getCount() { return mTitleList.size(); }
        @Override
        public CharSequence getPageTitle(int position) { return mTitleList.get(position); }
    }
}
