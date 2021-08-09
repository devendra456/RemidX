package com.skyview.remidx.ui.vehicle.fragments.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.skyview.remidx.R;
import com.skyview.remidx.VehicleDetailsActivity;
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
    private RetrofitConnection retrofitConnection;
    private VehcalClickListner vehcalClickListner;

    public VehicleListAdapter(Context context, List<DetailsModel> detailsModelList,VehcalClickListner vehcalClickListner) {
        this.context = context;
        this.detailsModelList = detailsModelList;
        this.vehcalClickListner=vehcalClickListner;
    }

    @NonNull
    @Override
    public VehicleListAdapter.VehicleListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.single_item_vehicle_list,parent,false);
        return new VehicleListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleListAdapter.VehicleListViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.vehicleNumberTextView.setText(detailsModelList.get(position).getWhicleNum());
        holder.ownerNameTextView.setText(detailsModelList.get(position).getWhicleOwnerName());
        retrofitConnection = RetrofitConnection.getInstance();
        holder.deleteCardView.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setCancelable(false)
                    .setMessage("Are you sure want to delete?")
                    .setNegativeButton("Cancel", (dialog12, which) -> dialog12.dismiss())
                    .setPositiveButton("Delete", (dialog1, which) -> delete(position)).show();

        });
        holder.editCardView.setOnClickListener(v -> {
            vehcalClickListner.OnSelectedVehical(detailsModelList.get(position));
        });
        holder.itemView.setOnClickListener(v -> context.startActivity(new Intent(context, VehicleDetailsActivity.class).putExtra("list",detailsModelList.get(position))));
    }

    private void delete(int position) {
        itemClickedPosition=position;
        Call call=retrofitConnection.getApiClient().deleteData(detailsModelList.get(position).getID().toString());
        retrofitConnection.callApiResponse(context,call,this,"DELETE");
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
    public interface VehcalClickListner{
        public void OnSelectedVehical(DetailsModel detailsModel);
    }
}
