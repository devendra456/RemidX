package com.skyview.remidx.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.skyview.remidx.R;
import com.skyview.remidx.SingleDriverDetails;
import com.skyview.remidx.model_class.DriverModel;
import com.skyview.remidx.network_request.RetrofitConnection;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;

public class DriverListAdapter extends RecyclerView.Adapter<DriverListAdapter.MyViewHolder> implements RetrofitConnection.CallBackRetrofit {
    public DriverListAdapter(List<DriverModel> driverModelList) {
        this.driverModelList = driverModelList;
    }
    private RetrofitConnection connection=RetrofitConnection.getInstance();
    private List<DriverModel> driverModelList;
    int deletepos=-1;
    Context mContext;

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_driver, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        DriverModel model=driverModelList.get(position);
        mContext=holder.itemView.getContext();
        if(model!=null){
            holder.driverName.setText(model.getDriverName());
            holder.driving_liecence.setText(model.getDrivingLicense());
            String dob=model.getDateofbirth().toString().trim();
            dob=dob.substring(0,10);
            holder.dobtext.setText(dob);
            Picasso.get().load(""+model.getPhoto()).error(R.drawable.person)
                    .placeholder(R.drawable.person).into(holder.driverImage);
        }

        holder.editCard.setOnClickListener(View ->{

        });

        holder.deletCard.setOnClickListener(View ->{
            AlertDialog.Builder dialog=new AlertDialog.Builder(holder.itemView.getContext())
                    .setMessage("Are you Sure want to delete this driver");
                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deletepos=position;
                        deleteDrive(model.getID(),position,holder.itemView.getContext());
                    }
                });
                dialog.show();

        });

        holder.itemView.setOnClickListener(View ->{
            Intent intent=new Intent(holder.itemView.getContext(), SingleDriverDetails.class);
            intent.putExtra("data",model);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    private void deleteDrive(Long id, int position, Context context) {
        Call<String> call=connection.getApiClient().deleteDriver(id.toString());
        connection.callApiResponse(context,call,this,"deletedriver");
    }

    @Override
    public int getItemCount() {
        return driverModelList.size();
    }

    @Override
    public void resposeResult(String s, Boolean b, String action) throws JSONException {
        if (s!=null){
            JSONObject jsonObject = new JSONObject(s);
            if(jsonObject.getString("status").equals("true")){
                driverModelList.remove(deletepos);
                notifyDataSetChanged();
                Toast.makeText(mContext, ""+jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(mContext, ""+jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
            }
        }
        Log.d("delete", ""+s);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView driverImage;
        TextView driverName,driving_liecence,dobtext;
        CardView editCard,deletCard;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            driverImage=itemView.findViewById(R.id.driverImage);
            driverName=itemView.findViewById(R.id.driverName);
            driving_liecence=itemView.findViewById(R.id.driving_liecence);
            dobtext=itemView.findViewById(R.id.dobtext);
            deletCard=itemView.findViewById(R.id.cardViewdelete);
            editCard=itemView.findViewById(R.id.cardView2);
        }
    }
}
