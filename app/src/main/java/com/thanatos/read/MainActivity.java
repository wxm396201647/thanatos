package com.thanatos.read;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.common.collect.Lists;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.thanatos.read.ui.MyAdapter;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * Created by wang.ming5 on 2018/5/2.
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    List<String> list = Lists.newArrayList();

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_camera:

                    break;
                case R.id.nav_gallery:

                    break;
                case R.id.nav_slideshow:

                    break;
                case R.id.nav_manage:

                    break;
                case R.id.nav_share:

                    break;
                case R.id.nav_send:

                    break;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        list.add(String.format(Locale.CHINA, "书架", 0));
        list.add(String.format(Locale.CHINA, "第%02d页", 1));
        list.add(String.format(Locale.CHINA, "第%02d页", 2));
        /*设置Adapter*/
        pager.setAdapter(new MyAdapter(getSupportFragmentManager(), list));
        tab.setTabMode(TabLayout.MODE_FIXED);
        /*Tab与ViewPager绑定*/
        tab.setupWithViewPager(pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
