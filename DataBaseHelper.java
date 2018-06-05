package com.example.knight.b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Knight on 19/10/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SQLiteDatabase.db";
    private static final String KEY_NAME = "name";
    private static final String KEY_NO = "number";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + "user" + "("
                + KEY_NAME + " TEXT,"
                + KEY_NO + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "user");
        onCreate(sqLiteDatabase);
    }

    // Insertion and deletion operations
    void addContact(LogInDetails logInDetails) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, logInDetails.getName()); // Contact Name
        values.put(KEY_NO, logInDetails.getNumber()); // Contact Phone

        // Inserting Row
        db.insert("user", null, values);
        db.close(); // Closing database connection
    }

    public LogInDetails getAllContacts() {

        // Select All Query
        String selectQuery = "SELECT  * FROM " + "user";
        LogInDetails contact = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                contact = new LogInDetails(cursor.getString(0), cursor.getString(1));


            } while (cursor.moveToNext());
        }
        return contact;
    }


}
