package com.cursoandoid.starwars.activity;

import static com.cursoandoid.starwars.Constants.LANGUAGE_PREFERENCES;
import static com.cursoandoid.starwars.Constants.TYPE_PREFERENCES;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.cursoandoid.starwars.Constants;
import com.cursoandoid.starwars.R;
import com.cursoandoid.starwars.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {
    private ActivitySettingsBinding binding;

    private String listType;
    private String language;

    SharedPreferences preferences;

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
        sharedPreferencesLanguage();
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
                preferences = getSharedPreferences(Constants.TYPE_PREFERENCES, 0);
                SharedPreferences.Editor editor = preferences.edit();

                listType = "grid";
                editor.putString("list_type", listType);
                editor.apply();

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
                preferences = getSharedPreferences(Constants.TYPE_PREFERENCES, 0);
                SharedPreferences.Editor editor = preferences.edit();

                listType = "list";
                editor.putString("list_type", listType);
                editor.apply();

                binding.circleGrid.setImageDrawable(ContextCompat.getDrawable(SettingsActivity.this, R.drawable.circle_white));
                binding.textGrid.setTextColor(ContextCompat.getColor(SettingsActivity.this, R.color.light_gray));

                binding.circleList.setImageDrawable(ContextCompat.getDrawable(SettingsActivity.this, R.drawable.circle_orange));
                binding.textList.setTextColor(ContextCompat.getColor(SettingsActivity.this, R.color.orange));
            }
        });

        binding.clPortuguese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences = getSharedPreferences(LANGUAGE_PREFERENCES, 0);
                SharedPreferences.Editor editor = preferences.edit();

                language = "pt";
                setPortuguese();

                editor.putString("language_preferences", language);
                editor.apply();

                Toast.makeText(SettingsActivity.this, getString(R.string.restart), Toast.LENGTH_SHORT).show();
            }
        });

        binding.clEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences = getSharedPreferences(LANGUAGE_PREFERENCES, 0);
                SharedPreferences.Editor editor = preferences.edit();

                language = "en";
                setEnglish();

                editor.putString("language_preferences", language);
                editor.apply();

                Toast.makeText(SettingsActivity.this, getString(R.string.restart), Toast.LENGTH_SHORT).show();
            }
        });

        binding.clRussian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences = getSharedPreferences(LANGUAGE_PREFERENCES, 0);
                SharedPreferences.Editor editor = preferences.edit();

                language = "ru";
                setRussian();

                editor.putString("language_preferences", language);
                editor.apply();

                Toast.makeText(SettingsActivity.this, getString(R.string.restart), Toast.LENGTH_SHORT).show();
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

    private void sharedPreferencesLanguage() {
        //Recuperar dados salvos
        SharedPreferences preferences = getSharedPreferences(LANGUAGE_PREFERENCES, 0);

        //Valida se temos o nome em preferências
        String type = preferences.getString("language_preferences", "");

        switch (type) {
            case "pt":
                setPortuguese();
                break;
            case "en":
                setEnglish();
                break;
            case "ru":
                setRussian();
                break;
        }

    }

    private void setPortuguese() {
        binding.circlePortuguese.setImageDrawable(ContextCompat.getDrawable(SettingsActivity.this, R.drawable.circle_orange));
        binding.textPortuguese.setTextColor(ContextCompat.getColor(SettingsActivity.this, R.color.orange));

        binding.circleEnglish.setImageDrawable(ContextCompat.getDrawable(SettingsActivity.this, R.drawable.circle_white));
        binding.textEnglish.setTextColor(ContextCompat.getColor(SettingsActivity.this, R.color.light_gray));

        binding.circleRussian.setImageDrawable(ContextCompat.getDrawable(SettingsActivity.this, R.drawable.circle_white));
        binding.textRussian.setTextColor(ContextCompat.getColor(SettingsActivity.this, R.color.light_gray));
    }

    private void setEnglish() {
        binding.circleEnglish.setImageDrawable(ContextCompat.getDrawable(SettingsActivity.this, R.drawable.circle_orange));
        binding.textEnglish.setTextColor(ContextCompat.getColor(SettingsActivity.this, R.color.orange));

        binding.circlePortuguese.setImageDrawable(ContextCompat.getDrawable(SettingsActivity.this, R.drawable.circle_white));
        binding.textPortuguese.setTextColor(ContextCompat.getColor(SettingsActivity.this, R.color.light_gray));

        binding.circleRussian.setImageDrawable(ContextCompat.getDrawable(SettingsActivity.this, R.drawable.circle_white));
        binding.textRussian.setTextColor(ContextCompat.getColor(SettingsActivity.this, R.color.light_gray));
    }

    private void setRussian() {
        binding.circleRussian.setImageDrawable(ContextCompat.getDrawable(SettingsActivity.this, R.drawable.circle_orange));
        binding.textRussian.setTextColor(ContextCompat.getColor(SettingsActivity.this, R.color.orange));

        binding.circlePortuguese.setImageDrawable(ContextCompat.getDrawable(SettingsActivity.this, R.drawable.circle_white));
        binding.textPortuguese.setTextColor(ContextCompat.getColor(SettingsActivity.this, R.color.white));

        binding.circleEnglish.setImageDrawable(ContextCompat.getDrawable(SettingsActivity.this, R.drawable.circle_white));
        binding.textEnglish.setTextColor(ContextCompat.getColor(SettingsActivity.this, R.color.light_gray));
    }

}