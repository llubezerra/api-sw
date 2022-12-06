package com.cursoandoid.starwars.activity;

import static com.cursoandoid.starwars.Constants.SEARCH_ALL_STARSHIPS_EXTRAS;
import static com.cursoandoid.starwars.Constants.SEARCH_BY_NAME_STARSHIPS_EXTRAS;
import static com.cursoandoid.starwars.Constants.SEARCH_INFORMATION_API_EXTRAS;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandoid.starwars.R;
import com.cursoandoid.starwars.adapter.AdapterStarshipSearch;
import com.cursoandoid.starwars.fragment.StarshipSearchFragment;
import com.cursoandoid.starwars.model.Starship;
import com.cursoandoid.starwars.viewmodel.StarshipSearchViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StarshipSearchActivity extends DefaultSearchActivity{

    private StarshipSearchViewModel viewModel;
    private StarshipSearchFragment fragment;

    Context context;
    ProgressDialog progressDialog;

    private AdapterStarshipSearch adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Use ViewModel
        viewModel = new ViewModelProvider(this).get(StarshipSearchViewModel.class);

        setupNavigation();

        //criar Observer da view model
        //binding.textResults.setText(getString(R.string.results, count));

//        viewModel.saveProgressDialog(progressDialog);
//        viewModel.callGetAllStarships();

        //set status bar não tá ok
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
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Loading....");
            progressDialog.show();

            Toast.makeText(this, "starship by name", Toast.LENGTH_SHORT).show();
        }

        binding.searchButton.setOnClickListener(v -> {


        });

//            newString= (String) savedInstanceState.getSerializable("STRING_I_NEED");

    }

//        count = response.body().getCount();
//        System.out.println(count);

    public void saveContext(Context context) {
        this.context = context;
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
//    private void generateDataList(List<Starship> starshipList) {
//        adapter = new AdapterStarshipSearch(context, starshipList);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
//        binding.recyclerSearchList.setLayoutManager(layoutManager);
//        binding.recyclerSearchList.setAdapter(adapter);
//        Integer count2 = adapter.getItemCount();
//        System.out.println(count2 + "adapter");
//    }

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

//    // Create the observer which updates the UI.
//    final LiveData<ArrayList<Starship>> listObserver = viewModel.getDataStarshipsList();
//        listObserver.observe(getViewLifecycleOwner(), new Observer<ArrayList<String>>() {
//        @Override
//        public void onChanged(ArrayList<String> strings) {
//            // Update the UI
//            generateDataList(viewModel.getDataStarshipsList());
//        }
//    });
//
//    // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
//        viewModel.getDataStarshipsList().observe(this, listObserver);
//}

}