package nilfars.uee.bus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteDB {

    public static final String KEY_ROWID="_id";
    public static final String KEY_NAME="customer_name";
    public static final String KEY_EMAIL="email";
    public static final String KEY_COMMENT="comment";

    private static final String DATABASE_NAME="BusDB";
    private static final String DATABASE_TABLE="feedsTable";
    private static final int DATABASE_VERSION=1;

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;
    private SQLiteDatabase ourReadableDatabase;


    public SQLiteDB(Context c){
        ourContext=c;
    }


    public long createEntry(String name1, String mail1, String comment1) {
        ContentValues cv=new ContentValues();
        cv.put(KEY_NAME,name1);
        cv.put(KEY_EMAIL,mail1);
        cv.put(KEY_COMMENT,comment1);

        return ourDatabase.insert(DATABASE_TABLE,null,cv);


    }

    public Cursor readData(){

        Cursor c= ourDatabase.rawQuery("select * from feedsTable",null);
        return c;

    }

    public String getData() {
        String []columns =new String[]{KEY_ROWID,KEY_NAME,KEY_EMAIL,KEY_COMMENT};

        Cursor c=ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        String result="";

        int iRow=c.getColumnIndex(KEY_ROWID);
        int iName=c.getColumnIndex(KEY_NAME);
        int iEmail=c.getColumnIndex(KEY_EMAIL);
        int iCommnet=c.getColumnIndex(KEY_COMMENT);

        for(c.moveToFirst(); !c.isAfterLast();c.moveToNext()){
            result= result + c.getString(iRow) + "   " + c.getString((iName)) + "   " + c.getString(iEmail) + " " + c.getString(iCommnet) + "\n";
        }
        return result;
    }

    public String getName(long l) throws SQLException{
        String []columns =new String[]{KEY_ROWID,KEY_NAME,KEY_EMAIL,KEY_COMMENT};
        Cursor c=ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID + "=" + l,null,null,null,null);
        if(c !=null){
            c.moveToFirst();
            String name=c.getString(1);
            return name;
        }
        return null;

    }

    public String getEmail(long l) throws SQLException{
        String []columns =new String[]{KEY_ROWID,KEY_NAME,KEY_EMAIL,KEY_COMMENT};
        Cursor c=ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID + "=" + l,null,null,null,null);
        if(c !=null){
            c.moveToFirst();
            String mail=c.getString(2);
            return mail;
        }
        return null;
    }

    public String getComment(long l) throws SQLException{
        String []columns =new String[]{KEY_ROWID,KEY_NAME,KEY_EMAIL,KEY_COMMENT};
        Cursor c=ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID + "=" + l,null,null,null,null);
        if(c !=null){
            c.moveToFirst();
            String com=c.getString(3);
            return com;
        }
        return null;
    }

    public void updateEntry(long lRow, String name2, String mail2, String comment2)throws SQLException {

        ContentValues cvUpdated=new ContentValues();
        cvUpdated.put(KEY_NAME,name2);
        cvUpdated.put(KEY_EMAIL,mail2);
        cvUpdated.put(KEY_COMMENT,comment2);
        ourDatabase.update(DATABASE_TABLE,cvUpdated,KEY_ROWID + "=" + lRow,null);


    }

    public void deleteEntry(long lRow1) throws SQLException{

        ourDatabase.delete(DATABASE_TABLE,KEY_ROWID + "=" + lRow1,null );

    }


    private static class DbHelper extends SQLiteOpenHelper{


        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
                    KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_NAME + " TEXT NOT NULL, " +
                    KEY_EMAIL+ " TEXT NOT NULL, " +
                    KEY_COMMENT+ " TEXT NOT NULL);"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }

//        public Cursor readData(){
//
//            SQLiteDatabase sd=this.getReadableDatabase();
//            Cursor c= sd.rawQuery("select * from BusDB",null);
//            return c;
//
//        }
    }
    public SQLiteDB open() throws SQLException {
        ourHelper=new DbHelper(ourContext);
        ourDatabase=ourHelper.getWritableDatabase();
        ourReadableDatabase=ourHelper.getReadableDatabase();
        return  this;
    }
    public void close(){
        ourHelper.close();

    }


}
