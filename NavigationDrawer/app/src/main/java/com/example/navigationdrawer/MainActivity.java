package com.example.navigationdrawer;

import android.graphics.Point;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        FragmentOne.OnFragmentInteractionListener,
        FragmentTwo.OnFragmentInteractionListener{

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.action_nav);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            Fragment fragment = null;
            Class fragmentClass = null;
            fragmentClass = FragmentOne.class;
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        navigationView = (NavigationView) findViewById(R.id.navigationView);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels;
        Log.e("width",String.valueOf(dpWidth));

        int dpWidthInt= 0;
        if (dpWidth >= 1000){
            dpWidthInt = Integer.parseInt(String.valueOf(dpWidth).substring(0,4));
        } else {
            dpWidthInt = Integer.parseInt(String.valueOf(dpWidth).substring(0,3));
        }

        Log.e("dpwidth",String.valueOf(dpWidthInt));

        int width = (int) (dpWidthInt - (56 * displayMetrics.density));
        Log.e("width",String.valueOf(width));
        DrawerLayout.LayoutParams navP = (DrawerLayout.LayoutParams) navigationView.getLayoutParams();
        navP.width = width;
        navigationView.setLayoutParams(navP);
        Log.e("getwidth",String.valueOf(navigationView.getWidth()));
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * Metodo cuando se pulsa atras en el manu de abajo de android
     */
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Metodo de las opciones de la actionbar
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d("Navigation","Se ha pulsado icono home");
                if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.openDrawer(GravityCompat.START);
                    Log.e("getwidth",String.valueOf(navigationView.getWidth()));
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Metodo para mostrar un toast corto desde cualquier parte del fichero
     * @param message
     */
    public void toastShort(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    /**
     * Metodo que en base a opcion selectionada se realiza una accion
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        Class fragmentClass = null;
        switch (item.getItemId()) {
            case R.id.action_home:
                fragmentClass = FragmentOne.class;
                break;
            case R.id.action_dependency:
                fragmentClass = FragmentTwo.class;
                break;
            case R.id.action_sector:
                fragmentClass = FragmentOne.class;
                break;
            case R.id.action_help:
                fragmentClass = FragmentTwo.class;
                break;
            case R.id.action_settings:
                fragmentClass = FragmentOne.class;
                break;
            case R.id.action_aboutus:
                fragmentClass = FragmentTwo.class;
                break;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        toastShort(item.getTitle().toString());
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
