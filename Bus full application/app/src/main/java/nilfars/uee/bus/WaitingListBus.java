package nilfars.uee.bus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WaitingListBus extends AppCompatActivity {

    cgDatabaseHelper mywaitingdb;

    TextView textView1,textView2,textView3,textView4,textView5;
    EditText editText,editText6,editText4,editText5,editText7;
    Button button1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_list_bus);

        mywaitingdb = new cgDatabaseHelper(this);

        editText = (EditText) findViewById(R.id.editText);
        editText6 = (EditText) findViewById(R.id.editText6);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);
        editText7 = (EditText) findViewById(R.id.editText7);
        button1 = (Button) findViewById(R.id.button1);



        AddData();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final Context context = this;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, sidebar.class);
                startActivity(intent);
            }
        });
//        init();
    }

    public void AddData(){
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().isEmpty() || editText6.getText().toString().isEmpty() || editText4.getText().toString().isEmpty() || editText5.getText().toString().isEmpty() || editText7.getText().toString().isEmpty()){
                    Toast.makeText(WaitingListBus.this,"Please fill all the text fields",Toast.LENGTH_LONG).show();

                }else{
                    boolean isInserted = mywaitingdb.insertdata(editText.getText().toString(),editText6.getText().toString(),editText4.getText().toString(),editText5.getText().toString(),editText7.getText().toString());
                    if (isInserted == true ){
                        //Toast.makeText(Main5Activity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        editText.setText("");
                        editText6.setText("");
                        editText4.setText("");
                        editText5.setText("");
                        editText7.setText("");
                        Toast.makeText(WaitingListBus.this,"Thank you fot your subscription!",Toast.LENGTH_SHORT).show();

                        Intent aaa = new Intent(WaitingListBus.this , sidebar.class);
                        startActivity(aaa);
                    }else{
                        Toast.makeText(WaitingListBus.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }



}
