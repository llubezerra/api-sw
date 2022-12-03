package com.cursoandoid.starwars.activity;

import android.os.Bundle;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.cursoandoid.starwars.R;

public class RandomSearchActivity extends DefaultSearchActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupNavigation();
    }

    private void setupNavigation() {
        binding.arrow.setOnClickListener(v -> onBackPressed());

        binding.clHeader.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_random));
        binding.icon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.darthvader));
        binding.text.setText(R.string.search_random);
        binding.getRoot().setBackgroundColor(ContextCompat.getColor(this, R.color.orange_light));
        binding.clSearch.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
