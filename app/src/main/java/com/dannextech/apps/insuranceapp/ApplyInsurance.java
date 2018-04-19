package com.dannextech.apps.insuranceapp;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ApplyInsurance extends Fragment {

    Button btApply;
    RadioGroup rgInsuranceType;
    RadioButton rbLife,rbHealth,rbVehicle;


    public ApplyInsurance() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_apply_insurance, container, false);

        final InsuranceQueries queries = new InsuranceQueries(getContext());

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        btApply = (Button) v.findViewById(R.id.btApply);
        rgInsuranceType = (RadioGroup) v.findViewById(R.id.rgInsuranceType);
        rbLife = (RadioButton) v.findViewById(R.id.rbLife);
        rbHealth = (RadioButton) v.findViewById(R.id.rbHealth);
        rbVehicle = (RadioButton) v.findViewById(R.id.rbVehicle);

        btApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = null;
                if (rgInsuranceType.getCheckedRadioButtonId()==R.id.rbLife){
                    Toast.makeText(getContext(),"You have selected "+rbLife.getText().toString(),Toast.LENGTH_LONG).show();
                    type = rbLife.getText().toString();
                }else if (rgInsuranceType.getCheckedRadioButtonId()==R.id.rbHealth){
                    Toast.makeText(getContext(),"You have selected "+rbHealth.getText().toString(),Toast.LENGTH_LONG).show();
                    type = rbHealth.getText().toString();
                }else if (rgInsuranceType.getCheckedRadioButtonId()==R.id.rbVehicle){
                    Toast.makeText(getContext(),"You have selected "+rbVehicle.getText().toString(),Toast.LENGTH_LONG).show();
                    type = rbVehicle.getText().toString();
                }

                queries.updateInsurance(preferences.getString(InsuranceContract.UserAccountDb.COL_ID,"empty"),type);
            }
        });
        return v;
    }

}
