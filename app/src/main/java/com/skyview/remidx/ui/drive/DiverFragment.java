package com.skyview.remidx.ui.drive;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class DiverFragment extends Fragment implements RetrofitConnection.CallBackRetrofit {

    private DriverViewModel driverViewModel;
    private FragmentDriversBinding binding;
    private RecyclerView recyclerDriver;
    RetrofitConnection connection=RetrofitConnection.getInstance();
    private String allDrives="AllDrivers";
    private List<DriverModel> driverModelList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        driverViewModel =
                new ViewModelProvider(this).get(DriverViewModel.class);

        binding = FragmentDriversBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerDriver=binding.recyclerDriver;
        //get All Drive list
        getAllDriveList();

        return root;
    }

    private void getAllDriveList() {
        Call call=connection.getApiClient().getDrivers();
        connection.callApiResponse(getContext(), call, this, allDrives);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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
            driverModelList=new Gson().fromJson(jsonArray.toString(), type);

        }
        Toast.makeText(getContext(),driverModelList.get(5).getDriverName(),Toast.LENGTH_LONG).show();
    }
}