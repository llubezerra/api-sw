package com.cursoandoid.starwars.activity;

import static com.cursoandoid.starwars.Constants.TYPE_PREFERENCES;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.cursoandoid.starwars.Constants;
import com.cursoandoid.starwars.R;
import com.cursoandoid.starwars.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {
    private ActivitySettingsBinding binding;

    private String listType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(Color.BLACK);

        setupNavigation();
    }

    @Override
    protected void onStart() {
        super.onStart();
        sharedPreferencesListOrGrid();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void setupNavigation() {
        binding.arrow.setOnClickListener(v -> onBackPressed());
        binding.icon.setColorFilter(ContextCompat.getColor(this, R.color.light_gray));

        binding.clGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MODE 0 -> PRIVADO: SÓ O NOSSO APP CONSEGUIRÁ SALVAR E LER ESSE ARQUIVO
                SharedPreferences preferences = getSharedPreferences(Constants.TYPE_PREFERENCES, 0);
                SharedPreferences.Editor editor = preferences.edit();

                listType = "grid";
                editor.putString("list_type", listType);
                editor.commit();

                binding.circleGrid.setImageDrawable(ContextCompat.getDrawable(SettingsActivity.this, R.drawable.circle_orange));
                binding.textGrid.setTextColor(ContextCompat.getColor(SettingsActivity.this, R.color.orange));

                binding.circleList.setImageDrawable(ContextCompat.getDrawable(SettingsActivity.this, R.drawable.circle_white));
                binding.textList.setTextColor(ContextCompat.getColor(SettingsActivity.this, R.color.light_gray));
            }
        });

        binding.clList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MODE 0 -> PRIVADO: SÓ O NOSSO APP CONSEGUIRÁ SALVAR E LER ESSE ARQUIVO
                SharedPreferences preferences = getSharedPreferences(Constants.TYPE_PREFERENCES, 0);
                SharedPreferences.Editor editor = preferences.edit();

                listType = "list";
                editor.putString("list_type", listType);
                editor.commit();

                binding.circleGrid.setImageDrawable(ContextCompat.getDrawable(SettingsActivity.this, R.drawable.circle_white));
                binding.textGrid.setTextColor(ContextCompat.getColor(SettingsActivity.this, R.color.light_gray));

                binding.circleList.setImageDrawable(ContextCompat.getDrawable(SettingsActivity.this, R.drawable.circle_orange));
                binding.textList.setTextColor(ContextCompat.getColor(SettingsActivity.this, R.color.orange));
            }
        });

        binding.clPortuguese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.clEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.clRussian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void sharedPreferencesListOrGrid() {
        //Recuperar dados salvos
        SharedPreferences preferences = getSharedPreferences(TYPE_PREFERENCES, 0);

        //Valida se temos o nome em preferências
        String type = preferences.getString("list_type", "");

        if(type.equals("list")){
            binding.circleGrid.setImageDrawable(ContextCompat.getDrawable(SettingsActivity.this, R.drawable.circle_white));
            binding.textGrid.setTextColor(ContextCompat.getColor(SettingsActivity.this, R.color.light_gray));

            binding.circleList.setImageDrawable(ContextCompat.getDrawable(SettingsActivity.this, R.drawable.circle_orange));
            binding.textList.setTextColor(ContextCompat.getColor(SettingsActivity.this, R.color.orange));
        } else if(type.equals("grid")) {
            binding.circleGrid.setImageDrawable(ContextCompat.getDrawable(SettingsActivity.this, R.drawable.circle_orange));
            binding.textGrid.setTextColor(ContextCompat.getColor(SettingsActivity.this, R.color.orange));

            binding.circleList.setImageDrawable(ContextCompat.getDrawable(SettingsActivity.this, R.drawable.circle_white));
            binding.textList.setTextColor(ContextCompat.getColor(SettingsActivity.this, R.color.light_gray));
        }

    }

}