package com.cursoandoid.starwars.activity;

import static com.cursoandoid.starwars.Constants.SEARCH_ALL_STARSHIPS_EXTRAS;
import static com.cursoandoid.starwars.Constants.SEARCH_BY_NAME_STARSHIPS_EXTRAS;
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
import com.cursoandoid.starwars.fragment.StarshipSearchFragment;
import com.cursoandoid.starwars.model.SwapiObject;
import com.cursoandoid.starwars.viewmodel.StarshipSearchViewModel;

import java.util.List;
import java.util.Objects;

public class StarshipSearchActivity extends DefaultSearchActivity{

    private StarshipSearchViewModel viewModel;
    private StarshipSearchFragment fragment;
    private SearchAdapter adapter;

    ProgressDialog progressDialog;

    String search;
    String url;
    Integer page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue_gray));

        //Use ViewModel
        viewModel = new ViewModelProvider(this).get(StarshipSearchViewModel.class);

        setupNavigation();
    }

    private void setupNavigation() {

        //Here get the string stored in the string variable (stored in Constants) and do calls and redirects
        String searchExtras = getIntent().getStringExtra(SEARCH_INFORMATION_API_EXTRAS);

        //Se usar == não vai!
        if(Objects.equals(searchExtras, SEARCH_ALL_STARSHIPS_EXTRAS)) {
            // ENTRAR NA TELA E ABRIR TUDO (Go fragment)
            Log.d("LOG", "Search: " + searchExtras);
            goToFragment();
        } else if(Objects.equals(searchExtras, SEARCH_BY_NAME_STARSHIPS_EXTRAS)) {
            Log.d("LOG","Search: " + searchExtras);
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
        viewModel.callGetByNameStarships(search, this);

        // Create the observer which updates the UI.
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.getDataListDone().observe(this, new Observer<List<SwapiObject>>() {
            @Override
            public void onChanged(List<SwapiObject> starships) {
                // Update the UI, in this case, a list
                progressDialog.dismiss();
                if(starships != null){
                    binding.clRecyclerSearch.setVisibility(View.VISIBLE);
                    generateDataList(starships);
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
    private void generateDataList(List<SwapiObject> starshipList) {
        adapter = new SearchAdapter(starshipList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerSearchList.setLayoutManager(layoutManager);
        binding.recyclerSearchList.setAdapter(adapter);
        binding.textResults.setText(getString(R.string.results, viewModel.getQuantity()));
    }

    private void nextPage() {

        if(page == 1){
            url = "https://swapi.dev/api/starships/?page=2";
        } else if(page == 2){
            url = "https://swapi.dev/api/starships/?page=3";
        } else if(page == 3){
            url = "https://swapi.dev/api/starships/?page=4";
        }

        //API CALL
        viewModel.callGetPaginationStarships(url, this);

        // Create the observer which updates the UI.
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.getDataListDone().observe(this, new Observer<List<SwapiObject>>() {
            @Override
            public void onChanged(List<SwapiObject> starships) {
                // Update the UI, in this case, a list
                progressDialog.dismiss();
                if(starships != null){
                    generateDataList(starships);
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
        //Toast.makeText(this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
        binding.clFailure.setVisibility(View.VISIBLE);
    }

    private void goToFragment() {
        //ir pra fragment
        fragment = new StarshipSearchFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);

        fragment.saveContext(this);

        transaction.commit();
        //sumir o search
        binding.clSearch.setVisibility(View.GONE);
    }

}