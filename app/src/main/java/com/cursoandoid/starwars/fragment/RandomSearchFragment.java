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

    int screen;

    private RandomSearchViewModel viewModel;
    protected FragmentDefaultSearchBinding binding;
    private SearchAdapter adapter;

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
            viewModel.callGetAllStarships(requireActivity());

            // Create the observer which updates the UI.
            // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
            viewModel.getDataList().observe(getViewLifecycleOwner(), new Observer<List<SwapiObject>>() {
                @Override
                public void onChanged(List<SwapiObject> starships) {
                    // Update the UI, in this case, a list
                    progressDialog.dismiss();
                    if(starships != null){
                        generateDataList(starships);
                    } else {
                        onFailure();
                    }
                }
            });
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
                    } else {
                        onFailure();
                    }
                }
            });
        }
        else {
            //API CALL
            viewModel.callGetAllCharacters(requireActivity());

            // Create the observer which updates the UI.
            // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
            viewModel.getDataList().observe(getViewLifecycleOwner(), characters -> {
                    // Update the UI, in this case, a list
                    progressDialog.dismiss();
                    if(characters != null){
                        generateDataList(characters);
                    } else {
                        onFailure();
                    }
            });
        }
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<SwapiObject> dataList) {
        adapter = new SearchAdapter(dataList, context);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        binding.recyclerSearchList.setLayoutManager(layoutManager);
        binding.recyclerSearchList.setAdapter(adapter);
        binding.textResults.setText(getString(R.string.results, adapter.getItemCount()));
    }

    public void onFailure(){
        binding.clResult.setVisibility(View.GONE);
        binding.clFailure.setVisibility(View.VISIBLE);
        binding.failure.setTextColor(ContextCompat.getColor(context, R.color.dark_gray));
    }

    public void saveContext(Context context, int screen) {
        this.context = context;
        this.screen = screen;
    }

}
