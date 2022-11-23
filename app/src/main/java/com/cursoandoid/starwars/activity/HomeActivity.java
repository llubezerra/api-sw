package com.cursoandoid.starwars.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;

import com.cursoandoid.starwars.R;
import com.cursoandoid.starwars.adapter.AdapterMenu;
import com.cursoandoid.starwars.databinding.ActivityHomeBinding;
import com.cursoandoid.starwars.model.Menu;
import com.cursoandoid.starwars.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements AdapterMenu.ItemMenuHomeClickListener {
    private ActivityHomeBinding binding;
    private HomeViewModel viewModel;
    private List<Menu> listMenu = new ArrayList<>();
    private String list, grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(Color.BLACK);

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        //Listagem de itens
        this.criarMenu();

        recyclerViewGrid();

    }

    private void recyclerViewGrid(){
        //Configurar adapter
        AdapterMenu adapter = new AdapterMenu(listMenu, this, grid, this);

        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManagerGrid = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        binding.recyclerMenuList.setLayoutManager(layoutManagerGrid);
        binding.recyclerMenuList.setAdapter(adapter);
    }

    private void recyclerViewList(){
        //Configurar adapter
        AdapterMenu adapter = new AdapterMenu(listMenu, this, list, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        binding.recyclerMenuList.setLayoutManager(layoutManager);
        binding.recyclerMenuList.setAdapter(adapter);
    }

    private void criarMenu() {

        Menu menu = new Menu(ContextCompat.getDrawable(this, R.drawable.starship), getString(R.string.search_spaceships));
        this.listMenu.add(menu);

        menu = new Menu(ContextCompat.getDrawable(this, R.drawable.planet), getString(R.string.search_planets));
        this.listMenu.add(menu);

        menu = new Menu(ContextCompat.getDrawable(this, R.drawable.luke), getString(R.string.search_characters));
        this.listMenu.add(menu);

        menu = new Menu(ContextCompat.getDrawable(this, R.drawable.darthvader), getString(R.string.search_random));
        this.listMenu.add(menu);

        menu = new Menu(ContextCompat.getDrawable(this, R.drawable.ic_icon_ionic_md_settings), getString(R.string.settings));
        this.listMenu.add(menu);

        menu = new Menu(ContextCompat.getDrawable(this, R.drawable.ic_icon_metro_music), getString(R.string.sound));
        this.listMenu.add(menu);

    }

    //Evento de click
    @Override
    public void onClickItem(Menu menu) {
       viewModel.openSearch(menu, this);
    }
}