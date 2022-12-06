package com.cursoandoid.starwars.activity;

import android.os.Bundle;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import com.cursoandoid.starwars.R;
import com.cursoandoid.starwars.fragment.CharacterSearchFragment;

public class CharacterSearchActivity extends DefaultSearchActivity {
    private CharacterSearchFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupNavigation();

        //Toast.makeText(this, "starship by name", Toast.LENGTH_SHORT).show();
    }

    private void setupNavigation() {
        binding.clHeader.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_character));
        binding.icon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.luke));
        binding.text.setText(R.string.characters);
        binding.editText.setHint(R.string.hint);

        binding.searchButton.setOnClickListener(v -> {
            //ir pra fragment
            fragment = new CharacterSearchFragment();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame, fragment);

            fragment.saveContext(this);

            transaction.commit();
            //sumir o search na mensagem de erro --> implementar
            binding.clSearch.setVisibility(View.GONE);
        });
    }

}
