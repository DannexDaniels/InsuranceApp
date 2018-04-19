package com.dannextech.apps.insuranceapp;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfile extends Fragment {

    TextView tvName, tvId, tvAge, tvPhone, tvEmail, tvPassword, tvType;

    public MyProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        tvName = (TextView) view.findViewById(R.id.tvNameProfile);
        tvId = (TextView) view.findViewById(R.id.tvIdProfile);
        tvAge = (TextView) view.findViewById(R.id.tvAgeProfile);
        tvPhone = (TextView) view.findViewById(R.id.tvPhoneProfile);
        tvEmail = (TextView) view.findViewById(R.id.tvEmailProfile);
        tvPassword = (TextView) view.findViewById(R.id.tvPasswordProfile);
        tvType = (TextView) view.findViewById(R.id.tvTypeProfile);

        tvName.setText(preferences.getString(InsuranceContract.UserAccountDb.COL_NAME,"nul"));
        tvId.setText(preferences.getString(InsuranceContract.UserAccountDb.COL_ID,"nul"));
        tvAge.setText(preferences.getString(InsuranceContract.UserAccountDb.COL_AGE,"nul"));
        tvPhone.setText(preferences.getString(InsuranceContract.UserAccountDb.COL_PHONE,"nul"));
        tvEmail.setText(preferences.getString(InsuranceContract.UserAccountDb.COL_EMAIL,"nul"));
        tvPassword.setText(preferences.getString(InsuranceContract.UserAccountDb.COL_PASSWORD,"nul"));
        tvType.setText(preferences.getString(InsuranceContract.UserAccountDb.COL_TYPE,"nul"));

        return view;
    }

}
