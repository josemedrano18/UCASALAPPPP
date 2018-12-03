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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders>
{
 private List<ItemObject> itemList;
 private Context context;
 public RecyclerViewAdapter(Context context, List<ItemObject> itemList)
 {
     this.itemList = itemList;
     this.context = context;
 }
 @Override
 public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType)
 {

     View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,null);
     RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
     return rcv;
 }

 @Override
 public void onBindViewHolder(final RecyclerViewHolders holder, final int position) {


     holder.Eje.setText(itemList.get(position).getEje());
     animate(holder);
     holder.cv.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
          //   Toast.makeText(context, "Clickeaste "+ itemList.get(position).getEje(), Toast.LENGTH_LONG).show();
             Context context = v.getContext();
             Intent intent = new Intent(context, Carreras.class);
             intent.putExtra("EJE_TEMATICO", itemList.get(position).getEje());
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





