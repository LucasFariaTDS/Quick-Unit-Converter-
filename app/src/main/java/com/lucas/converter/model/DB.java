package com.lucas.converter.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DB extends SQLiteOpenHelper implements BaseColumns {
    public static final String Name_Bank = "bank.db";
    public static final String Table = "dataUsers";
    public static final String ID = "id";
    public static final String Username = "username";
    public static final String Password = "password";
    public static final String Email = "email";
    public static final int Version = 1;

    public DB(Context context) {
        super(context, Name_Bank, null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String criarDB = "CREATE TABLE " + DB.Table +
                "( " + DB.ID + " INTEGER PRIMARY KEY, "
                + DB.Username + " text, "
                + DB.Password + " text, "
                + DB.Email + " text)";
        sqLiteDatabase.execSQL(criarDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table);
        onCreate(sqLiteDatabase);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
