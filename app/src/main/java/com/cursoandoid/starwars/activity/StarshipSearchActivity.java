package com.cursoandoid.starwars.activity;

import static com.cursoandoid.starwars.Constants.SEARCH_ALL_STARSHIPS_EXTRAS;
import static com.cursoandoid.starwars.Constants.SEARCH_BY_NAME_STARSHIPS_EXTRAS;
import static com.cursoandoid.starwars.Constants.SEARCH_INFORMATION_API_EXTRAS;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandoid.starwars.R;
import com.cursoandoid.starwars.adapter.AdapterStarshipSearch;
import com.cursoandoid.starwars.fragment.StarshipSearchFragment;
import com.cursoandoid.starwars.model.Starship;
import com.cursoandoid.starwars.viewmodel.StarshipSearchViewModel;

import java.util.List;
import java.util.Objects;

public class StarshipSearchActivity extends DefaultSearchActivity{

    private StarshipSearchViewModel viewModel;
    private StarshipSearchFragment fragment;
    private AdapterStarshipSearch adapter;

    ProgressDialog progressDialog;

    String search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Use ViewModel
        viewModel = new ViewModelProvider(this).get(StarshipSearchViewModel.class);

        setupNavigation();

        //TODO: set status bar não tá ok
        //getWindow().setStatusBarColor(Color.alpha(R.color.dark_gray));
    }

    private void setupNavigation() {

        //Here get the string stored in the string variable (stored in Constants) and do calls and redirects
        String searchExtras = getIntent().getStringExtra(SEARCH_INFORMATION_API_EXTRAS);

        //Se usar == não vai!
        if(Objects.equals(searchExtras, SEARCH_ALL_STARSHIPS_EXTRAS)) {
            // ENTRAR NA TELA E ABRIR TUDO (Go fragment)
            System.out.println("Search: " + searchExtras);
            goToFragment();
        } else if(Objects.equals(searchExtras, SEARCH_BY_NAME_STARSHIPS_EXTRAS)) {
            System.out.println("Search: " + searchExtras);
        }

        binding.searchButton.setOnClickListener(v -> {
            search = binding.editText.getText().toString();
            callByName();
            getCurrentFocus();
        });

//      newString= (String) savedInstanceState.getSerializable("STRING_I_NEED");

    }

    public void callByName() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        //API CALL
        viewModel.callGetByNameStarships(search);

        // Create the observer which updates the UI.
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.getDataListDone().observe(this, new Observer<List<Starship>>() {
            @Override
            public void onChanged(List<Starship> starships) {
                // Update the UI, in this case, a list
                progressDialog.dismiss();
                if(starships != null){
                    binding.clRecyclerSearch.setVisibility(View.VISIBLE);
                    generateDataList(starships);
                } else {
                    onFailure();
                }
            }
        });

    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<Starship> starshipList) {
        adapter = new AdapterStarshipSearch(this, starshipList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerSearchList.setLayoutManager(layoutManager);
        binding.recyclerSearchList.setAdapter(adapter);
        binding.textResults.setText(getString(R.string.results, adapter.getItemCount()));
    }

    public void onFailure(){
        //visible gone pro constraint
        Toast.makeText(this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();

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