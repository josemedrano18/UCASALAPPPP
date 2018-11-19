package com.example.myapplication.Activities;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;

public class RecyclerViewHoldersCarreras extends RecyclerView.ViewHolder {
    public TextView NomCarrera;
    public RelativeLayout relativeLayout;
    public RecyclerViewHoldersCarreras(View itemView)
    {
        super(itemView);
        NomCarrera=(TextView)itemView.findViewById(R.id.nomcarrera);
        relativeLayout= (RelativeLayout) itemView.findViewById(R.id.RelativeLayoutCarreras);
    }

}
