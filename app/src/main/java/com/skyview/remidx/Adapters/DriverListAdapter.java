package com.skyview.remidx.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.skyview.remidx.R;
import com.skyview.remidx.SingleDriverDetails;
import com.skyview.remidx.model_class.DriverModel;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

public class DriverListAdapter extends RecyclerView.Adapter<DriverListAdapter.MyViewHolder> {
    public DriverListAdapter(List<DriverModel> driverModelList) {
        this.driverModelList = driverModelList;
    }

    private List<DriverModel> driverModelList;

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

        });

        holder.itemView.setOnClickListener(View ->{
            Intent intent=new Intent(holder.itemView.getContext(), SingleDriverDetails.class);
            intent.putExtra("data",model);
            holder.itemView.getContext().startActivity(intent);
        });
    }
    @Override
    public int getItemCount() {
        return driverModelList.size();
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
