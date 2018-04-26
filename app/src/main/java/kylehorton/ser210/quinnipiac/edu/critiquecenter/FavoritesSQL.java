package kylehorton.ser210.quinnipiac.edu.critiquecenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class FavoritesSQL extends SQLiteOpenHelper {
    public static final String MY_TABLE = "review_table";
    public static final String ID1 = "ID";
    private static final String ID2 = "name";
    private static final String ID3 = "genre";
    public static ArrayList list;

    //constructed required by helper
    public FavoritesSQL(Context context) {
        super(context, MY_TABLE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + MY_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ID2 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS " + MY_TABLE);
        onCreate(db);
    }

    // adds data to the SQL data base. Called in Trivia activity
    public boolean addData(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID2, item);
        long result = db.insert(MY_TABLE, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    // Returns all data from database
    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + MY_TABLE;
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    // deletes all rows from table database
    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(MY_TABLE, null, null);
    }


    /**
     * Returns only the ID that matches the name passed in
     *
     * @param name
     * @return
     */
    public Cursor getItemID(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + ID1 + " FROM " + MY_TABLE +
                " WHERE " + ID2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    //deleted first item in database
    public void deleteEntry() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.query(MY_TABLE, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            long rowId = cursor.getLong(cursor.getColumnIndex(ID1));
            database.delete(MY_TABLE, ID1 + "=" + rowId, null);
        }
    }
}
