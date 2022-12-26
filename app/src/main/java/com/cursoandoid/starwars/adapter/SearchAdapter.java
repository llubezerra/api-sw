package com.cursoandoid.starwars.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandoid.starwars.R;
import com.cursoandoid.starwars.model.SwapiObject;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    private List<SwapiObject> dataList;
    private Context context;

    public SearchAdapter(List<SwapiObject> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemSearchList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_default_search, parent, false);
        return new MyViewHolder(itemSearchList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SwapiObject model = dataList.get(position);

        holder.cl.setBackground(ContextCompat.getDrawable(context, model.getBgRes()));
        holder.image.setImageDrawable(ContextCompat.getDrawable(context, model.getIconRes()));

        holder.text1.setText(model.getString1());
        holder.text2.setText(model.getString2());
        holder.text3.setText(model.getString3());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text1;
        TextView text2;
        TextView text3;

        ConstraintLayout cl;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);

            text1 = itemView.findViewById(R.id.text_name);
            text2 = itemView.findViewById(R.id.text_2);
            text3 = itemView.findViewById(R.id.text_3);

            cl = itemView.findViewById(R.id.cl_image);
            image = itemView.findViewById(R.id.icon_in_circle);
        }
    }
}
