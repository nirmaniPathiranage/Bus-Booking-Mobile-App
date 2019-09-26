package com.example.nano_science.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchBusActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    RecyclerView recyclerView;
    BusAdapter adapter;
    List<Bus> busList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bus);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        //recycle view

        busList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewBus);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //adding some items to our list

        busList.add(
                new Bus(
                        1,
                        "Matara",
                        "Kataragama    Godagama    Colombo",
                        "2019-09-29    2019-09-29    2019-09-29",
                        "at 14:00 pm    at 17:10 pm    at 19:46 pm",
                        "Facilities",
                        "Route Number: EX 1-12/32",
                        "Travel Class: XL",
                        "Expressway: Kataragama - Colombo",
                        "Super Luxury",
                        "Duration: 2 hours 36 minutes",
                        "Rs. 1,185.45/Person",
                        R.drawable.bus1,
                        R.drawable.busfacilities1));

        busList.add(
                new Bus(
                        1,
                        "Matara",
                        "Kataragama    Godagama    Colombo",
                        "2019-09-29    2019-09-29    2019-09-29",
                        "at 11:15 am    at 14:25 pm    at 17:01 pm",
                        "Facilities",
                        "Route Number: EX 1-12/32",
                        "Travel Class: XL",
                        "Expressway: Kataragama - Colombo",
                        "Super Luxury",
                        "Duration: 2 hours 36 minutes",
                        "Rs. 1,185.45/Person",
                        R.drawable.bus2,
                        R.drawable.busfacilities1));

        busList.add(
                new Bus(
                        1,
                        "Matara",
                        "Embilipitiya    Kataragama    Colombo",
                        "2019-09-29    2019-09-29    2019-09-29",
                        "at 03:45 am    at 05:35 am    at 07:20 am",
                        "Facilities",
                        "Route Number: EX 1-9/493",
                        "Travel Class: XL",
                        "Expressway: Embilipitiya - Colombo",
                        "Super Luxury",
                        "Duration: 1 hours 45 minutes",
                        "Rs. 1,054.20/Person",
                        R.drawable.bus3,
                        R.drawable.busfacilities1));

        busList.add(
                new Bus(
                        1,
                        "Matara",
                        "Kataragama    Godagama    Colombo",
                        "2019-09-29    2019-09-29    2019-09-29",
                        "at 14:00 pm    at 17:10 pm    at 19:46 pm",
                        "Facilities",
                        "Route Number: EX 1-12/32",
                        "Travel Class: XL",
                        "Expressway: Kataragama - Colombo",
                        "Super Luxury",
                        "Duration: 2 hours 36 minutes",
                        "Rs. 1,185.45/Person",
                        R.drawable.bus4,
                        R.drawable.busfacilities1));


        adapter = new BusAdapter(this, busList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (actionBarDrawerToggle.onOptionsItemSelected(menuItem)) {
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        final Context context = this;

        if (id == R.id.about) {
            Intent intent = new Intent(context, AboutUsIntActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Let's view About Us Page", Toast.LENGTH_SHORT).show();
        }

        else if (id == R.id.home) {
            Intent intent1 = new Intent(context, MainActivity.class);
            startActivity(intent1);
            Toast.makeText(this, "Let's view Home Page", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}