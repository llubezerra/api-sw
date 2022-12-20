package com.cursoandoid.starwars.activity;

import static com.cursoandoid.starwars.Constants.SEARCH_ALL_CHARACTERS_EXTRAS;
import static com.cursoandoid.starwars.Constants.SEARCH_BY_NAME_CHARACTERS_EXTRAS;
import static com.cursoandoid.starwars.Constants.SEARCH_INFORMATION_API_EXTRAS;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandoid.starwars.R;
import com.cursoandoid.starwars.UtilsGeneric;
import com.cursoandoid.starwars.adapter.AdapterCharacterSearch;
import com.cursoandoid.starwars.fragment.CharacterSearchFragment;
import com.cursoandoid.starwars.model.Character;
import com.cursoandoid.starwars.viewmodel.CharacterSearchViewModel;

import java.util.List;
import java.util.Objects;

public class CharacterSearchActivity extends DefaultSearchActivity {

    private CharacterSearchViewModel viewModel;
    private CharacterSearchFragment fragment;
    private AdapterCharacterSearch adapter;

    ProgressDialog progressDialog;

    String search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.brown));

        //Use ViewModel
        viewModel = new ViewModelProvider(this).get(CharacterSearchViewModel.class);

        setupNavigation();

    }

    private void setupNavigation() {
        binding.clHeader.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_character));
        binding.icon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.luke));
        binding.text.setText(R.string.characters);
        binding.editText.setHint(R.string.hint);

        //Here get the string stored in the string variable (stored in Constants) and do calls and redirects
        String searchExtras = getIntent().getStringExtra(SEARCH_INFORMATION_API_EXTRAS);

        //Se usar == não vai!
        if(Objects.equals(searchExtras, SEARCH_ALL_CHARACTERS_EXTRAS)) {
            // ENTRAR NA TELA E ABRIR TUDO (Go fragment)
            Log.d("LOG", "Search: " + searchExtras);
            goToFragment();
        } else if(Objects.equals(searchExtras, SEARCH_BY_NAME_CHARACTERS_EXTRAS)) {
            Log.d("LOG","Search: " + searchExtras);
        }

        binding.searchButton.setOnClickListener(v -> {
            search = binding.editText.getText().toString();
            callByName();
            UtilsGeneric.hideKeyboard(this);
        });
    }

    private void setupValues() {

    }

    public void callByName() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        //API CALL
        viewModel.callGetByNameCharacters(search);

        // Create the observer which updates the UI.
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.getDataListDone().observe(this, new Observer<List<Character>>() {
            @Override
            public void onChanged(List<Character> characters) {
                // Update the UI, in this case, a list
                progressDialog.dismiss();
                if(characters != null){
                    binding.clRecyclerSearch.setVisibility(View.VISIBLE);
                    generateDataList(characters);
                } else {
                    onFailure();
                }
            }
        });

    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<Character> characterList) {
        adapter = new AdapterCharacterSearch(this, characterList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerSearchList.setLayoutManager(layoutManager);
        binding.recyclerSearchList.setAdapter(adapter);
        binding.textResults.setText(getString(R.string.results, adapter.getItemCount()));
    }

    public void onFailure(){
        binding.clFailure.setVisibility(View.VISIBLE);
        binding.failure.setTextColor(ContextCompat.getColor(this, R.color.brown));
    }

    private void goToFragment() {
        //ir pra fragment
        fragment = new CharacterSearchFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);

        fragment.saveContext(this);

        transaction.commit();

        binding.clSearch.setVisibility(View.GONE);
    }

}
