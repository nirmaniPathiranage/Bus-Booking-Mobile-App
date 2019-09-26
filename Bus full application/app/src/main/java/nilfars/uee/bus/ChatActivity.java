package nilfars.uee.bus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    public static final String DATABASE_NAME = "CHAT.db";

    SQLiteDatabase mDatabase;

    EditText editFullName, editEmailAddress, editPhoneNo, editMessage;
    Spinner spinnerMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

        createTable();

        editFullName = (EditText) findViewById(R.id.editText_fullname);
        editEmailAddress = (EditText) findViewById(R.id.editText_emailaddress);
        editPhoneNo = (EditText) findViewById(R.id.editText_phoneno);
        editMessage = (EditText) findViewById(R.id.editTextArea_message);
        spinnerMenu = (Spinner) findViewById(R.id.spinnerLanguagetype);

        findViewById(R.id.button_add).setOnClickListener(this);
        findViewById(R.id.button_reset).setOnClickListener(this);
        findViewById(R.id.textViewChats).setOnClickListener(this);
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
            Intent intent = new Intent(context, about.class);
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

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS chat( \n" +
                "   messageid INTEGER NOT NULL CONSTRAINT chat_pk PRIMARY KEY AUTOINCREMENT, \n" +
                "   fullname varchar(50) NOT NULL, \n" +
                "   email varchar(50) NOT NULL, \n" +
                "   phone varchar(10) NOT NULL, \n" +
                "   language varchar(10) NOT NULL, \n" +
                "   message varchar(100) NOT NULL\n" +
                ");";

        mDatabase.execSQL(sql);

    }

    private void addChat() {

        String fullname = editFullName.getText().toString().trim();
        String email = editEmailAddress.getText().toString().trim();
        String phone = editPhoneNo.getText().toString().trim();
        String language = spinnerMenu.getSelectedItem().toString();
        String message = editMessage.getText().toString().trim();

        //validate data

        if (fullname.isEmpty()) {
            editFullName.setError("Full Name Field can't be empty");
            editFullName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editEmailAddress.setError("Email Address Field can't be empty");
            editEmailAddress.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmailAddress.setError("Please enter a valid Email address");
            editEmailAddress.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            editPhoneNo.setError("Contact No. Field can't be empty");
            editPhoneNo.requestFocus();
            return;
        }

        if (message.isEmpty()) {
            editMessage.setError("Message Field can't be empty");
            editMessage.requestFocus();
            return;
        }

        String sql = "INSERT INTO chat(fullname, email, phone, language, message)" +
                "VALUES (?, ?, ?, ?, ?) ";

        mDatabase.execSQL(sql, new String[]{fullname, email, phone, language, message});

        Toast.makeText(this, "Message Sent Successfully! ", Toast.LENGTH_SHORT).show();

    }

    private void clearChat() {

        String fullname = editFullName.getText().toString();
        String email = editEmailAddress.getText().toString();
        String phone = editPhoneNo.getText().toString();
        String language = spinnerMenu.getSelectedItem().toString();
        String message = editMessage.getText().toString();


        if ((fullname.isEmpty()) && (email.isEmpty()) && (phone.isEmpty()) && (message.isEmpty()))
            Toast.makeText(getApplicationContext(), "Already Empty!!!", Toast.LENGTH_SHORT).show();

        else {
            editMessage.setText("");
            editPhoneNo.setText("");
            editEmailAddress.setText("");
            editFullName.setText("");
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_add:

                addChat();

                break;

            case R.id.button_reset:

                //startActivity(new Intent(this, MenuActivity.class));

                clearChat();

                break;

            case R.id.textViewChats:

                startActivity(new Intent(this, ChatViewActivity.class));

                break;
        }
    }
}
