package com.example.myapplication.Activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.List;

public class DetalleCarreraAdapter extends RecyclerView.Adapter<DetalleCarreraAdapter.DetalleCarreraViewHolder> {
    private Context mCtx;
    private List<DetalleCarrera> detalleList;

    public DetalleCarreraAdapter(Context mCtx, List<DetalleCarrera> detalleList) {
        this.mCtx = mCtx;
        this.detalleList = detalleList;
    }

    @Override
    public DetalleCarreraViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.detalle_carrera, null);
        return new DetalleCarreraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetalleCarreraViewHolder holder, int position) {
        DetalleCarrera detalle = detalleList.get(position);

        holder.textViewTitulo.setText(detalle.getTitulo());
        holder.textViewDuracion.setText(detalle.getDuracion());
        holder.textViewSalida.setText(String.valueOf(detalle.getSalida()));
        holder.textViewPerfil.setText(String.valueOf(detalle.getPerfil()));
        holder.textviewRequisitos.setText(detalle.getRequisitos());
        holder.textviewCurso.setText(String.valueOf(detalle.getCurso()));
        holder.textviewPlan.setText(String.valueOf(detalle.getPlanestudio()));
    }

    @Override
    public int getItemCount() {
        return detalleList.size();
    }

    class DetalleCarreraViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitulo, textViewDuracion, textViewSalida, textViewPerfil,textviewRequisitos,textviewCurso,textviewPlan;


        public DetalleCarreraViewHolder(View itemView) {
            super(itemView);

            textViewTitulo = itemView.findViewById(R.id.textViewTitulo);
            textViewDuracion = itemView.findViewById(R.id.textViewDuracion);
            textViewSalida = itemView.findViewById(R.id.textViewSalida);
            textViewPerfil = itemView.findViewById(R.id.textViewPerfil);
            textviewRequisitos=itemView.findViewById(R.id.textViewRequisitos);
            textviewCurso=itemView.findViewById(R.id.textViewCurso);
            textviewPlan=itemView.findViewById(R.id.textViewPlan);
        }
    }
}
