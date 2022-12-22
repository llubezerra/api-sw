package com.cursoandoid.starwars.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandoid.starwars.R;
import com.cursoandoid.starwars.adapter.AdapterMenu;
import com.cursoandoid.starwars.databinding.ActivityHomeBinding;
import com.cursoandoid.starwars.model.Menu;
import com.cursoandoid.starwars.viewmodel.HomeViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity implements AdapterMenu.ItemMenuHomeClickListener {
    private ActivityHomeBinding binding;
    private HomeViewModel viewModel;
    private List<Menu> listMenu = new ArrayList<>();
    private String menuType;

    private MediaPlayer mediaPlayer;

    BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Show all layout in the right language
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        setLanguage();

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(Color.BLACK);

        //Listagem de itens
        this.criarMenu();

    }

    @Override
    protected void onStart() {
        super.onStart();
        menuTypeList();
    }

    //Quando voltamos da Activity seguinte estaremos no onStop(), daí caímos no onRestart() e como não precisamos voltar com o dialog damos o dismiss
    @Override
    protected void onRestart() {
        super.onRestart();
        if(bottomSheetDialog != null){
            bottomSheetDialog.dismiss();
        }
    }

    private void menuTypeList(){
        if(viewModel.listType(this)){
            menuType = "list";
            recyclerViewList();
        }else if(viewModel.listType(this)){
            menuType = "grid";
            recyclerViewGrid();
        }else{
            menuType = "grid";
            recyclerViewGrid();
        }
    }

    private void recyclerViewGrid(){
        //Configurar adapter
        AdapterMenu adapter = new AdapterMenu(listMenu, this, menuType, this);

        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManagerGrid = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        binding.recyclerMenuList.setLayoutManager(layoutManagerGrid);
        binding.recyclerMenuList.setAdapter(adapter);
    }

    private void recyclerViewList(){
        //Configurar adapter
        AdapterMenu adapter = new AdapterMenu(listMenu, this, menuType, this);

        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        binding.recyclerMenuList.setLayoutManager(layoutManager);
        binding.recyclerMenuList.setAdapter(adapter);
    }

    private void setLanguage(){
        String language = viewModel.language(this);

        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration config = getResources().getConfiguration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
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
        if(viewModel.getShouldShowDialog(menu, this)) {
            showBottomSheetChooseDialog(menu);
        } else if(viewModel.getShouldShowMusic(menu, this)){
            showBottomSheetMusicDialog(menu);
        } else{
            viewModel.apiCallType(menu, this);
        }
    }

    private void showBottomSheetChooseDialog(Menu menu) {

        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_choose_layout);
        bottomSheetDialog.show();

        Button searchAll = bottomSheetDialog.findViewById(R.id.buttom_all);
        Button searchByName = bottomSheetDialog.findViewById(R.id.buttom_name);

        searchAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.setSearchType("all");
                viewModel.apiCallType(menu, HomeActivity.this);
            }
        });

        searchByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.setSearchType("byName");
                viewModel.apiCallType(menu, HomeActivity.this);
            }
        });

    }

    private void showBottomSheetMusicDialog(Menu menu) {

        BottomSheetDialog musicDialog;
        musicDialog = new BottomSheetDialog(this);
        musicDialog.setContentView(R.layout.bottom_sheet_music_layout);
        musicDialog.show();

        //Toca a música
        mediaPlayer = MediaPlayer.create(this, R.raw.imperial_march);
        if (mediaPlayer != null) {
            mediaPlayer.start();

            //Configura o audio manager
            AudioManager audioManager;
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

            //Recupera o volume máximo
            int volumeMaximo = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

            //Seta o volume máximo na execução
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volumeMaximo, 0);

        }

        ConstraintLayout stop = musicDialog.findViewById(R.id.buttom_stop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    musicDialog.dismiss();
                }
            }
        });
    }

}