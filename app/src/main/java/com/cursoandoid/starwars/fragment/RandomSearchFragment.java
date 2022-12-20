package com.cursoandoid.starwars.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandoid.starwars.R;
import com.cursoandoid.starwars.adapter.AdapterCharacterSearch;
import com.cursoandoid.starwars.adapter.AdapterPlanetSearch;
import com.cursoandoid.starwars.adapter.AdapterStarshipSearch;
import com.cursoandoid.starwars.databinding.FragmentDefaultSearchBinding;
import com.cursoandoid.starwars.model.Character;
import com.cursoandoid.starwars.model.Planet;
import com.cursoandoid.starwars.model.Starship;
import com.cursoandoid.starwars.viewmodel.RandomSearchViewModel;

import java.util.List;

public class RandomSearchFragment extends Fragment {

    Context context;
    ProgressDialog progressDialog;

    int screen;

    private RandomSearchViewModel viewModel;
    protected FragmentDefaultSearchBinding binding;

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

        if(screen == 0) {
            //API CALL
            viewModel.callGetAllStarships();

            // Create the observer which updates the UI.
            // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
            viewModel.getDataListStarship().observe(getViewLifecycleOwner(), new Observer<List<Starship>>() {
                @Override
                public void onChanged(List<Starship> starships) {
                    // Update the UI, in this case, a list
                    progressDialog.dismiss();
                    if(starships != null){
                        generateDataListStarship(starships);
                    } else {
                        onFailure();
                    }
                }
            });
        } else if (screen == 1) {
            //API CALL
            viewModel.callGetAllPlanets();

            // Create the observer which updates the UI.
            // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
            viewModel.getDataListPlanet().observe(getViewLifecycleOwner(), new Observer<List<Planet>>() {
                @Override
                public void onChanged(List<Planet> planets) {
                    // Update the UI, in this case, a list
                    progressDialog.dismiss();
                    if (planets != null) {
                        generateDataListPlanet(planets);
                    } else {
                        onFailure();
                    }
                }
            });
        }
        else {
            //API CALL
            viewModel.callGetAllCharacters();

            // Create the observer which updates the UI.
            // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
            viewModel.getDataListCharacter().observe(getViewLifecycleOwner(), new Observer<List<Character>>() {
                @Override
                public void onChanged(List<Character> characters) {
                    // Update the UI, in this case, a list
                    progressDialog.dismiss();
                    if(characters != null){
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
        AdapterStarshipSearch adapterStarshipSearch = new AdapterStarshipSearch(context, starshipList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        binding.recyclerSearchList.setLayoutManager(layoutManager);
        binding.recyclerSearchList.setAdapter(adapterStarshipSearch);
        binding.textResults.setText(getString(R.string.results, adapterStarshipSearch.getItemCount()));
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataListPlanet(List<Planet> planetList) {
        AdapterPlanetSearch adapterPlanetSearch = new AdapterPlanetSearch(planetList, context);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        binding.recyclerSearchList.setLayoutManager(layoutManager);
        binding.recyclerSearchList.setAdapter(adapterPlanetSearch);
        binding.textResults.setText(getString(R.string.results, adapterPlanetSearch.getItemCount()));
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataListCharacter(List<Character> characterList) {
        AdapterCharacterSearch adapterCharacterSearch = new AdapterCharacterSearch(context, characterList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        binding.recyclerSearchList.setLayoutManager(layoutManager);
        binding.recyclerSearchList.setAdapter(adapterCharacterSearch);
        binding.textResults.setText(getString(R.string.results, adapterCharacterSearch.getItemCount()));
    }

    public void onFailure(){
        //visible gone pro constraint
        Toast.makeText(context, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();

    }

    public void saveContext(Context context, int screen) {
        this.context = context;
        this.screen = screen;
    }

}
