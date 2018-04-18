package kylehorton.ser210.quinnipiac.edu.critiquecenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class FavoritesSQL extends SQLiteOpenHelper {
    public static final String MY_TABLE = "review_table";
    public static final String ID1 = "ID";
    private static final String ID2 = "name";
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

    //delete a single item from the data base
    public void delete(String favorite) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(MY_TABLE, ID1 + "=?", new String[]{favorite});
        db.close();
    }
}