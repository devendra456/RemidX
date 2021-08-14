package com.skyview.remidx;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skyview.remidx.model_class.DataModel;
import com.skyview.remidx.model_class.DetailsModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TruckdetailsAdpter extends RecyclerView.Adapter<TruckdetailsAdpter.MyViewHolder>{
    List<DataModel> list;
    String seletect;
    public TruckdetailsAdpter(List<DataModel> list) {
        this.list=list;
        this.seletect=seletect;
    }

    int leftDays[];
    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_truckdetails, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        DataModel model=list.get(position);
        int days=Integer.parseInt(model.getRemainDays());
            holder.truckNumber.setText(model.getTruckNumber());
            holder.leftDaysText.setText(model.getRemainDays());
            holder.expiryDate.setText(model.getExpiryOn());
            if (days < 10 && days>=0) {
                holder.leftDaysText.setText("" + days + " Days Left");
                holder.leftDaysText.setTextColor(Color.BLACK);
                holder.leftDaysText.setBackgroundResource(R.drawable.red_bg_light);
            } else if (days > 10 && days < 30) {
                holder.leftDaysText.setText("" + days + " Days Left");
                holder.leftDaysText.setTextColor(Color.BLACK);
                holder.leftDaysText.setBackgroundResource(R.drawable.yellow_bg);
            }
            else if(days<0){
                holder.leftDaysText.setText("Expired");
                holder.leftDaysText.setTextColor(Color.WHITE);
                holder.leftDaysText.setBackgroundResource(R.drawable.red_bg);
            }
            else {
                holder.leftDaysText.setTextColor(Color.BLACK);
                holder.leftDaysText.setText("" + days + " Days Left");
                holder.leftDaysText.setBackgroundResource(R.drawable.green_bg);
            }

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView leftDaysText,truckNumber,truckName,expiryDate;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            leftDaysText=itemView.findViewById(R.id.remainingDay);
            truckNumber=itemView.findViewById(R.id.truck_Number);
            truckName=itemView.findViewById(R.id.truckName);
            expiryDate=itemView.findViewById(R.id.expiryDate);
        }
    }
}
