package com.cursoandoid.starwars.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;

import com.cursoandoid.starwars.R;
import com.cursoandoid.starwars.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {
    private ActivitySettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(Color.BLACK);

        setupNavigation();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void setupNavigation() {
        binding.arrow.setOnClickListener(v -> onBackPressed());
        binding.icon.setColorFilter(ContextCompat.getColor(this, R.color.light_gray));
    }

}