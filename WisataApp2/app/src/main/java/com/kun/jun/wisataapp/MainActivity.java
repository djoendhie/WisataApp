package com.kun.jun.wisataapp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.kun.jun.wisataapp.avtivity.DatabaseFirebaseActivity;
import com.kun.jun.wisataapp.gabung.RFire.MainMainActivity;
import com.kun.jun.wisataapp.gabung.fragment.HomeFragment;
import com.kun.jun.wisataapp.gabung.loginloading.LoginActivity;
import com.kun.jun.wisataapp.gabung.ntah.SessionManager;
import com.kun.jun.wisataapp.gabung.rcview.IsiActivity;

public class MainActivity extends SessionManager
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginActivity.preference preferences = new LoginActivity.preference(MainActivity.this);
        String nama = preferences.getNama();

        if (TextUtils.isEmpty(nama) || nama == null){
           startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }else {
            Toast.makeText(this, "Welcome" + " " + nama, Toast.LENGTH_SHORT).show();
        }
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

        Fragment fragment = new HomeFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.batas, fragment).commit();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Exit");
            builder.setMessage("yakin?").setCancelable(false).setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.this.finish();
                }
            })
                    .setNegativeButton("no,", null).show();
        }

    }



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
            Intent seting = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(seting);
            return true;
        }else  if (id == R.id.action_Logout) {
            //TODO Logout
            LoginActivity.preference preference = new LoginActivity.preference(MainActivity.this);
            preference.logout();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        FragmentManager manager = getSupportFragmentManager();

        if (id == R.id.nav_camera) {
            fragment = new HomeFragment();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.batas,fragment);
            transaction.commit();
        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(this, MainMainActivity.class));

        } else if (id == R.id.nav_slideshow) {
            startActivity(new Intent(this, DatabaseFirebaseActivity.class));

        } else if (id == R.id.nav_send) {
            startActivity(new Intent(this,AboutActivity.class));

        }

//        transaction.add(R.id.container, fragment). commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
