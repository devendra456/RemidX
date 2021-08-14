package com.skyview.remidx.network_request;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.skyview.remidx.R;

class ProgressBarDialog {
    public ProgressBarDialog(Context context) {
        this.context = context;
    }
    public void show(){
        if (dialog==null){
            dialog=new Dialog(context);
            dialog.setContentView(R.layout.progress_layout);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }
    }
    public void hide(){
       if (dialog!=null){
           dialog.dismiss();
       }
    }
    private Dialog dialog=null;
    private Context context;
}
