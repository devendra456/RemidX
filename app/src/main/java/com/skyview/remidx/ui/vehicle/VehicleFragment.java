package com.skyview.remidx.ui.vehicle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.skyview.remidx.R;
import com.skyview.remidx.ui.vehicle.fragments.AddVehicleFragment;
import com.skyview.remidx.ui.vehicle.fragments.VehicleListFragment;

public class VehicleFragment extends Fragment {

    private LinearLayout addVehicleLayout;
    private LinearLayout vehicleListLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vehical, container, false);
        addVehicleLayout = view.findViewById(R.id.addVehicleLayout);
        vehicleListLayout = view.findViewById(R.id.vehicleListLayout);
        setAddVehicleFragment();
        switchTabs();
        return view;
    }

    private void switchTabs() {
        addVehicleLayout.setOnClickListener(v -> {
            vehicleListLayout.setSelected(false);
            addVehicleLayout.setSelected(true);
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.vehicleDetailsFrameLayout,
                            new AddVehicleFragment()).commit();
        });
        vehicleListLayout.setOnClickListener(v -> {
            vehicleListLayout.setSelected(true);
            addVehicleLayout.setSelected(false);
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.vehicleDetailsFrameLayout,
                            new VehicleListFragment()).commit();
        });
    }

    private void setAddVehicleFragment() {
        addVehicleLayout.setSelected(true);
        FragmentManager fragmentTransaction=getActivity().getSupportFragmentManager();
        fragmentTransaction.beginTransaction().add(R.id.vehicleDetailsFrameLayout, new AddVehicleFragment(),"home").commit();

    }
}