package com.example.navigationdrawer;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navview);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.navigationDrawerLayour);
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.action_home);
        setupNavigationView();
    }

    /**
     * Metodo que inicializa el Listener NavigationItemSelected, y en base a a opcion selectionada se realiza una accion
     */
    private void setupNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Log.d("nav drawer","opcion home");
                        break;
                    case R.id.action_dependency:
                        Log.d("nav drawer","opcion dependency");
                        break;
                    case R.id.action_sector:
                        Log.d("nav drawer","opcion sector");
                        break;
                    case R.id.action_subheader:
                        Log.d("nav drawer","opcion subheader");
                        break;
                }
                item.setChecked(true);
                getSupportActionBar().setTitle(item.getTitle());
                drawerLayout.closeDrawer(R.id.navigationDrawerLayour);
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d("Navigation","Se ha pulsado icono home");

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
