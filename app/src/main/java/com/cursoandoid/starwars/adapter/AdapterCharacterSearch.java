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
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandoid.starwars.R;
import com.cursoandoid.starwars.model.Character;

import java.util.List;

public class AdapterCharacterSearch extends RecyclerView.Adapter<AdapterCharacterSearch.MyViewHolder> {

    private List<Character> dataList;
    private Context context;

    public AdapterCharacterSearch(Context context, List<Character> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textName;
        TextView text2;
        TextView text3;

        ConstraintLayout cl;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.text_name);
            text2 = itemView.findViewById(R.id.text_2);
            text3 = itemView.findViewById(R.id.text_3);

            cl = itemView.findViewById(R.id.cl_image);
            image = itemView.findViewById(R.id.icon_in_circle);

        }
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

        // 2 formatos no mesmo textView
        holder.textName.setText(HtmlCompat.fromHtml(context.getString(R.string.name, dataList.get(position).getName()), HtmlCompat.FROM_HTML_MODE_LEGACY));
        holder.text2.setText(HtmlCompat.fromHtml(context.getString(R.string.height, dataList.get(position).getHeight()), HtmlCompat.FROM_HTML_MODE_COMPACT));
        holder.text3.setText(HtmlCompat.fromHtml(context.getString(R.string.eyes_color, dataList.get(position).getEyeColor()), HtmlCompat.FROM_HTML_MODE_LEGACY));

        // Change elements on view, wich is xml adapter_default_search
        holder.cl.setBackground(ContextCompat.getDrawable(context, R.drawable.icon_circle_person));
        holder.image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.person));

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
