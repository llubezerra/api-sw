package com.cursoandoid.starwars.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.cursoandoid.starwars.databinding.ActivityDefaultSearchBinding;

public abstract class DefaultSearchActivity extends AppCompatActivity {
    protected ActivityDefaultSearchBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDefaultSearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupNavigation();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void setupNavigation() {
        binding.arrow.setOnClickListener(v -> onBackPressed());
    }
}