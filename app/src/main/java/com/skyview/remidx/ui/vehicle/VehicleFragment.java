package com.skyview.remidx.ui.vehicle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.skyview.remidx.R;
import com.skyview.remidx.databinding.FragmentVehicalBinding;

public class VehicleFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_vehical,container,false);
        LinearLayout addVehicleLayout=view.findViewById(R.id.addVehicleLayout);
        LinearLayout vehicleListLayout=view.findViewById(R.id.vehicleListLayout);

        addVehicleLayout.setSelected(true);

        addVehicleLayout.setOnClickListener(v -> {
            vehicleListLayout.setSelected(false);
            addVehicleLayout.setSelected(true);
        });
        vehicleListLayout.setOnClickListener(v -> {
            vehicleListLayout.setSelected(true);
            addVehicleLayout.setSelected(false);
        });
        return view;
    }
}