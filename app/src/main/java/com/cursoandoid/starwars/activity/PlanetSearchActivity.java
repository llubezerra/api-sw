package com.cursoandoid.starwars.activity;

import android.os.Bundle;

import androidx.core.content.ContextCompat;

import com.cursoandoid.starwars.R;

public class PlanetSearchActivity extends DefaultSearchActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupNavigation();
    }

    //começar a usar o binding pra arrumar a classe
    private void setupNavigation() {
        binding.clHeader.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_planet));
        binding.icon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.planet));
        binding.text.setText(R.string.planets);
        binding.editText.setHint(R.string.hint);
    }

}
