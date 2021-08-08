package com.skyview.remidx.ui.drive;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skyview.remidx.Adapters.DriverListAdapter;
import com.skyview.remidx.R;
import com.skyview.remidx.model_class.DriverModel;

import java.util.List;

public class DrivesList extends Fragment {
    private List<DriverModel> driverModelList;
    private RecyclerView recyclerDriver;

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
        setAdapterDriverList();
        return view;
    }

    private void setAdapterDriverList() {
        DriverListAdapter adapter=new DriverListAdapter(driverModelList);
        recyclerDriver.setAdapter(adapter);
    }
}