package com.skyview.remidx.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.parser.moshi.JsonReader;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.skyview.remidx.Adapters.DriverListAdapter;
import com.skyview.remidx.R;
import com.skyview.remidx.TruckdetailsAdpter;
import com.skyview.remidx.databinding.FragmentHomeBinding;
import com.skyview.remidx.model_class.DataModel;
import com.skyview.remidx.model_class.DetailsModel;
import com.skyview.remidx.model_class.DriverModel;
import com.skyview.remidx.network_request.Api;
import com.skyview.remidx.network_request.RetrofitConnection;
import com.skyview.remidx.ui.drive.EdiDriversFragement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment implements RetrofitConnection.CallBackRetrofit{

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private TextView totalTruckNumber;
    private List<DetailsModel> detailsModelList;
    private List<DataModel> insurencModelList;
    private List<DataModel> taxModelList;
    private List<DataModel> statePermitModelList;
    private List<DataModel> poluutionModelList;
    private List<DataModel> globlePermitModelList;
    private List<DataModel> fitmitModelList;
    private List<DataModel> driverListForView;
    TabLayout tablayout;
    private List<DriverModel> driverModelList;
    private String allDrives="AllDrivers";
    private String allData="AllVehicalDetails";

    int arr[]={7,10,15,20,45,300,231,8,35,12};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RetrofitConnection retrofitInstant=RetrofitConnection.getInstance();

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView=binding.recycler;
        tablayout=binding.tablayout;
        totalTruckNumber=binding.totalTruckNumber;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // get Data from api
        Call<String> call=retrofitInstant.getApiClient().getAllData();
        retrofitInstant.callApiResponse(getContext(), call, this,allData);
        getAllDriveList();
        //set data to list


        //onTab Change listner
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                    {
                       /* Collections.sort(insurencModelList, new Comparator<DataModel>() {
                            @Override
                            public int compare(DataModel dataModel, DataModel t1) {
                                return dataModel.getRemainDays().compareTo(t1.getRemainDays());
                            }
                        });*/
                        Collections.sort(insurencModelList);
                        TruckdetailsAdpter adpter=new TruckdetailsAdpter(insurencModelList);
                        recyclerView.setAdapter(adpter);
                    }
                    break;
                    case 1:
                    {
                        /*Collections.sort(fitmitModelList, new Comparator<DataModel>() {
                        @Override
                        public int compare(DataModel dataModel, DataModel t1) {
                            return dataModel.getRemainDays().compareTo(t1.getRemainDays());
                                }
                            });*/
                        Collections.sort(fitmitModelList);
                        TruckdetailsAdpter adpter=new TruckdetailsAdpter(fitmitModelList);
                        recyclerView.setAdapter(adpter);
                    }
                    break;
                    case 2:
                    {
                        Collections.sort(globlePermitModelList);
                        TruckdetailsAdpter adpter=new TruckdetailsAdpter(globlePermitModelList);
                        recyclerView.setAdapter(adpter);
                    }
                    break;
                    case 3:
                    {
                        Collections.sort(taxModelList);
                        TruckdetailsAdpter adpter=new TruckdetailsAdpter(taxModelList);
                        recyclerView.setAdapter(adpter);
                    }
                    break;
                    case 4:
                    {
                        Collections.sort(statePermitModelList);
                        TruckdetailsAdpter adpter=new TruckdetailsAdpter(statePermitModelList);
                        recyclerView.setAdapter(adpter);
                    }
                    break;
                    case 5:
                    {
                        Collections.sort(poluutionModelList);
                        TruckdetailsAdpter adpter=new TruckdetailsAdpter(poluutionModelList);
                        recyclerView.setAdapter(adpter);
                    }
                    break;
                    case 6:
                    {
                        Collections.sort(driverListForView);
                        TruckdetailsAdpter adpter=new TruckdetailsAdpter(driverListForView);
                        recyclerView.setAdapter(adpter);
                    }
                    break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void resposeResult(String s, Boolean b,String action) throws JSONException {

        if (action.equals(allData)) {

            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(s);
                if (jsonObject.getString("status").equals("success")) {
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    detailsModelList = new ArrayList<>();
                    Type type = new TypeToken<ArrayList<DetailsModel>>() {
                    }.getType();
                    detailsModelList = new Gson().fromJson(jsonArray.toString(), type);
                    Log.d("data", s.toString());
                    System.out.println("response " + s.toString());
                    setRemainingDays();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
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
            setRemainingForDriverList();
        }

    }

    private void setRemainingForDriverList(){
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDate = df.format(c);
        Log.d("formattedDate", currentDate.toString());
        driverListForView=new ArrayList<>();
        for (int i=0;i<driverModelList.size();i++){
            DataModel model=new DataModel();
            String date=driverModelList.get(i).getDLValidUpto();
            if (date != null) {
                date=date.substring(0, 10);
                String day=Daybetween(currentDate,date,"yyyy-MM-dd");
                Log.d("pul_day",day);
                model.setExpiryOn(date);
                model.setRemainDays(day);
                if (driverModelList.get(i).getDrivingLicense()!=null)
                model.setTruckName(driverModelList.get(i).getDrivingLicense());
                if (driverModelList.get(i).getDriverName()!=null)
                    model.setTruckNumber(driverModelList.get(i).getDriverName());
                driverListForView.add(model);
            }

        }

    }

    private void setRemainingDays() {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDate = df.format(c);
        Log.d("formattedDate", currentDate.toString());

        insurencModelList=new ArrayList<>();
        globlePermitModelList=new ArrayList<>();
        taxModelList=new ArrayList<>();
        poluutionModelList=new ArrayList<>();
        statePermitModelList=new ArrayList<>();
        fitmitModelList=new ArrayList<>();

        for (int i=0;i<detailsModelList.size();i++){
            DataModel model=new DataModel();
            String date=detailsModelList.get(i).getFitnessToDate();
            if (date != null) {
                date=date.substring(0, 10);
                String day=Daybetween(currentDate,date,"yyyy-MM-dd");
                Log.d("fit_day",day);
                model.setExpiryOn(date);
                model.setRemainDays(day);
                model.setTruckName(detailsModelList.get(i).getWhicleOwnerName());
                model.setTruckNumber(detailsModelList.get(i).getWhicleNum());
                fitmitModelList.add(model);
            }
        }

        for (int i=0;i<detailsModelList.size();i++){
            DataModel model=new DataModel();
            String date=detailsModelList.get(i).getInsuranceToDate().trim();

            date=date.substring(0, 10);
            String day=Daybetween(currentDate,date,"yyyy-MM-dd");
            Log.d("insu_day",day);
            model.setExpiryOn(date);
            model.setRemainDays(day);
            model.setDay(Integer.parseInt(day));
            model.setTruckName(detailsModelList.get(i).getWhicleOwnerName());
            model.setTruckNumber(detailsModelList.get(i).getWhicleNum());
            insurencModelList.add(model);
        }

        for (int i=0;i<detailsModelList.size();i++){
            DataModel model=new DataModel();
            String date=detailsModelList.get(i).getTaxTo();
            if (date != null) {
                date=date.substring(0, 10);
                String day=Daybetween(currentDate,date,"yyyy-MM-dd");
                Log.d("date",day);
                model.setExpiryOn(date);
                model.setRemainDays(day);
                model.setTruckName(detailsModelList.get(i).getWhicleOwnerName());
                model.setTruckNumber(detailsModelList.get(i).getWhicleNum());
                taxModelList.add(model);
            }
        }

        for (int i=0;i<detailsModelList.size();i++){
            DataModel model=new DataModel();
            String date=detailsModelList.get(i).getPermitStateToDate();
            if (date != null) {
                date=date.substring(0, 10);
                String day=Daybetween(currentDate,date,"yyyy-MM-dd");
                Log.d("state_day",day);
                model.setExpiryOn(date);
                model.setRemainDays(day);
                model.setTruckName(detailsModelList.get(i).getWhicleOwnerName());
                model.setTruckNumber(detailsModelList.get(i).getWhicleNum());
                statePermitModelList.add(model);
            }

        }

        for (int i=0;i<detailsModelList.size();i++){
            DataModel model=new DataModel();
            Object date1=detailsModelList.get(i).getPermitGlobelToDate();
            if (date1 != null) {
                String date=(date1.toString()).substring(0, 10);
                String day=Daybetween(currentDate,date,"yyyy-MM-dd");
                Log.d("glob_day",day);
                model.setExpiryOn(date);
                model.setRemainDays(day);
                model.setTruckName(detailsModelList.get(i).getWhicleOwnerName());
                model.setTruckNumber(detailsModelList.get(i).getWhicleNum());
                globlePermitModelList.add(model);
            }


        }

        for (int i=0;i<detailsModelList.size();i++){
            DataModel model=new DataModel();
            String date=detailsModelList.get(i).getPUCTo();
            if (date != null) {
                date=date.substring(0, 10);
                String day=Daybetween(currentDate,date,"yyyy-MM-dd");
                Log.d("pul_day",day);
                model.setExpiryOn(date);
                model.setRemainDays(day);
                model.setTruckName(detailsModelList.get(i).getWhicleOwnerName());
                model.setTruckNumber(detailsModelList.get(i).getWhicleNum());
                poluutionModelList.add(model);
            }

        }
        totalTruckNumber.setText(String.valueOf(detailsModelList.size()));
        Collections.sort(insurencModelList);
        TruckdetailsAdpter adpter=new TruckdetailsAdpter(insurencModelList);
        recyclerView.setAdapter(adpter);
    }

    public String Daybetween(String date1,String date2,String pattern)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern,Locale.ENGLISH);
        Date Date1 = null,Date2 = null;
        try{
            Date1 = sdf.parse(date1);
            Date2 = sdf.parse(date2);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return String.valueOf((Date2.getTime() - Date1.getTime())/(24*60*60*1000));
    }

    private void getAllDriveList() {
        Call call=RetrofitConnection.getInstance().getApiClient().getDrivers();
        RetrofitConnection.getInstance().callApiResponse(getContext(), call, this, allDrives);
    }

}