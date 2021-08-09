package com.skyview.remidx.ui.vehicle.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.skyview.remidx.R;
import com.skyview.remidx.model_class.DetailsModel;
import com.skyview.remidx.network_request.RetrofitConnection;
import com.skyview.remidx.ui.vehicle.fragments.adapter.VehicleListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class VehicleListFragment extends Fragment implements RetrofitConnection.CallBackRetrofit, VehicleListAdapter.VehcalClickListner {

    private RecyclerView vehicleRecyclerView;
    private ArrayList<DetailsModel> detailsModelArrayList=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view= inflater.inflate(R.layout.fragment_vehicle_list, container, false);
        vehicleRecyclerView=view.findViewById(R.id.vehicleListRecyclerView);

        getVehicleList();

        return view;
    }

    private void getVehicleList() {
        RetrofitConnection retrofitConnection=RetrofitConnection.getInstance();
        Call<String> call=retrofitConnection.getApiClient().getAllData();
        retrofitConnection.callApiResponse(getContext(),call,this,"VEHICLE_LIST");
    }

    @Override
    public void resposeResult(String s, Boolean b, String action) throws JSONException {
        if (s != null) {
            JSONObject jsonObject=new JSONObject(s);
            if(jsonObject.getString("status").equals("success")){
                JSONArray jsonArray= jsonObject.getJSONArray("data");
                Type type= new TypeToken<ArrayList<DetailsModel>>(){}.getType();
                detailsModelArrayList.clear();
                detailsModelArrayList=new Gson().fromJson(jsonArray.toString(),type);
                setDataOnRecyclerView();
            }
        }

    }

    private void setDataOnRecyclerView() {
        vehicleRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        VehicleListAdapter vehicleListAdapter=new VehicleListAdapter(getContext(),detailsModelArrayList,this);
        vehicleRecyclerView.setAdapter(vehicleListAdapter);
    }

    @Override
    public void OnSelectedVehical(DetailsModel detailsModel) {

        AddVehicleFragment addVehicleFragment=new AddVehicleFragment(detailsModel);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.vehicleDetailsFrameLayout,addVehicleFragment).commit();
    }
}