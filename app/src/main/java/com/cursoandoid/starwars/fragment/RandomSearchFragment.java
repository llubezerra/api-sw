package com.cursoandoid.starwars.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandoid.starwars.R;
import com.cursoandoid.starwars.adapter.SearchAdapter;
import com.cursoandoid.starwars.databinding.FragmentDefaultSearchBinding;
import com.cursoandoid.starwars.model.SwapiObject;
import com.cursoandoid.starwars.viewmodel.RandomSearchViewModel;

import java.util.List;

public class RandomSearchFragment extends Fragment {

    Context context;
    ProgressDialog progressDialog;

    private RandomSearchViewModel viewModel;
    protected FragmentDefaultSearchBinding binding;
    private SearchAdapter adapter;

    int screen;
    String url;
    Integer page = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        // Inflate the layout for this fragment
        binding = FragmentDefaultSearchBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.getRoot().setBackgroundColor(ContextCompat.getColor(context, R.color.orange_light));

        //String.valueOf(quantity);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Get the ViewModel
        viewModel = new ViewModelProvider(this).get(RandomSearchViewModel.class);

        if (screen == 0) {
            //API CALL
            viewModel.callGetAllStarships(requireActivity());

            // Create the observer which updates the UI.
            // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
            viewModel.getDataList().observe(getViewLifecycleOwner(), new Observer<List<SwapiObject>>() {
                @Override
                public void onChanged(List<SwapiObject> starships) {
                    // Update the UI, in this case, a list
                    progressDialog.dismiss();
                    if (starships != null) {
                        generateDataList(starships);
                        if (viewModel.enablePagination()) {
                            binding.loadMore.setVisibility(View.VISIBLE);
                        }
                    } else {
                        onFailure();
                    }
                }
            });
            nextPageStarships();

        } else if (screen == 1) {
            //API CALL
            viewModel.callGetAllPlanets(requireActivity());

            // Create the observer which updates the UI.
            // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
            viewModel.getDataList().observe(getViewLifecycleOwner(), new Observer<List<SwapiObject>>() {
                @Override
                public void onChanged(List<SwapiObject> planets) {
                    // Update the UI, in this case, a list
                    progressDialog.dismiss();
                    if (planets != null) {
                        generateDataList(planets);
                        if (viewModel.enablePagination()) {
                            binding.loadMore.setVisibility(View.VISIBLE);
                        }
                    } else {
                        onFailure();
                    }
                }
            });
            nextPagePlanets();

        } else {
            //API CALL
            viewModel.callGetAllCharacters(requireActivity());

            // Create the observer which updates the UI.
            // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
            viewModel.getDataList().observe(getViewLifecycleOwner(), characters -> {
                // Update the UI, in this case, a list
                progressDialog.dismiss();
                if (characters != null) {
                    generateDataList(characters);
                    if (viewModel.enablePagination()) {
                        binding.loadMore.setVisibility(View.VISIBLE);
                    }
                } else {
                    onFailure();
                }
            });
            nextPageCharacters();

        }
    }

    private void nextPageStarships() {

        binding.loadMore.setOnClickListener(v -> {
            page++;

            if (page == 1) {
                url = "https://swapi.dev/api/starships/?page=2";
            } else if (page == 2) {
                url = "https://swapi.dev/api/starships/?page=3";
            } else if (page == 3) {
                url = "https://swapi.dev/api/starships/?page=4";
            }

            //API CALL
            viewModel.callGetPaginationStarships(url, requireActivity());

            // Create the observer which updates the UI.
            // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
            viewModel.getDataList().observe(getViewLifecycleOwner(), new Observer<List<SwapiObject>>() {
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
        });

    }

    private void nextPagePlanets() {

        binding.loadMore.setOnClickListener(v -> {
            page++;

            url = "https://swapi.dev/api/planets/?page=" + page;

            //API CALL
            viewModel.callGetPaginationPlanets(url, requireActivity());

            // Create the observer which updates the UI.
            // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
            viewModel.getDataList().observe(getViewLifecycleOwner(), new Observer<List<SwapiObject>>() {
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
        });

    }

    private void nextPageCharacters() {

        binding.loadMore.setOnClickListener(v -> {
            page++;

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
            viewModel.callGetPaginationCharacters(url, requireActivity());

            // Create the observer which updates the UI.
            // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
            viewModel.getDataList().observe(getViewLifecycleOwner(), new Observer<List<SwapiObject>>() {
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
        });

    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<SwapiObject> dataList) {
        adapter = new SearchAdapter(dataList, context);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        binding.recyclerSearchList.setLayoutManager(layoutManager);
        binding.recyclerSearchList.setAdapter(adapter);
        binding.textResults.setText(getString(R.string.results, viewModel.getQuantity()));
    }

    public void onFailure() {
        binding.clResult.setVisibility(View.GONE);
        binding.clFailure.setVisibility(View.VISIBLE);
        binding.failure.setTextColor(ContextCompat.getColor(context, R.color.dark_gray));
    }

    public void saveContext(Context context, int screen) {
        this.context = context;
        this.screen = screen;
    }

}
