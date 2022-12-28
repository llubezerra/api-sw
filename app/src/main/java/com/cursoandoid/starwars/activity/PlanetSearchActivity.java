package com.cursoandoid.starwars.activity;

import static com.cursoandoid.starwars.Constants.SEARCH_ALL_PLANETS_EXTRAS;
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
import com.cursoandoid.starwars.adapter.SearchAdapter;
import com.cursoandoid.starwars.fragment.PlanetSearchFragment;
import com.cursoandoid.starwars.model.SwapiObject;
import com.cursoandoid.starwars.viewmodel.PlanetSearchViewModel;

import java.util.List;
import java.util.Objects;

public class PlanetSearchActivity extends DefaultSearchActivity {

    private PlanetSearchViewModel viewModel;
    private PlanetSearchFragment fragment;
    private SearchAdapter adapter;

    ProgressDialog progressDialog;

    String search;
    String url;
    Integer page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.orange_super_dark));

        //Use ViewModel
        viewModel = new ViewModelProvider(this).get(PlanetSearchViewModel.class);

        setupNavigation();

    }

    //começar a usar o binding pra arrumar a classe
    private void setupNavigation() {
        binding.clHeader.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_planet));
        binding.icon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.planet));
        binding.text.setText(R.string.planets);
        binding.editText.setHint(R.string.hint);

        //Here get the string stored in the string variable (stored in Constants) and do calls and redirects
        String searchExtras = getIntent().getStringExtra(SEARCH_INFORMATION_API_EXTRAS);

        //Se usar == não vai!
        if (Objects.equals(searchExtras, SEARCH_ALL_PLANETS_EXTRAS)) {
            // ENTRAR NA TELA E ABRIR TUDO (Go fragment)
            Log.d("LOG", "Search: " + searchExtras);
            goToFragment();
        } else if (Objects.equals(searchExtras, SEARCH_BY_NAME_CHARACTERS_EXTRAS)) {
            Log.d("LOG", "Search: " + searchExtras);
        }

        binding.searchButton.setOnClickListener(v -> {
            search = binding.editText.getText().toString();
            callByName();
            UtilsGeneric.hideKeyboard(this);
        });

        binding.loadMore.setOnClickListener(v -> {
            page++;
            nextPage();
        });

    }

    public void callByName() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        //API CALL
        viewModel.callGetByNamePlanets(search, this);

        // Create the observer which updates the UI.
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.getDataListDone().observe(this, new Observer<List<SwapiObject>>() {
            @Override
            public void onChanged(List<SwapiObject> planets) {
                // Update the UI, in this case, a list
                progressDialog.dismiss();
                if (planets != null) {
                    binding.clRecyclerSearch.setVisibility(View.VISIBLE);
                    generateDataList(planets);
                    if(viewModel.enablePagination()){
                        binding.loadMore.setVisibility(View.VISIBLE);
                    }
                } else {
                    onFailure();
                }
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<SwapiObject> planetList) {
        adapter = new SearchAdapter(planetList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerSearchList.setLayoutManager(layoutManager);
        binding.recyclerSearchList.setAdapter(adapter);
        binding.textResults.setText(getString(R.string.results, viewModel.getQuantity()));
    }

    private void nextPage() {

            url = "https://swapi.dev/api/planets/?page=" + page;

            //API CALL
            viewModel.callGetPaginationPlanets(url, this);

            // Create the observer which updates the UI.
            // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
            viewModel.getDataListDone().observe(this, new Observer<List<SwapiObject>>() {
                @Override
                public void onChanged(List<SwapiObject> planets) {
                    // Update the UI, in this case, a list
                    progressDialog.dismiss();
                    if(planets != null){
                        generateDataList(planets);
                        if(!viewModel.paginationNext()){
                            binding.loadMore.setText(getString(R.string.end));
                            binding.loadMore.setEnabled(false);
                        }
                    } else {
                        onFailure();
                    }
                }
            });
    }

    public void onFailure(){
        binding.clFailure.setVisibility(View.VISIBLE);
        binding.failure.setTextColor(ContextCompat.getColor(this, R.color.orange_super_dark));
    }

    private void goToFragment() {
        //ir pra fragment
        fragment = new PlanetSearchFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);

        fragment.saveContext(this);

        transaction.commit();

        binding.clSearch.setVisibility(View.GONE);
    }

}
