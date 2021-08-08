package com.skyview.remidx.ui.vehicle.fragments.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.skyview.remidx.R;
import com.skyview.remidx.model_class.DetailsModel;
import com.skyview.remidx.network_request.RetrofitConnection;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;

public class VehicleListAdapter extends RecyclerView.Adapter<VehicleListAdapter.VehicleListViewHolder> implements RetrofitConnection.CallBackRetrofit {

    private Context context;
    private List<DetailsModel> detailsModelList;
    private int itemClickedPosition;

    public VehicleListAdapter(Context context, List<DetailsModel> detailsModelList) {
        this.context = context;
        this.detailsModelList = detailsModelList;
    }

    @NonNull
    @Override
    public VehicleListAdapter.VehicleListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.single_item_vehicle_list,parent,false);
        return new VehicleListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleListAdapter.VehicleListViewHolder holder, int position) {
        holder.vehicleNumberTextView.setText(detailsModelList.get(position).getWhicleNum().toString());
        holder.ownerNameTextView.setText(detailsModelList.get(position).getWhicleOwnerName().toString());
        RetrofitConnection retrofitConnection=RetrofitConnection.getInstance();
        holder.deleteCardView.setOnClickListener(v -> {
            itemClickedPosition=position;
            Call call=retrofitConnection.getApiClient().deleteData(detailsModelList.get(position).getID().toString());
            retrofitConnection.callApiResponse(context,call,this,"DELETE");
        });
        holder.editCardView.setOnClickListener(v -> {

        });
    }

    @Override
    public int getItemCount() {
        return detailsModelList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void resposeResult(String s, Boolean b, String action) throws JSONException {
        JSONObject jsonObject=new JSONObject(s);
        if(jsonObject.getString("status").equals("true")){
            Log.d("resposeResult", "resposeResult: "+s);
            notifyDataSetChanged();
            detailsModelList.remove(itemClickedPosition);
        }
    }

    public static class VehicleListViewHolder extends RecyclerView.ViewHolder {
        private final TextView vehicleNumberTextView;
        private final TextView ownerNameTextView;
        private final CardView deleteCardView;
        private final CardView editCardView;
        public VehicleListViewHolder(@NonNull View itemView) {
            super(itemView);
            vehicleNumberTextView=itemView.findViewById(R.id.vehicleNumberTextView);
            ownerNameTextView=itemView.findViewById(R.id.ownerNameTextView);
            deleteCardView=itemView.findViewById(R.id.deleteCardView);
            editCardView=itemView.findViewById(R.id.editCardView);
        }
    }
}
