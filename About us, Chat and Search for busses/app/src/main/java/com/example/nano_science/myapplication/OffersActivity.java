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

public class OffersActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    RecyclerView recyclerView;
    OfferAdapter adapter;
    List<Offer> offerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        //recycle view

        offerList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //adding some items to our list

        offerList.add(
                new Offer(
                        1,
                        "50% up to Rs 175 Off for tickets",
                        "pay using Paypal",
                        R.drawable.paypal));

        offerList.add(
                new Offer(
                        1,
                        "Get 10% cashback upto Rs 100 in the BusBooking wallet",
                        "Use Code:RBRUPAY",
                        R.drawable.rupay));

        offerList.add(
                new Offer(
                        1,
                        "Save up to 50% on GSRTC buses",
                        "RBRTC",
                        R.drawable.msrt));

        offerList.add(
                new Offer(
                        1,
                        "Save up to Rs 200 on bus tickets",
                        "Use code FIRST only on App",
                        R.drawable.festivejourney));

        offerList.add(
                new Offer(
                        1,
                        "Save up to Rs 200 on bus tickets",
                        "Use Code COOLSAVER",
                        R.drawable.coolsaver));

        offerList.add(
                new Offer(
                        1,
                        "Cashback up to Rs 300 for bus tickets",
                        "Pay using Amazon pay",
                        R.drawable.amazonpay));

        offerList.add(
                new Offer(
                        1,
                        "Flat Rs. 50 cashback for bus tickets",
                        "FREECHARGE pay offer",
                        R.drawable.freecharge));

        offerList.add(
                new Offer(
                        1,
                        "20% up to Rs 200 + 100 Cashback",
                        "Use Code NIYO",
                        R.drawable.niyo));

        offerList.add(
                new Offer(
                        1,
                        "Get Rs 1000",
                        "Earn Rs 100 for every Referral",
                        R.drawable.referandearn));

        offerList.add(
                new Offer(
                        1,
                        "Save up to 50% on MSRTC buses",
                        "RBRTC",
                        R.drawable.msrt));

        offerList.add(
                new Offer(
                        1,
                        "Cashback up to Rs 200 on bus tickets",
                        "Use Code FESTIVE",
                        R.drawable.festivejourney));

        offerList.add(
                new Offer(
                        1,
                        "Save up to Rs 150 on bus tickets",
                        "Use Code CELEBRATE",
                        R.drawable.festivejourney));

        offerList.add(
                new Offer(
                        1,
                        "20% up to Rs 250 Super cash",
                        "Pay using MOBIKWIK",
                        R.drawable.mobikwik));

        adapter = new OfferAdapter(this, offerList);
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
