package com.itsdl.androidtutorials.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.fragments.ChangePasswordFragment;
import com.itsdl.androidtutorials.fragments.FunctionsFragment;
import com.itsdl.androidtutorials.fragments.HelpFragment;
import com.itsdl.androidtutorials.fragments.LoginFragment;
import com.itsdl.androidtutorials.fragments.UserProfileFragment;
import com.itsdl.androidtutorials.fragments.VersionInfoFragment;
import com.itsdl.androidtutorials.utils.Global;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = ( Toolbar ) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = ( DrawerLayout ) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);


        toggle.syncState();

        NavigationView navigationView = ( NavigationView ) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Test adapter
        Fragment fragment = new LoginFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frContainer,fragment, Global.LOGIN)
                .addToBackStack(Global.LOGIN)
                .commit();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = ( DrawerLayout ) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

        if(getSupportFragmentManager().getBackStackEntryCount()<1) {
            Toast.makeText(getApplicationContext(), "" + getSupportFragmentManager().getBackStackEntryCount(),
                    Toast.LENGTH_LONG).show();
            //getSupportFragmentManager().popBackStack();
            System.exit(0);
        }
        if(getSupportFragmentManager().findFragmentById(R.id.frContainer) != null){
            if (getSupportFragmentManager().findFragmentById(R.id.frContainer).getTag().equals("LOGIN")){
                System.exit(0);
            }
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            navProfile();
        } else if (id == R.id.nav_password) {
            navPassword();

        } else if (id == R.id.nav_help) {
            navHelp();

        } else if (id == R.id.nav_info) {
            navInfo();

        }

        DrawerLayout drawer = ( DrawerLayout ) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void navInfo() {
        VersionInfoFragment fragment = new VersionInfoFragment();
        replaceFragment(fragment,"VERSION");
    }

    private void navHelp() {
        HelpFragment fragment = new HelpFragment();
        replaceFragment(fragment,"HELP");
    }

    private void navPassword() {
        ChangePasswordFragment fragment = new ChangePasswordFragment();
        replaceFragment(fragment,"CHANGE_PASS");
    }

    private void navProfile() {
        UserProfileFragment fConv = new UserProfileFragment();
        replaceFragment(fConv,"Profile");
    }

    private void replaceFragment(Fragment fConv,String tag) {
        if(getSupportFragmentManager()!=null){
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.frContainer, fConv,tag);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
