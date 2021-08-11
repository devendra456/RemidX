package com.skyview.remidx.ui.vehicle.fragments;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.skyview.remidx.R;
import com.skyview.remidx.model_class.DetailsModel;
import com.skyview.remidx.network_request.RetrofitConnection;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;

public class AddVehicleFragment extends Fragment implements View.OnClickListener, RetrofitConnection.CallBackRetrofit {

    private EditText VehicleNumber;
    private EditText Insurance_From_Date;
    private EditText Insurance_To_Date;
    private EditText Fitness_From_Date;
    private EditText Fitness_To_Date;
    private EditText Permit_State_FromDate;
    private EditText Permit_State_ToDate;
    private EditText Permit_Globel_FromDate;
    private EditText Permit_Globel_ToDate;
    private EditText Tax_From;
    private EditText Tax_to;
    private EditText PUC_From;
    private EditText PUC_To;
    private EditText Whicle_OwnerName;
    private EditText Mobile;
    private EditText InsuranceCompany;

    private LinearLayout Layout_Insurance_From_Date;
    private LinearLayout Layout_Insurance_To_Date;
    private LinearLayout Layout_Fitness_From_Date;
    private LinearLayout Layout_Fitness_To_Date;
    private LinearLayout Layout_Permit_State_FromDate;
    private LinearLayout Layout_Permit_State_ToDate;
    private LinearLayout Layout_Permit_Globel_FromDate;
    private LinearLayout Layout_Permit_Globel_ToDate;
    private LinearLayout Layout_Tax_From;
    private LinearLayout Layout_Tax_to;
    private LinearLayout Layout_PUC_From;
    private LinearLayout Layout_PUC_To;

    private Button submitButton;

    private DetailsModel detailsModel;

    public AddVehicleFragment(DetailsModel detailsModel) {
        this.detailsModel = detailsModel;
    }

    public AddVehicleFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_vehicle, container, false);

        VehicleNumber = view.findViewById(R.id.vehicleNumber);
        Insurance_From_Date = view.findViewById(R.id.Insurance_From_Date);
        Insurance_To_Date = view.findViewById(R.id.Insurance_To_Date);
        Fitness_From_Date = view.findViewById(R.id.Fitness_From_Date);
        Fitness_To_Date = view.findViewById(R.id.Fitness_To_Date);
        Permit_State_FromDate = view.findViewById(R.id.Permit_State_FromDate);
        Permit_State_ToDate = view.findViewById(R.id.Permit_State_ToDate);
        Permit_Globel_FromDate = view.findViewById(R.id.Permit_Globel_FromDate);
        Permit_Globel_ToDate = view.findViewById(R.id.Permit_Globel_ToDate);
        Tax_From = view.findViewById(R.id.Tax_From);
        Tax_to = view.findViewById(R.id.Tax_to);
        PUC_From = view.findViewById(R.id.PUC_From);
        PUC_To = view.findViewById(R.id.PUC_To);
        Whicle_OwnerName = view.findViewById(R.id.Whicle_OwnerName);
        Mobile = view.findViewById(R.id.Mobile);
        InsuranceCompany = view.findViewById(R.id.InsuranceCompany);

        Layout_Insurance_From_Date = view.findViewById(R.id.Layout_Insurance_From_Date);
        Layout_Insurance_To_Date = view.findViewById(R.id.Layout_Insurance_To_Date);
        Layout_Fitness_From_Date = view.findViewById(R.id.Layout_Fitness_From_Date);
        Layout_Fitness_To_Date = view.findViewById(R.id.Layout_Fitness_To_Date);
        Layout_Permit_State_FromDate = view.findViewById(R.id.Layout_Permit_State_FromDate);
        Layout_Permit_State_ToDate = view.findViewById(R.id.Layout_Permit_State_ToDate);
        Layout_Permit_Globel_FromDate = view.findViewById(R.id.Layout_Permit_Globel_FromDate);
        Layout_Permit_Globel_ToDate = view.findViewById(R.id.Layout_Permit_Globel_ToDate);
        Layout_Tax_From = view.findViewById(R.id.Layout_Tax_From);
        Layout_Tax_to = view.findViewById(R.id.Layout_Tax_to);
        Layout_PUC_From = view.findViewById(R.id.Layout_PUC_From);
        Layout_PUC_To = view.findViewById(R.id.Layout_PUC_To);

        submitButton = view.findViewById(R.id.submit);

        Layout_Insurance_From_Date.setOnClickListener(this::onClick);
        Layout_Insurance_To_Date.setOnClickListener(this::onClick);
        Layout_Fitness_From_Date.setOnClickListener(this::onClick);
        Layout_Fitness_To_Date.setOnClickListener(this::onClick);
        Layout_Permit_State_FromDate.setOnClickListener(this::onClick);
        Layout_Permit_State_ToDate.setOnClickListener(this::onClick);
        Layout_Permit_Globel_FromDate.setOnClickListener(this::onClick);
        Layout_Permit_Globel_ToDate.setOnClickListener(this::onClick);
        Layout_Tax_From.setOnClickListener(this::onClick);
        Layout_Tax_to.setOnClickListener(this::onClick);
        Layout_PUC_From.setOnClickListener(this::onClick);
        Layout_PUC_To.setOnClickListener(this::onClick);
        submitButton.setOnClickListener(this::onClick);

        if (detailsModel != null) {
            VehicleNumber.setText(detailsModel.getWhicleNum());
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
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (
                id == R.id.Layout_Insurance_From_Date ||
                        id == R.id.Layout_Insurance_To_Date ||
                        id == R.id.Layout_Fitness_From_Date ||
                        id == R.id.Layout_Fitness_To_Date ||
                        id == R.id.Layout_Permit_State_FromDate ||
                        id == R.id.Layout_Permit_State_ToDate ||
                        id == R.id.Layout_Permit_Globel_FromDate ||
                        id == R.id.Layout_Permit_Globel_ToDate ||
                        id == R.id.Layout_Tax_From ||
                        id == R.id.Layout_Tax_to ||
                        id == R.id.Layout_PUC_From ||
                        id == R.id.Layout_PUC_To
        ) {
            Dialog dialog = new Dialog(getContext());
            dialog.setContentView(R.layout.calender_layout);
            dialog.show();
            DatePicker datePicker = dialog.findViewById(R.id.calenderView);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        //String date = dayOfMonth + "-" + monthOfYear + "-" + year;
                        String date = dayOfMonth + "-" + monthOfYear + "-" + year;
                        switch (id) {
                            case R.id.Layout_Insurance_From_Date:
                                Insurance_From_Date.setText(date);
                                break;
                            case R.id.Layout_Insurance_To_Date:
                                Insurance_To_Date.setText(date);
                                break;
                            case R.id.Layout_Fitness_From_Date:
                                Fitness_From_Date.setText(date);
                                break;
                            case R.id.Layout_Fitness_To_Date:
                                Fitness_To_Date.setText(date);
                                break;
                            case R.id.Layout_Permit_State_FromDate:
                                Permit_State_FromDate.setText(date);
                                break;
                            case R.id.Layout_Permit_State_ToDate:
                                Permit_State_ToDate.setText(date);
                                break;
                            case R.id.Layout_Permit_Globel_FromDate:
                                Permit_Globel_FromDate.setText(date);
                                break;
                            case R.id.Layout_Permit_Globel_ToDate:
                                Permit_Globel_ToDate.setText(date);
                                break;
                            case R.id.Layout_Tax_From:
                                Tax_From.setText(date);
                                break;
                            case R.id.Layout_Tax_to:
                                Tax_to.setText(date);
                                break;
                            case R.id.Layout_PUC_From:
                                PUC_From.setText(date);
                                break;
                            case R.id.Layout_PUC_To:
                                PUC_To.setText(date);
                                break;
                        }
                    }
                });
            }

        }

        if (id == R.id.submit) {
            if (
                    !TextUtils.isEmpty(VehicleNumber.getText()) &&
                            !TextUtils.isEmpty(Insurance_From_Date.getText()) &&
                            !TextUtils.isEmpty(Insurance_To_Date.getText()) &&
                            !TextUtils.isEmpty(Fitness_From_Date.getText()) &&
                            !TextUtils.isEmpty(Fitness_To_Date.getText()) &&
                            !TextUtils.isEmpty(Permit_State_FromDate.getText()) &&
                            !TextUtils.isEmpty(Permit_State_ToDate.getText()) &&
                            !TextUtils.isEmpty(Permit_Globel_FromDate.getText()) &&
                            !TextUtils.isEmpty(Permit_Globel_ToDate.getText()) &&
                            !TextUtils.isEmpty(Tax_From.getText()) &&
                            !TextUtils.isEmpty(Tax_to.getText()) &&
                            !TextUtils.isEmpty(PUC_From.getText()) &&
                            !TextUtils.isEmpty(PUC_To.getText()) &&
                            !TextUtils.isEmpty(Whicle_OwnerName.getText()) &&
                            !TextUtils.isEmpty(Mobile.getText()) &&
                            !TextUtils.isEmpty(InsuranceCompany.getText())
            ) {

                if(detailsModel==null){
                    RetrofitConnection retrofitConnection = RetrofitConnection.getInstance();
                    Call<String> call = retrofitConnection.getApiClient().addVehicle(
                            Whicle_OwnerName.getText().toString(),
                            VehicleNumber.getText().toString(),
                            Mobile.getText().toString(),
                            InsuranceCompany.getText().toString(),
                            Insurance_From_Date.getText().toString(),
                            Insurance_To_Date.getText().toString(),
                            Permit_Globel_FromDate.getText().toString(),
                            Permit_Globel_ToDate.getText().toString(),
                            Permit_State_FromDate.getText().toString(),
                            Permit_State_ToDate.getText().toString(),
                            PUC_From.getText().toString(),
                            PUC_To.getText().toString(),
                            Tax_From.getText().toString(),
                            Tax_to.getText().toString(),
                            Fitness_From_Date.getText().toString(),
                            Fitness_To_Date.getText().toString()
                    );
                    retrofitConnection.callApiResponse(getContext(), call, this, "ADD_VEHICLE");
                }
                else {
                    RetrofitConnection retrofitConnection = RetrofitConnection.getInstance();
                    Call<String> call = retrofitConnection.getApiClient().editVehicle(
                            Integer.parseInt(detailsModel.getID().toString()),
                            Whicle_OwnerName.getText().toString(),
                            VehicleNumber.getText().toString(),
                            Mobile.getText().toString(),
                            InsuranceCompany.getText().toString(),
                            Insurance_From_Date.getText().toString(),
                            Insurance_To_Date.getText().toString(),
                            Permit_Globel_FromDate.getText().toString(),
                            Permit_Globel_ToDate.getText().toString(),
                            Permit_State_FromDate.getText().toString(),
                            Permit_State_ToDate.getText().toString(),
                            PUC_From.getText().toString(),
                            PUC_To.getText().toString(),
                            Tax_From.getText().toString(),
                            Tax_to.getText().toString(),
                            Fitness_From_Date.getText().toString(),
                            Fitness_To_Date.getText().toString()
                    );
                    retrofitConnection.callApiResponse(getContext(), call, this, "EDIT_VEHICLE");
                }

            } else {
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void resposeResult(String s, Boolean b, String action) throws JSONException {

        if(action.equals("ADD_VEHICLE")) {
            Log.d("resposeResult", "resposeResult: " + s);
            JSONObject jsonObject = new JSONObject(s);
            if (jsonObject.getString("status").equals("success")) {
                Toast.makeText(getContext(), "Record added successfully", Toast.LENGTH_SHORT).show();
                VehicleNumber.setText("");
                Insurance_From_Date.setText("");
                Insurance_To_Date.setText("");
                Fitness_From_Date.setText("");
                Fitness_To_Date.setText("");
                Permit_State_FromDate.setText("");
                Permit_State_ToDate.setText("");
                Permit_Globel_FromDate.setText("");
                Permit_Globel_ToDate.setText("");
                Tax_From.setText("");
                Tax_to.setText("");
                PUC_From.setText("");
                PUC_To.setText("");
                Whicle_OwnerName.setText("");
                Mobile.setText("");
                InsuranceCompany.setText("");
            }
        }

        if(action.equals("EDIT_VEHICLE")){
            Log.d("EditresposeResult", "resposeResult: "+s);
            JSONObject jsonObject = new JSONObject(s);
            if(jsonObject.getString("status").equals("success")){
                Toast.makeText(getContext(), "Updated successfully", Toast.LENGTH_LONG).show();
            }
        }
    }
}