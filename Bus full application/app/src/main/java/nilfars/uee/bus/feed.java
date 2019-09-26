package nilfars.uee.bus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class feed extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private SlidrInterface slidr;


    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar = null;

    EditText name;
    EditText mail;
    EditText comment, sqlRow;
    Button submit, submitnew;
    Button feed;

    Button sqlModify, sqlDelete, sqlgetInfo,reset;

    MyDatabase mydb;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        databaseReference = FirebaseDatabase.getInstance().getReference("feedsnew");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = (EditText) findViewById(R.id.editname);
        mail = (EditText) findViewById(R.id.editmail);
        comment = (EditText) findViewById(R.id.editcomment);

        submit = (Button) findViewById(R.id.btnsubmit);
        feed = (Button) findViewById(R.id.btnfeed);
        submitnew = (Button) findViewById(R.id.submit2);
        reset = (Button) findViewById(R.id.resetnif);

        sqlRow = (EditText) findViewById(R.id.searchId);
        sqlModify = (Button) findViewById(R.id.updatebtn);
        sqlDelete = (Button) findViewById(R.id.deletebtn);
        sqlgetInfo = (Button) findViewById(R.id.getinfo);


        sqlModify.setOnClickListener(this);
        sqlDelete.setOnClickListener(this);
        sqlgetInfo.setOnClickListener(this);


        feed.setOnClickListener(this);
        submit.setOnClickListener(this);


        mydb = new MyDatabase(feed.this);
        submitnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name.getText().toString().trim();
                String mail1 = mail.getText().toString().trim();
                String comment1 = comment.getText().toString().trim();

                if (!TextUtils.isEmpty(name1) && !TextUtils.isEmpty(mail1) && !TextUtils.isEmpty(comment1)) {

                    mydb.insertData(name.getText().toString(), mail.getText().toString(), comment.getText().toString());
//                        Toast.makeText(this,"Feedback added !",Toast.LENGTH_LONG).show();


                    Intent i = new Intent(feed.this, FeedRecycleView.class);
                    startActivity(i);
                } else {
//                        Toast.makeText(this,"Fields Can not be Empty!",Toast.LENGTH_LONG).show();


                }
            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText("");
                mail.setText("");
                comment.setText("");
            }
        });



        slidr = Slidr.attach(this);

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


//    private void addFeed(){
//
//        String name1=name.getText().toString().trim();
//        String mail1=mail.getText().toString().trim();
//        String comment1=comment.getText().toString().trim();
//
//
//        if(!TextUtils.isEmpty(name1)&& !TextUtils.isEmpty(mail1)&& !TextUtils.isEmpty(comment1)){
//
//
//            String id=databaseReference .push().getKey();//this id is generated uniquely(randomly)
//            FeedModel feedmodel=new FeedModel(id,name1,mail1,comment1);
//            databaseReference.child(id).setValue(feedmodel);
//
////            databaseReference.child("message").child(id).setValue("Hello, World!");
//            Toast.makeText(this,"Feedback added !",Toast.LENGTH_LONG).show();
//
//
//        }else{
//
//            Toast.makeText(this,"Fields Can not be Empty!",Toast.LENGTH_LONG).show();
//        }
//
//
//
//    }


    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.btnsubmit:

                boolean didItWork = true;
                try {


                    String name1 = name.getText().toString().trim();
                    String mail1 = mail.getText().toString().trim();
                    String comment1 = comment.getText().toString().trim();

                    SQLiteDB entry = new SQLiteDB(feed.this);

                    if (!TextUtils.isEmpty(name1) && !TextUtils.isEmpty(mail1) && !TextUtils.isEmpty(comment1)) {
                        entry.open();
                        entry.createEntry(name1, mail1, comment1);
                        Toast.makeText(this, "Data insertion Successful !", Toast.LENGTH_LONG).show();
                        name.setText("");
                        mail.setText("");
                        comment.setText("");
                        entry.close();
                    } else {

                        Toast.makeText(this, "Fields Can not be Empty!", Toast.LENGTH_LONG).show();


                    }
                } catch (Exception e) {

                    didItWork = false;
                    String error = e.toString();
                    Toast.makeText(this, error, Toast.LENGTH_LONG).show();


                }


                break;
            case R.id.btnfeed:

                startActivity(new Intent(feed.this, viewHistory.class));
                break;
            case R.id.updatebtn:

                try {
                    String name2 = name.getText().toString().trim();
                    String mail2 = mail.getText().toString().trim();
                    String comment2 = comment.getText().toString().trim();
                    String sRow = sqlRow.getText().toString();
                    long lRow = Long.parseLong(sRow);

                    SQLiteDB cm = new SQLiteDB(this);
                    cm.open();
                    cm.updateEntry(lRow, name2, mail2, comment2);
                    Toast.makeText(this, " Record is Updated !", Toast.LENGTH_LONG).show();

                    cm.close();

                    startActivity(new Intent(feed.this, viewHistory.class));
                } catch (Exception e) {

                    didItWork = false;
                    String error = e.toString();
                    Toast.makeText(this, error, Toast.LENGTH_LONG).show();


                }
                break;
            case R.id.deletebtn:

                try {
                    String sRow1 = sqlRow.getText().toString();
                    long lRow1 = Long.parseLong(sRow1);
                    SQLiteDB cm1 = new SQLiteDB(this);
                    cm1.open();
                    cm1.deleteEntry(lRow1);
                    Toast.makeText(this, "Record is deleted !", Toast.LENGTH_LONG).show();

                    cm1.close();


                    startActivity(new Intent(feed.this, viewHistory.class));
                } catch (Exception e) {

                    didItWork = false;
                    String error = e.toString();
                    Toast.makeText(this, error, Toast.LENGTH_LONG).show();


                }
                break;
            case R.id.getinfo:

                try{String s = sqlRow.getText().toString();
                long l = Long.parseLong(s);
                SQLiteDB comdb = new SQLiteDB(this);
                comdb.open();
                String returnName = comdb.getName(l);
                String returnEmail = comdb.getEmail(l);
                String returnComment = comdb.getComment(l);

                comdb.close();
                name.setText(returnName);
                mail.setText(returnEmail);
                comment.setText(returnComment);

        }
                catch (Exception e) {

                didItWork = false;
                String error = e.toString();
                Toast.makeText(this, error, Toast.LENGTH_LONG).show();


            }

                break;


        }


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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sidebar, menu);
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
        //here is the main place where we need to work on.
        int id = item.getItemId();
        switch (id) {

            case R.id.home:
                Intent h = new Intent(feed.this, sidebar.class);
                startActivity(h);
                break;
            case R.id.story:
                Intent i = new Intent(feed.this, stories.class);
                startActivity(i);
                break;
            case R.id.exp:
                Intent g = new Intent(feed.this, experience.class);
                startActivity(g);
                break;
            case R.id.about:
                Intent s = new Intent(feed.this, about.class);
                startActivity(s);
                break;

            case R.id.contact:
                Intent t = new Intent(feed.this, contact.class);
                startActivity(t);
                break;

            case R.id.feed:
                Intent f = new Intent(feed.this, feed.class);
                startActivity(f);
                break;

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
