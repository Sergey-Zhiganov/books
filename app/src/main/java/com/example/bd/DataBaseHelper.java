package com.example.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    static final String DATABASE = "BookDB";
    static final int SCHEMA = 1;
    static final String TABLE = "Books";
    public static final String COLUMN_ID = "ID_Book";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_AUTHOR = "Author";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(" CREATE TABLE " + TABLE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_AUTHOR + " TEXT,"
                + COLUMN_NAME + " TEXT);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public Cursor getAllBooks() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE, null, null, null, null, null, null);
    }

    public long addBook(String name, String author) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_AUTHOR, author);

        long result = db.insert(TABLE, null, values);
        db.close();
        return result;
    }

    public int updateBook(int bookId, String name, String author) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_AUTHOR, author);

        int result = db.update(TABLE, values, COLUMN_ID + " = ?", new String[]{String.valueOf(bookId)});
        db.close();
        return result;
    }

    public int deleteBookById(long bookId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE, COLUMN_ID + " = ?", new String[]{String.valueOf(bookId)});
        db.close();
        return result;
    }
}
