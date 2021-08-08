package com.skyview.remidx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.skyview.remidx.R;
import com.skyview.remidx.model_class.DriverModel;

public class SingleDriverDetails extends AppCompatActivity {
    DriverModel model;
    TextView dobText,driverNameText,addressText,
            wehicalNoText,salaryText,panIdText,dlText,adharNo,dlExpiryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_single_driver_details);
        dobText=findViewById(R.id.dobText);
        driverNameText=findViewById(R.id.driverNameText);
        addressText=findViewById(R.id.addressText);
        wehicalNoText=findViewById(R.id.wehicalNoText);
        salaryText=findViewById(R.id.salaryText);
        panIdText=findViewById(R.id.panIdText);
        dlText=findViewById(R.id.dlText);
        adharNo=findViewById(R.id.adharNo);
        dlExpiryText=findViewById(R.id.dlExpiryText);
        setTextValue();
    }

    private void setTextValue() {
        Intent intent=getIntent();
        model=(DriverModel) intent.getSerializableExtra("data");
        if (model!=null){
            if (model.getDriverName()!=null)
            driverNameText.setText(model.getDriverName());
            if (model.getDateofbirth()!=null){
                String dob=model.getDateofbirth();
                dob=dob.substring(0,10);
                dobText.setText(dob);
            }
            if (model.getAddress()!=null)
                addressText.setText(model.getAddress().toString());
            if (model.getAadharNo()!=null)
                adharNo.setText(model.getAadharNo().toString());
            if (model.getWechileNo()!=null)
                wehicalNoText.setText(model.getWechileNo().toString());
            if (model.getSalaryAmtPerMonth()!=null)
                salaryText.setText(model.getSalaryAmtPerMonth().toString());
            if (model.getPanId()!=null)
                panIdText.setText(model.getPanId().toString());
            if (model.getDrivingLicense()!=null)
                dlText.setText(model.getDrivingLicense());
            if (model.getDLValidUpto()!=null)
                dlExpiryText.setText(model.getDLValidUpto());
        }
    }
}