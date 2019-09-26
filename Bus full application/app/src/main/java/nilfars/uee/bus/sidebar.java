package nilfars.uee.bus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;


public class sidebar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private SlidrInterface slidr;


    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar=null;

    ImageView feedbackview;
    ImageView bookseatview;
    ImageView journeysview;
    ImageView busview;
    ImageView suggestionsview;
    ImageView productlistview;
    ImageView achievementview;
    ImageView settingsview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sidebar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        slidr = Slidr.attach(this);



        bookseatview = findViewById(R.id.bookseat);
        bookseatview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(sidebar.this, "Booking Soon", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(sidebar.this, JourneySearchForm.class));

            }
        });

        feedbackview = findViewById(R.id.feedback);
        feedbackview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(sidebar.this, FeedRecycleView.class));

            }
        });



        journeysview = findViewById(R.id.journeys);
        journeysview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(sidebar.this, New_Journeys.class));

            }
        });

        busview = findViewById(R.id.bustype);
        busview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(sidebar.this, "Bus types Soon", Toast.LENGTH_SHORT).show();
            }
        });

        suggestionsview = findViewById(R.id.suggestions);
        suggestionsview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(sidebar.this, "Suggestions Soon", Toast.LENGTH_SHORT).show();
            }
        });

        productlistview = findViewById(R.id.productlist);
        productlistview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(sidebar.this, "Our Product list Soon", Toast.LENGTH_SHORT).show();
            }
        });

        achievementview = findViewById(R.id.achievement);
        achievementview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(sidebar.this, "Achievements Soon", Toast.LENGTH_SHORT).show();
            }
        });

        settingsview = findViewById(R.id.settings);
        settingsview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(sidebar.this, "Settings Soon", Toast.LENGTH_SHORT).show();
            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final Context context = this;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChatActivity.class);
                startActivity(intent);
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sidebar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        //here is the main place where we need to work on.
        int id=item.getItemId();
        switch (id){

            case R.id.home:
                Intent h= new Intent(sidebar.this,sidebar.class);
                startActivity(h);
                break;
            case R.id.story:
                Intent i= new Intent(sidebar.this,stories.class);
                startActivity(i);
                break;
            case R.id.exp:
                Intent g= new Intent(sidebar.this,experience.class);
                startActivity(g);
                break;
            case R.id.about:
                Intent s= new Intent(sidebar.this,about.class);
                startActivity(s);
                break;

            case R.id.contact:
                Intent t= new Intent(sidebar.this,contact.class);
                startActivity(t);
                break;

            case R.id.feed:
                Intent f= new Intent(sidebar.this,feed.class);
                startActivity(f);
                break;

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
