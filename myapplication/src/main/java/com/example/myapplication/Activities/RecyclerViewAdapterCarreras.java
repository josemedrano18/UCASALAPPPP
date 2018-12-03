package com.example.myapplication.Activities;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.myapplication.R;

import java.util.List;

public class RecyclerViewAdapterCarreras extends RecyclerView.Adapter<RecyclerViewHoldersCarreras>
{
 private List<ItemObjectCarreras> itemList;
 private Context context;
 public RecyclerViewAdapterCarreras(Context context, List<ItemObjectCarreras> itemList)
 {
     this.itemList = itemList;
     this.context = context;
 }
 @Override
 public RecyclerViewHoldersCarreras onCreateViewHolder(ViewGroup parent, int viewType)
 {

     View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_itemcarreras,null);
     RecyclerViewHoldersCarreras rcvc = new RecyclerViewHoldersCarreras(layoutView);
     return rcvc;
 }

 @Override
 public void onBindViewHolder(final RecyclerViewHoldersCarreras holder, final int position) {
     animate(holder);
     holder.NomCarrera.setText(itemList.get(position).getNomCarrera());
     holder.cv.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
          //   Toast.makeText(context, "Clickeaste "+ itemList.get(position).getNomCarrera(), Toast.LENGTH_LONG).show();
             Context context = v.getContext();
             Intent intent = new Intent(context, MostrarDetalleCarrera.class);
             intent.putExtra("NOM_CARRERA", itemList.get(position).getNomCarrera());
             context.startActivity(intent);

         }
     });
 }

 @Override
 public int getItemCount() {
  return this.itemList.size();
 }
 public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.anticipate_overshoot_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }

}



