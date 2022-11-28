package com.cursoandoid.starwars.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandoid.starwars.R;
import com.cursoandoid.starwars.model.Starship;

import java.util.List;

public class AdapterSearch extends RecyclerView.Adapter<AdapterSearch.MyViewHolder> {

    private List<Starship> dataList;
    private Context context;

    public AdapterSearch(Context context, List<Starship> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textName;
        TextView text2;
        TextView text3;

        public MyViewHolder(View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.text_name);
            text2 = itemView.findViewById(R.id.text_2);
            text3 = itemView.findViewById(R.id.text_3);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemSearchList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_search, parent, false);
        return new MyViewHolder(itemSearchList);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        // 2 formatos no mesmo textView
        holder.textName.setText(context.getString(R.string.name, dataList.get(position).getName()));
        holder.text2.setText(HtmlCompat.fromHtml(context.getString(R.string.crew, dataList.get(position).getCrew()), HtmlCompat.FROM_HTML_MODE_LEGACY));
        holder.text3.setText(HtmlCompat.fromHtml(context.getString(R.string.passengers, dataList.get(position).getPassengers()), HtmlCompat.FROM_HTML_MODE_LEGACY));

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
