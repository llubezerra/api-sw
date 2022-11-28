package com.cursoandoid.starwars.activity;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentTransaction;

import com.cursoandoid.starwars.R;
import com.cursoandoid.starwars.fragment.StarshipSearchFragment;

public class StarshipSearchActivity extends DefaultSearchActivity{
    private StarshipSearchFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupNavigation();

        //set status bar não tá ok
        //getWindow().setStatusBarColor(Color.alpha(R.color.dark_gray));
    }

    private void setupNavigation() {
        binding.searchButton.setOnClickListener(v -> {
            //ir pra fragment
            fragment = new StarshipSearchFragment();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame, fragment);

            fragment.saveContext(this);

            transaction.commit();
            //sumir o search
            binding.clSearch.setVisibility(View.GONE);
        });
    }

}