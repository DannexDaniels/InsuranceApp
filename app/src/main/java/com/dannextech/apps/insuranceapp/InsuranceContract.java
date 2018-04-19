package com.dannextech.apps.insuranceapp;

import android.provider.BaseColumns;

/**
 * Created by root on 2/19/18.
 */

public class InsuranceContract {
    public InsuranceContract() {
    }

    public static class UserAccountDb implements BaseColumns{
        public static final String TABLE_NAME = "user";

        public static final String COL_NAME = "name";
        public static final String COL_ID = "idno";
        public static final String COL_AGE = "age";
        public static final String COL_PHONE = "phone";
        public static final String COL_EMAIL = "email";
        public static final String COL_PASSWORD = "password";
        public static final String COL_TYPE = "type";
        public static final String COL_PRICE = "price";
        public static final String COL_PAYMENT_STATUS = "paid";

    }
}
