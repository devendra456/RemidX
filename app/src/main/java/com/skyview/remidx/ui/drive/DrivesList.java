package com.skyview.remidx.ui.drive;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.skyview.remidx.Adapters.DriverListAdapter;
import com.skyview.remidx.R;
import com.skyview.remidx.model_class.DriverModel;
import com.skyview.remidx.network_request.RetrofitConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class DrivesList extends Fragment implements DriverListAdapter.OnEditClick , RetrofitConnection.CallBackRetrofit{
    private List<DriverModel> driverModelList;
    private RecyclerView recyclerDriver;
    RetrofitConnection connection=RetrofitConnection.getInstance();
    private String allDrives="AllDrivers";
    public DrivesList(List<DriverModel> driverModelList) {
        this.driverModelList=driverModelList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_drives_list, container, false);
        recyclerDriver=view.findViewById(R.id.recyclerDriver);
        recyclerDriver.setLayoutManager(new LinearLayoutManager(getContext()));
        getAllDriveList();
        return view;
    }

    private void setAdapterDriverList() {
        DriverListAdapter adapter=new DriverListAdapter(driverModelList,this);
        recyclerDriver.setAdapter(adapter);
    }
    private void getAllDriveList() {
        Call call=connection.getApiClient().getDrivers();
        connection.callApiResponse(getContext(), call, this, allDrives);
    }
    @Override
    public void onClicked(DriverModel dataModel) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.driverFragement, new EdiDriversFragement(dataModel))
                .commit();
    }
    @Override
    public void resposeResult(String s, Boolean b, String action) throws JSONException {
        if (action.equals(allDrives)){
            JSONObject jsonObject=new JSONObject(s);
            String msg=jsonObject.getString("msg");
            String status=jsonObject.getString("status");
            JSONArray jsonArray=jsonObject.getJSONArray("data");
            driverModelList=new ArrayList<>();
            Type type=new TypeToken<ArrayList<DriverModel>>(){}.getType();
            driverModelList.addAll(new Gson().fromJson(jsonArray.toString(), type));
            Log.d("size", String.valueOf(driverModelList.size()));
            //Toast.makeText(getContext(),driverModelList.size(),Toast.LENGTH_LONG).show();
            setAdapterDriverList();
        }
    }
}