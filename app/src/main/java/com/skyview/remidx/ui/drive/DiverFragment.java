package com.skyview.remidx.ui.drive;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.skyview.remidx.R;
import com.skyview.remidx.databinding.FragmentDriversBinding;
import com.skyview.remidx.model_class.DataModel;
import com.skyview.remidx.model_class.DriverModel;
import com.skyview.remidx.network_request.RetrofitConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.internal.TypeReference;
import retrofit2.Call;
import retrofit2.Retrofit;

public class DiverFragment extends Fragment {

    private DriverViewModel driverViewModel;
    private FragmentDriversBinding binding;
    RetrofitConnection connection=RetrofitConnection.getInstance();
    private String allDrives="AllDrivers";
    private List<DriverModel> driverModelList;
    LinearLayout driverlistlayout,editdriverlayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        driverViewModel =
                new ViewModelProvider(this).get(DriverViewModel.class);

        binding = FragmentDriversBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        driverlistlayout=binding.DriverListLayout;
        editdriverlayout=binding.EditDriverLayout;
        driverlistlayout.setSelected(true);

        //get All Drive list
        setFragement();

        switchTab();

        return root;
    }

    private void switchTab() {
        driverlistlayout.setOnClickListener(View ->{
            driverlistlayout.setSelected(true);
            editdriverlayout.setSelected(false);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.driverFragement, new DrivesList(driverModelList)).commit();
        });

        editdriverlayout.setOnClickListener(View ->{
            driverlistlayout.setSelected(false);
            editdriverlayout.setSelected(true);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.driverFragement, new EdiDriversFragement())
                    .commit();
        });
    }

    private void setFragement() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.driverFragement, new DrivesList(driverModelList))
                .commit();
    }

  /*  private void getAllDriveList() {
        Call call=connection.getApiClient().getDrivers();
        connection.callApiResponse(getContext(), call, this, allDrives);
    }
*/
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

 /*   @Override
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
            setFragement();
        }
    }*/
}