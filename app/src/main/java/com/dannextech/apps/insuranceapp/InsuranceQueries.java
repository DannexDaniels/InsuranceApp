package com.dannextech.apps.insuranceapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import static com.dannextech.apps.insuranceapp.MainActivity.TAG;

/**
 * Created by root on 2/19/18.
 */

public class InsuranceQueries {
    Context context;
    InsuranceHelper helper;
    SQLiteDatabase database;

    public InsuranceQueries(Context context) {
        this.context = context;
        helper = new InsuranceHelper(context);
    }

    public boolean createAccount(String name, String id, String age, String email, String phone, String password){
        database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InsuranceContract.UserAccountDb.COL_NAME,name);
        values.put(InsuranceContract.UserAccountDb.COL_ID,id);
        values.put(InsuranceContract.UserAccountDb.COL_AGE,age);
        values.put(InsuranceContract.UserAccountDb.COL_EMAIL,email);
        values.put(InsuranceContract.UserAccountDb.COL_PHONE,phone);
        values.put(InsuranceContract.UserAccountDb.COL_PASSWORD,password);
        values.put(InsuranceContract.UserAccountDb.COL_TYPE,"null");
        values.put(InsuranceContract.UserAccountDb.COL_PRICE,"null");
        values.put(InsuranceContract.UserAccountDb.COL_PAYMENT_STATUS,"null");

        long result = database.insert(InsuranceContract.UserAccountDb.TABLE_NAME,null,values);

        return result==-1?false:true;
    }

    public boolean authenticateUser(String userName, String password){
        database = helper.getReadableDatabase();

        String user = null, pass = null, email = null, age = null, phone = null, uId = null, type = null, paid=null, price = null;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = preferences.edit();

        String columns[] = {InsuranceContract.UserAccountDb.COL_EMAIL, InsuranceContract.UserAccountDb.COL_PASSWORD,InsuranceContract.UserAccountDb.COL_PAYMENT_STATUS,InsuranceContract.UserAccountDb.COL_PRICE,InsuranceContract.UserAccountDb.COL_TYPE,InsuranceContract.UserAccountDb.COL_NAME,InsuranceContract.UserAccountDb.COL_ID,InsuranceContract.UserAccountDb.COL_PHONE,InsuranceContract.UserAccountDb.COL_AGE};
        String selection = InsuranceContract.UserAccountDb.COL_EMAIL + " = ? ";
        String selectionArgs[] = {userName};

        Cursor cursor = database.query(InsuranceContract.UserAccountDb.TABLE_NAME,columns,selection,selectionArgs,null,null,null);

        while (cursor.moveToNext()){
            user = cursor.getString(cursor.getColumnIndexOrThrow(InsuranceContract.UserAccountDb.COL_NAME));
            pass = cursor.getString(cursor.getColumnIndexOrThrow(InsuranceContract.UserAccountDb.COL_PASSWORD));
            email = cursor.getString(cursor.getColumnIndexOrThrow(InsuranceContract.UserAccountDb.COL_EMAIL));
            age = cursor.getString(cursor.getColumnIndexOrThrow(InsuranceContract.UserAccountDb.COL_AGE));
            phone = cursor.getString(cursor.getColumnIndexOrThrow(InsuranceContract.UserAccountDb.COL_PHONE));
            uId = cursor.getString(cursor.getColumnIndexOrThrow(InsuranceContract.UserAccountDb.COL_ID));
            price = cursor.getString(cursor.getColumnIndexOrThrow(InsuranceContract.UserAccountDb.COL_PRICE));
            type = cursor.getString(cursor.getColumnIndexOrThrow(InsuranceContract.UserAccountDb.COL_TYPE));
            paid = cursor.getString(cursor.getColumnIndexOrThrow(InsuranceContract.UserAccountDb.COL_PAYMENT_STATUS));

        }

        if (email==null){
            Toast.makeText(context,"User with email address "+ userName + " doesn't exist",Toast.LENGTH_LONG).show();
        }

        if (userName.equals(email) && password.equals(pass)){
            edit.putString(InsuranceContract.UserAccountDb.COL_NAME,user);
            edit.putString(InsuranceContract.UserAccountDb.COL_EMAIL,email);
            edit.putString(InsuranceContract.UserAccountDb.COL_ID,uId);
            edit.putString(InsuranceContract.UserAccountDb.COL_PHONE,phone);
            edit.putString(InsuranceContract.UserAccountDb.COL_AGE,age);
            edit.putString(InsuranceContract.UserAccountDb.COL_NAME,user);
            edit.putString(InsuranceContract.UserAccountDb.COL_TYPE,type);
            edit.putString(InsuranceContract.UserAccountDb.COL_PAYMENT_STATUS,paid);
            edit.putString(InsuranceContract.UserAccountDb.COL_PRICE,price);
            edit.apply();
            return true;
        }else {
            return false;
        }
    }

    public void updatePayment(String idNumber){
        database = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(InsuranceContract.UserAccountDb.COL_PAYMENT_STATUS,"Paid");

        String selection = InsuranceContract.UserAccountDb.COL_ID + " =? ";
        String selectionArgs[] = {idNumber};

        database.update(InsuranceContract.UserAccountDb.TABLE_NAME,values,selection,selectionArgs);

        Log.e(TAG, "updatePayment: successfull");

    }

    public void updateInsurance(String idNumber,String type){
        database = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(InsuranceContract.UserAccountDb.COL_TYPE,type);
        values.put(InsuranceContract.UserAccountDb.COL_PAYMENT_STATUS,"NOT PAID");
        values.put(InsuranceContract.UserAccountDb.COL_PRICE,"1000");

        String selection = InsuranceContract.UserAccountDb.COL_ID + " =? ";
        String selectionArgs[] = {idNumber};

        database.update(InsuranceContract.UserAccountDb.TABLE_NAME,values,selection,selectionArgs);

        Log.e(TAG, "updateInsurance: successfull");
    }
}
