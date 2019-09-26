package nilfars.uee.bus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabase extends SQLiteOpenHelper {


    public MyDatabase(@Nullable Context context) {
        super(context, "BusDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table newfeeds (id integer primary key autoincrement,nname varchar(20),nmail varchar(20),ncomment varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertData(String uname,String umail,String ucomment){

        SQLiteDatabase sd=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put("customer_name",uname);
        cv.put("email",umail);
        cv.put("comment",ucomment);

        sd.insert("feedsTable", null,cv);

    }

    public Cursor getInfo(){

        SQLiteDatabase sd=this.getReadableDatabase();
        Cursor c=sd.rawQuery("select * from feedsTable", null);
        return c;
    }
}
