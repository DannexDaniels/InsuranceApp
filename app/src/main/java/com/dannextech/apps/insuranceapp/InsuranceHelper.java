package com.dannextech.apps.insuranceapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 2/19/18.
 */

public class InsuranceHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "insurance";
    public static final int DATABASE_VERSION = 1;

    public static final String CREATE_TABLE = "CREATE TABLE " +
            InsuranceContract.UserAccountDb.TABLE_NAME + " ( " +
            InsuranceContract.UserAccountDb.COL_NAME + " TEXT, " +
            InsuranceContract.UserAccountDb.COL_AGE + " TEXT, " +
            InsuranceContract.UserAccountDb.COL_PHONE + " TEXT, " +
            InsuranceContract.UserAccountDb.COL_EMAIL + " TEXT, " +
            InsuranceContract.UserAccountDb.COL_ID + " TEXT PRIMARY KEY, " +
            InsuranceContract.UserAccountDb.COL_PASSWORD + " TEXT, " +
            InsuranceContract.UserAccountDb.COL_TYPE + " TEXT, " +
            InsuranceContract.UserAccountDb.COL_PRICE + " TEXT, " +
            InsuranceContract.UserAccountDb.COL_PAYMENT_STATUS + " TEXT)";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + InsuranceContract.UserAccountDb.TABLE_NAME;

    public InsuranceHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
