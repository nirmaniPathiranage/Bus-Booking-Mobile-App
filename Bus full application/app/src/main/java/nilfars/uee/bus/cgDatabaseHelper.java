package nilfars.uee.bus;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class cgDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mywaitingdb.db";
    public static final String TABLE_NAME = "waitinglist_TABLE";
    public static final String COL_1 = "Name";
    public static final String COL_2 = "Email";
    public static final String COL_3 = "Phone";
    public static final String COL_4 = "Nic";
    public static final String COL_5 = "Seats";

    public cgDatabaseHelper(Context context) {
        super(context,DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create TABLE " + TABLE_NAME + "(Name TEXT,Email TEXT,Phone TEXT,Nic TEXT,Seats TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertdata(String Name, String Email, String Phone,String Nic,String Seats){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,Name);
        contentValues.put(COL_2,Email);
        contentValues.put(COL_3,Phone);
        contentValues.put(COL_4,Nic);
        contentValues.put(COL_5,Seats);
        long result = sqLiteDatabase.insert(TABLE_NAME, null,contentValues);
        if (result == -1){
            return false;
        }else {
            return true;

        }
    }
}







