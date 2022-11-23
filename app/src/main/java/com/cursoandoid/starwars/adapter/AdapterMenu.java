package com.cursoandoid.starwars.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandoid.starwars.R;
import com.cursoandoid.starwars.model.Menu;

import java.util.List;

public class AdapterMenu extends RecyclerView.Adapter<AdapterMenu.MyViewHolder> {

    private List<Menu> listMenu;
    private Context context;
    private String listType;
    private ItemMenuHomeClickListener listener;

    public AdapterMenu(List<Menu> list, Context context, String listType, ItemMenuHomeClickListener listener) {
        this.listMenu = list;
        this.context = context;
        this.listType = listType;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        if(listType == list) {
//            View itemMenuList = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.adapter_menu_list, parent, false);
//            return new MyViewHolder(itemMenuList);

        //} else(listType == grid){
            View itemMenuList = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.adapter_menu_grid, parent, false);
            return new MyViewHolder(itemMenuList);
        //}
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Menu menu = listMenu.get(position);
        holder.text.setText(menu.getText());
        holder.image.setImageDrawable(menu.getImage());
        if (menu.getText().equals("Busca ALEATÓRIA")) {
            holder.color.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_home_special));
        }
    }

    @Override
    public int getItemCount() {
        return listMenu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView text;
        View color;
        LinearLayout root;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.text);
            color = itemView.findViewById(R.id.cl_background);
            root = itemView.findViewById(R.id.ll_main);
            root.setOnClickListener(view -> listener.onClickItem(listMenu.get(getAdapterPosition())));
        }
    }

    public interface ItemMenuHomeClickListener{
        void onClickItem(Menu menu);
    }

}
