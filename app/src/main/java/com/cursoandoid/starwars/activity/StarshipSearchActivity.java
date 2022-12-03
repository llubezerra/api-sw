package com.cursoandoid.starwars.activity;

import static com.cursoandoid.starwars.Constants.SEARCH_ALL_STARSHIPS_EXTRAS;
import static com.cursoandoid.starwars.Constants.SEARCH_INFORMATION_API_EXTRAS;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;

import com.cursoandoid.starwars.R;
import com.cursoandoid.starwars.fragment.StarshipSearchFragment;
import com.cursoandoid.starwars.viewmodel.StarshipSearchViewModel;

public class StarshipSearchActivity extends DefaultSearchActivity{

    private StarshipSearchViewModel viewModel; /** TEMPORÁRIO */

    private StarshipSearchFragment fragment;
    //String origin = getIntent().getStringExtra(SEARCH_INFORMATION_API_EXTRAS);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupNavigation();

        //set status bar não tá ok
        //getWindow().setStatusBarColor(Color.alpha(R.color.dark_gray));
    }

    private void setupNavigation() {
        binding.searchButton.setOnClickListener(v -> {
//            if(viewModel.getOrigin() == SEARCH_ALL_STARSHIPS_EXTRAS){
//                Toast.makeText(this, "Tudão", Toast.LENGTH_SHORT).show();
//            } else{
//                Toast.makeText(this, "Por nome", Toast.LENGTH_SHORT).show();
//            }
        });

//        Bundle bundle = getIntent().getExtras();
//
//        if(bundle.getString("strName")!= null)
//        {
//            //TODO here get the string stored in the string variable and do
//            // setText() on userName
//        }
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