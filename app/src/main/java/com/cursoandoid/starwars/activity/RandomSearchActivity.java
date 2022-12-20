package com.cursoandoid.starwars.activity;

import static com.cursoandoid.starwars.Constants.SEARCH_ALL_RANDOM_EXTRAS;
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
import com.cursoandoid.starwars.adapter.AdapterPlanetSearch;
import com.cursoandoid.starwars.adapter.AdapterStarshipSearch;
import com.cursoandoid.starwars.fragment.RandomSearchFragment;
import com.cursoandoid.starwars.model.Character;
import com.cursoandoid.starwars.model.Planet;
import com.cursoandoid.starwars.model.Starship;
import com.cursoandoid.starwars.viewmodel.RandomSearchViewModel;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class RandomSearchActivity extends DefaultSearchActivity {

    private RandomSearchViewModel viewModel;
    private RandomSearchFragment fragment;
    private AdapterStarshipSearch adapterStarshipSearch;
    private AdapterPlanetSearch adapterPlanetSearch;
    private AdapterCharacterSearch adapterCharacterSearch;

    ProgressDialog progressDialog;

    String search;
    int screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.orange_dark));

        //Use ViewModel
        viewModel = new ViewModelProvider(this).get(RandomSearchViewModel.class);

        Random random = new Random();
        screen = random.nextInt(3);

        setupNavigation();
        whereDoWeGo();
    }

    private void setupNavigation() {
        binding.clHeader.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_random));
        binding.icon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.darthvader));
        binding.text.setText(R.string.search_random);
        binding.editText.setHint(R.string.search_random);
        binding.getRoot().setBackgroundColor(ContextCompat.getColor(this, R.color.orange_light));

        binding.searchButton.setOnClickListener(v -> {
            search = binding.editText.getText().toString();
            callByName(screen);
            UtilsGeneric.hideKeyboard(this);
        });
    }

    private void whereDoWeGo() {

        //Here get the string stored in the string variable (stored in Constants) and do calls and redirects
        String searchExtras = getIntent().getStringExtra(SEARCH_INFORMATION_API_EXTRAS);

        //Se usar == não vai!
        if (Objects.equals(searchExtras, SEARCH_ALL_RANDOM_EXTRAS)) {
            // ENTRAR NA TELA E ABRIR TUDO (Go fragment)
            goToFragment(screen);
            Log.d("LOG", "Search: " + searchExtras);
        }
    }

    private void callByName(int screen) {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        if (screen == 0) {
            //API CALL
            viewModel.callGetByNameStarships(search);

            // Create the observer which updates the UI.
            // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
            viewModel.getDataListStarship().observe(this, new Observer<List<Starship>>() {
                @Override
                public void onChanged(List<Starship> starships) {
                    // Update the UI, in this case, a list
                    progressDialog.dismiss();
                    if (starships != null) {
                        binding.clRecyclerSearch.setVisibility(View.VISIBLE);
                        generateDataListStarship(starships);
                    } else {
                        onFailure();
                    }
                }
            });
        } else if (screen == 1) {
            //API CALL
            viewModel.callGetByNamePlanets(search);

            // Create the observer which updates the UI.
            // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
            viewModel.getDataListPlanet().observe(this, new Observer<List<Planet>>() {
                @Override
                public void onChanged(List<Planet> planets) {
                    // Update the UI, in this case, a list
                    progressDialog.dismiss();
                    if (planets != null) {
                        binding.clRecyclerSearch.setVisibility(View.VISIBLE);
                        generateDataListPlanet(planets);
                    } else {
                        onFailure();
                    }
                }
            });
        } else {
            //API CALL
            viewModel.callGetByNameCharacters(search);

            // Create the observer which updates the UI.
            // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
            viewModel.getDataListCharacter().observe(this, new Observer<List<Character>>() {
                @Override
                public void onChanged(List<Character> characters) {
                    // Update the UI, in this case, a list
                    progressDialog.dismiss();
                    if (characters != null) {
                        binding.clRecyclerSearch.setVisibility(View.VISIBLE);
                        generateDataListCharacter(characters);
                    } else {
                        onFailure();
                    }
                }
            });
        }
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataListStarship(List<Starship> starshipList) {
        adapterStarshipSearch = new AdapterStarshipSearch(this, starshipList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerSearchList.setLayoutManager(layoutManager);
        binding.recyclerSearchList.setAdapter(adapterStarshipSearch);
        binding.textResults.setText(getString(R.string.results, adapterStarshipSearch.getItemCount()));
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataListPlanet(List<Planet> planetList) {
        adapterPlanetSearch = new AdapterPlanetSearch(planetList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerSearchList.setLayoutManager(layoutManager);
        binding.recyclerSearchList.setAdapter(adapterPlanetSearch);
        binding.textResults.setText(getString(R.string.results, adapterPlanetSearch.getItemCount()));
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataListCharacter(List<Character> characterList) {
        adapterCharacterSearch = new AdapterCharacterSearch(this, characterList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerSearchList.setLayoutManager(layoutManager);
        binding.recyclerSearchList.setAdapter(adapterCharacterSearch);
        binding.textResults.setText(getString(R.string.results, adapterCharacterSearch.getItemCount()));
    }

    public void onFailure() {
        binding.clFailure.setVisibility(View.VISIBLE);
        binding.failure.setTextColor(ContextCompat.getColor(this, R.color.dark_gray));
    }

    private void goToFragment(int screen) {
        //ir pra fragment
        fragment = new RandomSearchFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);

        fragment.saveContext(this, screen);

        transaction.commit();

        binding.clSearch.setVisibility(View.GONE);
    }


}
