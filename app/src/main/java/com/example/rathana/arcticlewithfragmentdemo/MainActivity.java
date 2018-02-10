package com.example.rathana.arcticlewithfragmentdemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.rathana.arcticlewithfragmentdemo.fragment.DetailFragment;
import com.example.rathana.arcticlewithfragmentdemo.fragment.ListFragment;

public class MainActivity extends AppCompatActivity implements ListFragment.ListFragmentListener{

    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupNavigationDrawer();
        addFragment();
    }

    private void setupNavigationDrawer() {
        mDrawer=findViewById(R.id.drawer_layout);
        mNavigationView=findViewById(R.id.navView);
        toggle=new ActionBarDrawerToggle(this,mDrawer,toolbar,
                R.string.open_drawer,R.string.close_drawer){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawer.addDrawerListener(toggle);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        //getActionBar().setHomeButtonEnabled(true);
        //toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;
                String title="";
                switch (item.getItemId()){
                    case R.id.home_id:
                        fragment= ListFragment.getInstance("Home");
                        title="Home";
                        break;
                    case R.id.profile_id:
                        fragment=DetailFragment.getInstance("Detail");
                        title="Profile";
                        break;
                    case R.id.winner_list_id:
                        break;
                }
                FragmentTransaction t=getFragmentManager().beginTransaction();
                t.replace(R.id.content_frag,fragment);
                t.addToBackStack(null);
                t.commit();
                toolbar.setTitle(title);
                mDrawer.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    private void addFragment() {
        FragmentManager manager=getFragmentManager();
        FragmentTransaction t=manager.beginTransaction();
        //Fragment fragment=new ListFragment();
        ListFragment fragment=ListFragment.getInstance("listFragment");
        fragment.setInfo("pass data to fragment");
        t.add(R.id.content_frag,fragment);
        t.commit();
    }

    @Override
    public void onClickListener(String s) {
        //Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(String s) {
        Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
        FragmentTransaction t=getFragmentManager().beginTransaction();
        DetailFragment frag=DetailFragment.getInstance("detailFragment");
        frag.setDetail(s);
        t.replace(R.id.content_frag,frag);
        t.addToBackStack(null);
        t.commit();
    }
}
