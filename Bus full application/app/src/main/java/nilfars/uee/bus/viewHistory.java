package nilfars.uee.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;


import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.List;

public class viewHistory extends AppCompatActivity {

    private SlidrInterface slidr;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);
        slidr = Slidr.attach(this);


        TextView tv=findViewById(R.id.sql_data);
        SQLiteDB info=new SQLiteDB(this);
        info.open();
        String data= info.getData();
        info.close();
        tv.setText(data);

    }
}
