package com.example.myapplication.Activities;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;

public class RecyclerViewHolders extends RecyclerView.ViewHolder {
    public TextView Eje;
    public RelativeLayout relativeLayout;
    public RecyclerViewHolders(View itemView)
    {
        super(itemView);
        Eje=(TextView)itemView.findViewById(R.id.eje);
        relativeLayout= (RelativeLayout) itemView.findViewById(R.id.RelativeLayout);
    }

}
