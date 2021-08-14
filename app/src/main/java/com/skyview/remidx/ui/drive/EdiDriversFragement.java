package com.skyview.remidx.ui.drive;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.skyview.remidx.R;
import com.skyview.remidx.model_class.DataModel;
import com.skyview.remidx.model_class.DriverModel;
import com.skyview.remidx.network_request.RetrofitConnection;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;

public class EdiDriversFragement extends Fragment implements RetrofitConnection.CallBackRetrofit {
    RetrofitConnection connection=RetrofitConnection.getInstance();
    EditText edit_name,edit_mobile,edit_dob,edit_Address,edit_adharNo,edit_pan_id,edit_licence,edit_dl_validity;
    Button addButton;
    TextInputLayout dl_Validit;
    DriverModel dataModel=null;
    public EdiDriversFragement() {
        // Required empty public constructor
    }
    public EdiDriversFragement(DriverModel dataModel) {
    this.dataModel=dataModel;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_edi_drivers, container, false);
        edit_dl_validity=v.findViewById(R.id.edit_dl_validity);
        edit_name=v.findViewById(R.id.edit_name);
        edit_mobile=v.findViewById(R.id.edit_mobile);
        edit_dob=v.findViewById(R.id.edit_dob);
        edit_Address=v.findViewById(R.id.edit_Address);
        edit_adharNo=v.findViewById(R.id.edit_adharNo);
        edit_pan_id=v.findViewById(R.id.edit_pan_id);
        edit_licence=v.findViewById(R.id.edit_licence);
        addButton=v.findViewById(R.id.addButton);
        dl_Validit=v.findViewById(R.id.dl_Validit);

        if(dataModel!=null){
            setValue(v);
        }
        addButton.setOnClickListener(View ->{
            if(isValid()){
                if (dataModel==null)
                    addDriverData();
                else
                    ediDriver();
            }
            else {
                Toast.makeText(getContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
            }
        });

        edit_dob.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                DatePickerDialog pickerDialog=new DatePickerDialog(getContext());
                pickerDialog.show();
                pickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String date=getDateFormate(i, i1, i2);
                        edit_dob.setText(date);
                        Log.d("date", i+"-"+i1+"-"+i2);
                    }
                });
            }
        });

        edit_dl_validity.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                DatePickerDialog pickerDialog=new DatePickerDialog(getContext());
                pickerDialog.show();
                pickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        Log.d("date2", i+"-"+i1+"-"+i2);
                        String date=getDateFormate(i,i1,i2);
                        edit_dl_validity.setText(date);
                    }
                });
            }
        });

        return v;
    }

    private String getDateFormate(int i, int i1, int i2) {
        String s="";
        if(i<10){
            s+="0"+1+"-";
        }
        else {
            s+=i+"-";
        }
        if (i1<10){
            s+="0"+i1+"-";
        }
        else {
            s+=i1;
        }
        s+=i2;
        return s;
    }

    private void ediDriver() {
        Call<String> call=connection.getApiClient().editdriver(
                dataModel.getID().toString(),
                edit_name.getText().toString(),
                edit_mobile.getText().toString(),
                edit_Address.getText().toString(),
                edit_adharNo.getText().toString(),
                edit_pan_id.getText().toString(),
                edit_licence.getText().toString(),
                edit_dob.getText().toString(),
                edit_dl_validity.getText().toString());
        connection.callApiResponse(getContext(), call, this, "editDriver");
    }

    private void setValue(View v) {
        if (dataModel.getDLValidUpto()!=null)
        edit_dl_validity.setText(dataModel.getDLValidUpto().trim().substring(0,10));
        if (dataModel.getDriverName()!=null)
            edit_name.setText(dataModel.getDriverName());
        edit_mobile.setText("");
        if (dataModel.getDateofbirth()!=null)
            edit_dob.setText(dataModel.getDateofbirth().trim().substring(0,10));
        if (dataModel.getAddress()!=null)
            edit_Address.setText(dataModel.getAddress().toString());
        if (dataModel.getAadharNo()!=null)
            edit_adharNo.setText(dataModel.getAadharNo().toString());
        if (dataModel.getPanId()!=null)
            edit_pan_id.setText(dataModel.getPanId().toString());
        if (dataModel.getDrivingLicense()!=null)
            edit_licence.setText(dataModel.getDrivingLicense());

        addButton.setText("Edit Driver");
    }

    private Boolean isValid(){

        if (TextUtils.isEmpty(edit_name.getText())){
            edit_name.setError("Please fill");
            edit_name.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(edit_mobile.getText())){
            edit_mobile.setError("Please fill");
            edit_mobile.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(edit_dob.getText())){
            edit_dob.setError("Please fill");
            edit_dob.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(edit_Address.getText())){
            edit_Address.setError("Please fill");
            edit_Address.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(edit_adharNo.getText())){
            edit_adharNo.setError("Please fill");
            edit_adharNo.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(edit_pan_id.getText())){
            edit_pan_id.setError("Please fill");
            edit_pan_id.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(edit_licence.getText())){
            edit_licence.setError("Please fill");
            edit_licence.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(edit_dl_validity.getText())){
            edit_dl_validity.setError("Please fill");
            edit_dl_validity.requestFocus();
            return false;
        }


        else {
            return true;
        }
    }

    private void addDriverData() {
        Call<String> call=connection.getApiClient().addDriver(edit_name.getText().toString(),
                edit_mobile.getText().toString(),
                edit_Address.getText().toString(),
                edit_adharNo.getText().toString(),
                edit_pan_id.getText().toString(),
                edit_licence.getText().toString(),
                edit_dob.getText().toString(),
                edit_dl_validity.getText().toString());
        connection.callApiResponse(getContext(), call, this, "adddriver");
    }

    @Override
    public void resposeResult(String s, Boolean b, String action) throws JSONException {
        Log.d("addded", ""+s);
        if (s!=null){
            if(action.equals("adddriver")){
                JSONObject jsonObject=new JSONObject(s);
                if (jsonObject.getString("status").equals("success")){
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.driverFragement, new DrivesList()).commit();
                    Toast.makeText(getContext(), jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
            else {
                JSONObject jsonObject=new JSONObject(s);
                Log.d("editDrive", s);
                if (jsonObject.getString("status").equals("success")){
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.driverFragement, new DrivesList()).commit();
                    Toast.makeText(getContext(), jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}