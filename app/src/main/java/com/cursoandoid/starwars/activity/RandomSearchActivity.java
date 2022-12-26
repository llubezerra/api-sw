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
import com.cursoandoid.starwars.adapter.SearchAdapter;
import com.cursoandoid.starwars.fragment.RandomSearchFragment;
import com.cursoandoid.starwars.model.SwapiObject;
import com.cursoandoid.starwars.viewmodel.RandomSearchViewModel;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class RandomSearchActivity extends DefaultSearchActivity {

    private RandomSearchViewModel viewModel;
    private RandomSearchFragment fragment;
    private SearchAdapter adapter;

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
            viewModel.callGetByNameStarships(search, this);

            // Create the observer which updates the UI.
            // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
            viewModel.getDataList().observe(this, new Observer<List<SwapiObject>>() {
                @Override
                public void onChanged(List<SwapiObject> starships) {
                    // Update the UI, in this case, a list
                    progressDialog.dismiss();
                    if (starships != null) {
                        binding.clRecyclerSearch.setVisibility(View.VISIBLE);
                        generateDataList(starships);
                    } else {
                        onFailure();
                    }
                }
            });
        } else if (screen == 1) {
            //API CALL
            viewModel.callGetByNamePlanets(search, this);

            // Create the observer which updates the UI.
            // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
            viewModel.getDataList().observe(this, new Observer<List<SwapiObject>>() {
                @Override
                public void onChanged(List<SwapiObject> planets) {
                    // Update the UI, in this case, a list
                    progressDialog.dismiss();
                    if (planets != null) {
                        binding.clRecyclerSearch.setVisibility(View.VISIBLE);
                        generateDataList(planets);
                    } else {
                        onFailure();
                    }
                }
            });
        } else {
            //API CALL
            viewModel.callGetByNameCharacters(search, this);

            // Create the observer which updates the UI.
            // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
            viewModel.getDataList().observe(this, new Observer<List<SwapiObject>>() {
                @Override
                public void onChanged(List<SwapiObject> characters) {
                    // Update the UI, in this case, a list
                    progressDialog.dismiss();
                    if (characters != null) {
                        binding.clRecyclerSearch.setVisibility(View.VISIBLE);
                        generateDataList(characters);
                    } else {
                        onFailure();
                    }
                }
            });
        }
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<SwapiObject> dataList) {
        adapter = new SearchAdapter(dataList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerSearchList.setLayoutManager(layoutManager);
        binding.recyclerSearchList.setAdapter(adapter);
        binding.textResults.setText(getString(R.string.results, adapter.getItemCount()));
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
