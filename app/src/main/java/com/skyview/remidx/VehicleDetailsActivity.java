package com.skyview.remidx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.skyview.remidx.model_class.DetailsModel;

public class VehicleDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Vehicle Details");
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_vehicle_details);

        DetailsModel detailsModel= (DetailsModel) getIntent().getSerializableExtra("list");

        TextView vehicleNumber=findViewById(R.id.vehicleNumber);
        TextView Insurance_From_Date=findViewById(R.id.Insurance_From_Date);
        TextView Insurance_To_Date=findViewById(R.id.Insurance_To_Date);
        TextView Fitness_From_Date=findViewById(R.id.Fitness_From_Date);
        TextView Fitness_To_Date=findViewById(R.id.Fitness_To_Date);
        TextView Permit_State_FromDate=findViewById(R.id.Permit_State_FromDate);
        TextView Permit_State_ToDate=findViewById(R.id.Permit_State_ToDate);
        TextView Permit_Globel_FromDate=findViewById(R.id.Permit_Globel_FromDate);
        TextView Permit_Globel_ToDate=findViewById(R.id.Permit_Globel_ToDate);
        TextView Tax_From=findViewById(R.id.Tax_From);
        TextView Tax_to=findViewById(R.id.Tax_to);
        TextView PUC_From=findViewById(R.id.PUC_From);
        TextView PUC_To=findViewById(R.id.PUC_To);
        TextView Whicle_OwnerName=findViewById(R.id.Whicle_OwnerName);
        TextView Mobile=findViewById(R.id.Mobile);
        TextView InsuranceCompany=findViewById(R.id.InsuranceCompany);
        TextView createdBy=findViewById(R.id.createdBy);
        TextView CreatedOn=findViewById(R.id.CreatedOn);
        TextView updatedOn=findViewById(R.id.updatedOn);


        if (detailsModel != null) {
            vehicleNumber.setText(detailsModel.getWhicleNum());
            Insurance_From_Date.setText(detailsModel.getInsuranceFromDate());
            Insurance_To_Date.setText(detailsModel.getInsuranceToDate());
            Fitness_From_Date.setText(detailsModel.getFitnessFromDate());
            Fitness_To_Date.setText(detailsModel.getFitnessToDate());
            Permit_State_FromDate.setText(detailsModel.getPermitStateFromDate());
            Permit_State_ToDate.setText(detailsModel.getPermitStateToDate());
            Permit_Globel_FromDate.setText(String.valueOf(detailsModel.getPermitGlobelFromDate()));
            Permit_Globel_ToDate.setText(String.valueOf(detailsModel.getPermitGlobelToDate()));
            Tax_From.setText(detailsModel.getTaxFrom());
            Tax_to.setText(detailsModel.getTaxTo());
            PUC_From.setText(detailsModel.getPUCFrom());
            PUC_To.setText(detailsModel.getPUCTo());
            Whicle_OwnerName.setText(detailsModel.getWhicleOwnerName());
            Mobile.setText(detailsModel.getMobile());
            InsuranceCompany.setText(detailsModel.getInsuranceCompany());
            createdBy.setText(detailsModel.getCreatedBy());
            CreatedOn.setText(String.valueOf(detailsModel.getCreatedon()));
            updatedOn.setText(detailsModel.getUpdatedon());
        }


    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }
}