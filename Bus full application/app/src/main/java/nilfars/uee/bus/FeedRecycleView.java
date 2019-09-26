package nilfars.uee.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class FeedRecycleView extends AppCompatActivity {

    RecyclerView rv;

    String user_name,user_mail,user_comment,user_id;
    ArrayList<FeedModel> fm=new ArrayList<>();

    MyDatabase mydb;

    Cursor c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_recycle_view);

        rv=findViewById(R.id.rev);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(FeedRecycleView.this);
        rv.setLayoutManager(layoutManager);


//        SQLiteDB info=new SQLiteDB(this);
//        info.open();
//        Cursor data= info.readData();

        mydb=new MyDatabase(FeedRecycleView.this);

        c=mydb.getInfo();

        if(c.getCount()>0){

            if(c.moveToNext()){

                do{

                    user_id=c.getString(0);
                    user_name=c.getString(1);
                    user_mail=c.getString(2);
                    user_comment=c.getString(3);

                    FeedModel fmodel=new FeedModel(user_id,user_name,user_mail,user_comment);
                    fm.add(fmodel);

                }while(c.moveToNext());

            }
        }
        FeedAdapter my=new FeedAdapter(FeedRecycleView.this,fm);
        rv.setAdapter(my);


//        info.close();
    }
}
