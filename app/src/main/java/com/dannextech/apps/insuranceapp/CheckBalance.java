package com.dannextech.apps.insuranceapp;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CheckBalance extends Fragment {

    TextView tvStatus, tvType, tvAmount;
    Button btPay;


    public CheckBalance() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_check_balance, container, false);

        tvStatus = (TextView) v.findViewById(R.id.tvPaidStatus);
        tvType = (TextView) v.findViewById(R.id.tvtype);
        tvAmount = (TextView) v.findViewById(R.id.tvAmount);
        btPay = (Button) v.findViewById(R.id.btMakePayment);

        final InsuranceQueries queries = new InsuranceQueries(getContext());

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        tvType.setText(preferences.getString(InsuranceContract.UserAccountDb.COL_TYPE,"empty"));
        tvAmount.setText(preferences.getString(InsuranceContract.UserAccountDb.COL_PRICE,"empty"));
        tvStatus.setText(preferences.getString(InsuranceContract.UserAccountDb.COL_PAYMENT_STATUS,"empty"));

        btPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvAmount.getText().toString().equals("null")||tvType.getText().toString().equals("null")||tvStatus.getText().toString().equals("null")){
                    Snackbar.make(v,"You have not applied for an Insurance Cover yet",Snackbar.LENGTH_LONG).show();
                }else {
                    queries.updatePayment(preferences.getString(InsuranceContract.UserAccountDb.COL_ID,"empty"));
                    Snackbar.make(v,"You have paid successfully",Snackbar.LENGTH_LONG).show();
                    startActivity(new Intent(getContext(),Home.class));
                }
            }
        });

        return v;
    }

}
