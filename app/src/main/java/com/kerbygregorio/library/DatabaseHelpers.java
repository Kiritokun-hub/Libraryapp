package com.kerbygregorio.library;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelpers extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Library.db";
    private static final int DATABASE_VERSION = 2; // Updated version

    public static final String TABLE_NAME = "Library";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_AUTHORS = "Authors";
    public static final String COLUMN_DESCRIPTIONS = "Description";
    public static final String COLUMN_IMAGE_RESOURCE_ID = "image_resource_id"; // New column

    public DatabaseHelpers(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_AUTHORS + " TEXT, " +
                COLUMN_DESCRIPTIONS + " TEXT, " +
                COLUMN_IMAGE_RESOURCE_ID + " INTEGER)"; // Include the new column
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
