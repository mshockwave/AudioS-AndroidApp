package com.audioservice.audios;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class LoginRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.login_or_sign_up);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager)findViewById(R.id.pager_fragment);
        viewPager.setAdapter(mTabsAdapter);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private final FragmentPagerAdapter mTabsAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:{
                    return LoginFragment.newInstance();
                }

                case 1:{
                    return RegisterFragment.newInstance();
                }
                default:
                    return null;
            }
        }

        @Override
        public String getPageTitle(int position){
            switch(position){
                case 0:
                    return getString(R.string.login);
                case 1:
                    return getString(R.string.register);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() { return 2; }
    };
}
