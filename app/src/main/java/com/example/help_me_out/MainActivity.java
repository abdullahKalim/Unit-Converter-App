package com.example.help_me_out;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public SQLiteDatabase mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container, CalculatorFragment.class, null)
                    .commit();
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
         int id=item.getItemId();
            if(id== R.id.calculator)
            {
                FragmentManager fragmentManager=getSupportFragmentManager();
                        fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).
                        replace(R.id.fragment_container,CalculatorFragment.class,null).addToBackStack(null).setReorderingAllowed(true).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);

            }

            else if (id==R.id.num_sys||id==R.id.temp||id==R.id.weigth||id==R.id.angle||id==R.id.length||id==R.id.volume||id==R.id.energy||id==R.id.pressure||id==R.id.power)
            {

                ConverstionFragment cof=new ConverstionFragment();
                Bundle b=new Bundle();
                b.putString("Id", String.valueOf(item.getTitle()));
                cof.setArguments(b);
                FragmentManager fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).
                        replace(R.id.fragment_container,cof,null).setReorderingAllowed(true).commit();
                drawerLayout.closeDrawer(GravityCompat.START);

            }
            else if(id==R.id.postPre) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right).
                        replace(R.id.fragment_container,PostPreFragment.class,null).setReorderingAllowed(true).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
            }


            else if(id==R.id.currency||id==R.id.crypto)
            {
                FragmentManager fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).
                        replace(R.id.fragment_container,CurrencyFragment.class,null).setReorderingAllowed(true).commit();
                //CurrencyFragment cuf=new CurrencyFragment();
                //Bundle b=new Bundle();
                //b.putInt("Id",id);
                //cuf.setArguments(b);
                drawerLayout.closeDrawer(GravityCompat.START);
            }

            else if(id==R.id.dictionary)
            {
                FragmentManager fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).
                        replace(R.id.fragment_container,DictionaryFragment.class,null).setReorderingAllowed(true).commit();

                drawerLayout.closeDrawer(GravityCompat.START);

            }

            else if(id==R.id.settings)
            {
                FragmentManager fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).
                        replace(R.id.fragment_container,SettingsFragment.class,null).setReorderingAllowed(true).commit();

                drawerLayout.closeDrawer(GravityCompat.START);
            }

            else if(id==R.id.about)
            {
                FragmentManager fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).
                        replace(R.id.fragment_container,AboutFragment.class,null).setReorderingAllowed(true).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
            }
            else if(id==R.id.report)
            {
                Toast.makeText(this, "Report a Problem", Toast.LENGTH_SHORT).show();
            }

        return true;
    }



}