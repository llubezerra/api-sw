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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandoid.starwars.R;
import com.cursoandoid.starwars.adapter.AdapterPlanetSearch;
import com.cursoandoid.starwars.databinding.FragmentDefaultSearchBinding;
import com.cursoandoid.starwars.model.Planet;
import com.cursoandoid.starwars.viewmodel.PlanetSearchViewModel;

import java.util.List;

public class PlanetSearchFragment extends Fragment {

    Context context;
    ProgressDialog progressDialog;

    private PlanetSearchViewModel viewModel;
    protected FragmentDefaultSearchBinding binding;
    private AdapterPlanetSearch adapter;

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

        //String.valueOf(quantity);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Get the ViewModel
        viewModel = new ViewModelProvider(this).get(PlanetSearchViewModel.class);

        //API CALL
        viewModel.callGetAllPlanets();

        // Create the observer which updates the UI.
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.getDataListDone().observe(getViewLifecycleOwner(), new Observer<List<Planet>>() {
            @Override
            public void onChanged(List<Planet> planets) {
                // Update the UI, in this case, a list
                progressDialog.dismiss();
                if (planets != null) {
                    generateDataList(planets);
                } else {
                    onFailure();
                }
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<Planet> planetList) {
        adapter = new AdapterPlanetSearch(planetList, context);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        binding.recyclerSearchList.setLayoutManager(layoutManager);
        binding.recyclerSearchList.setAdapter(adapter);
        binding.textResults.setText(getString(R.string.results, adapter.getItemCount()));
    }

    public void onFailure() {
        //visible gone pro constraint
        Toast.makeText(context, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();

    }

    public void saveContext(Context context) {
        this.context = context;
    }

}
