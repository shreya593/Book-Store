package com.example.buyandselling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.zip.Inflater;

import static com.example.buyandselling.R.menu.top_menu;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
   private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
       if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home()).commit();
           navigationView.setCheckedItem(R.id.home);
        }


     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(top_menu,menu);
        return true;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new profile()).commit();
                break;
            case R.id.sell:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new sell()).commit();
                break;
            case R.id.about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new about()).commit();
                break;
            case R.id.Homepage:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Home()).commit();
                break;



        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
     public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
     }
        else{
            super.onBackPressed();
        }
    }
}
