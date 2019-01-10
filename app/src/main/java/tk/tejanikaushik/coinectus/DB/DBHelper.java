package tk.tejanikaushik.coinectus.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import tk.tejanikaushik.coinectus.DataTest.Data;

public class DBHelper extends SQLiteOpenHelper {
    public static String DB_NAME = "data";
    public static String TABLE_DB = "transactionTable";
    public static String ID_DB = "id";
    public static String TIME_DB = "time";
    public static String GET_AMOUNT_DB = "amountGet";
    public static String USE_AMOUNT_DB = "amountUse";
    public static String NOTE_DB = "note";
    public static int DB_VERSION = 1;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_DB + "("
                    + ID_DB + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + TIME_DB + " TEXT,"
                    + GET_AMOUNT_DB + " TEXT,"
                    + USE_AMOUNT_DB + " TEXT,"
                    + NOTE_DB + " TEXT "
                    + ")";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("DROP TABLE "+TABLE_DB);
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = "DROP TABLE IF EXISTS " + TABLE_DB;
        db.execSQL(drop);

        // Create tables again
        onCreate(db);
    }

    //..
    //Insert Data
    public long insertNote(String time, String getAmount, String useAmount, String note) {
        Log.d("testetetststett", "DATA FOR INSERT IS : " + time.concat(getAmount).concat(useAmount).concat(note));
        // get writable database as we want to write data
        Log.d("KAUHIuhhkhks", "start" + 01);
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("KAUHIuhhkhks", "start" + 02);
        ContentValues values = new ContentValues();
        values.put(TIME_DB, time);
        values.put(GET_AMOUNT_DB, getAmount);
        values.put(USE_AMOUNT_DB, useAmount);
        values.put(NOTE_DB, note);
        // insert row
        long id = db.insert(TABLE_DB, null, values);
        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    //..
    //Read All Note
    public ArrayList<Data> getAllNotes() {
        ArrayList<Data> dataAll = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DB;
        
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Data data = new Data();
                data.setId(cursor.getInt(cursor.getColumnIndex(ID_DB)));
                data.setTime(cursor.getString(cursor.getColumnIndex(TIME_DB)));
                data.setAmountGet(cursor.getString(cursor.getColumnIndex(GET_AMOUNT_DB)));
                data.setAmountUse(cursor.getString(cursor.getColumnIndex(USE_AMOUNT_DB)));
                data.setReason(cursor.getString(cursor.getColumnIndex(NOTE_DB)));
                dataAll.add(data);
            } while (cursor.moveToNext());
        } else {
            return new ArrayList<>();
        }
        Log.d("KAUHIuhhkhks", "ID : " + dataAll.get(0).getId() + "\nAmountGet : " + dataAll.get(0).getAmountGet() + "\nAmountUSe : " + dataAll.get(0).getAmountUse() + "\nTIme : " + dataAll.get(0).getTime() + "\nReason : " + dataAll.get(0).getReason());
        // close db connection
        db.close();
        // return notes lis
        return dataAll;
    }

    //..
    //Get Data Count
    public int getCount() {
        String countQuery = "SELECT  * FROM " + TABLE_DB;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }
}
