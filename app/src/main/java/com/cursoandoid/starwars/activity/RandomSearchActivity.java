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
    String url;
    Integer page = 0;

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
            if (adapter != null) {
                adapter.cleanPreviousList();
            }
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
                        if (viewModel.enablePagination()) {
                            binding.loadMore.setVisibility(View.VISIBLE);
                        } else {
                            binding.loadMore.setVisibility(View.GONE);
                        }
                    } else {
                        onFailure();
                    }
                }
            });

            binding.loadMore.setOnClickListener(v -> {
                page++;
                nextPageStarships();
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
                        if (viewModel.enablePagination()) {
                            binding.loadMore.setVisibility(View.VISIBLE);
                        } else {
                            binding.loadMore.setVisibility(View.GONE);
                        }
                    } else {
                        onFailure();
                    }
                }
            });

            binding.loadMore.setOnClickListener(v -> {
                page++;
                nextPagePlanets();
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
                        if (viewModel.enablePagination()) {
                            binding.loadMore.setVisibility(View.VISIBLE);
                        } else {
                            binding.loadMore.setVisibility(View.GONE);
                        }
                    } else {
                        onFailure();
                    }
                }
            });

            binding.loadMore.setOnClickListener(v -> {
                page++;
                nextPageCharacters();
            });

        }
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<SwapiObject> dataList) {
        adapter = new SearchAdapter(dataList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerSearchList.setLayoutManager(layoutManager);
        binding.recyclerSearchList.setAdapter(adapter);
        binding.textResults.setText(getString(R.string.results, viewModel.getQuantity()));
    }

    private void nextPageStarships() {

        if (page == 1) {
            url = "https://swapi.dev/api/starships/?page=2";
        } else if (page == 2) {
            url = "https://swapi.dev/api/starships/?page=3";
        } else if (page == 3) {
            url = "https://swapi.dev/api/starships/?page=4";
        }

        //API CALL
        viewModel.callGetPaginationStarships(url, this);

        // Create the observer which updates the UI.
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.getDataList().observe(this, new Observer<List<SwapiObject>>() {
            @Override
            public void onChanged(List<SwapiObject> starships) {
                // Update the UI, in this case, a list
                progressDialog.dismiss();
                if (starships != null) {
                    generateDataList(starships);
                    if (!viewModel.paginationNext()) {
                        binding.loadMore.setText(getString(R.string.end));
                        binding.loadMore.setEnabled(false);
                    }
                } else {
                    onFailure();
                }
            }
        });

    }

    private void nextPagePlanets() {

        url = "https://swapi.dev/api/planets/?page=" + page;

        //API CALL
        viewModel.callGetPaginationPlanets(url, this);

        // Create the observer which updates the UI.
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.getDataList().observe(this, new Observer<List<SwapiObject>>() {
            @Override
            public void onChanged(List<SwapiObject> planets) {
                // Update the UI, in this case, a list
                progressDialog.dismiss();
                if (planets != null) {
                    generateDataList(planets);
                    if (!viewModel.paginationNext()) {
                        binding.loadMore.setText(getString(R.string.end));
                        binding.loadMore.setEnabled(false);
                    }
                } else {
                    onFailure();
                }
            }
        });

    }

    private void nextPageCharacters() {

        switch (page) {
            case 1:
                url = "https://swapi.dev/api/people/?page=2";
                break;
            case 2:
                url = "https://swapi.dev/api/people/?page=3";
                break;
            case 3:
                url = "https://swapi.dev/api/people/?page=4";
                break;
            case 4:
                url = "https://swapi.dev/api/people/?page=5";
                break;
            case 5:
                url = "https://swapi.dev/api/people/?page=6";
                break;
            case 6:
                url = "https://swapi.dev/api/people/?page=7";
                break;
            case 7:
                url = "https://swapi.dev/api/people/?page=8";
                break;
            case 8:
                url = "https://swapi.dev/api/people/?page=9";
                break;
        }

        //API CALL
        viewModel.callGetPaginationCharacters(url, this);

        // Create the observer which updates the UI.
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.getDataList().observe(this, new Observer<List<SwapiObject>>() {
            @Override
            public void onChanged(List<SwapiObject> characters) {
                // Update the UI, in this case, a list
                progressDialog.dismiss();
                if (characters != null) {
                    generateDataList(characters);
                    if (!viewModel.paginationNext()) {
                        binding.loadMore.setText(getString(R.string.end));
                        binding.loadMore.setEnabled(false);
                    }
                } else {
                    onFailure();
                }
            }
        });

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
