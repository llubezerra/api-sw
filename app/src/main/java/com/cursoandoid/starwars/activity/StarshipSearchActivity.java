package com.cursoandoid.starwars.activity;

import static com.cursoandoid.starwars.Constants.SEARCH_ALL_STARSHIPS_EXTRAS;
import static com.cursoandoid.starwars.Constants.SEARCH_BY_NAME_STARSHIPS_EXTRAS;
import static com.cursoandoid.starwars.Constants.SEARCH_INFORMATION_API_EXTRAS;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;

import com.cursoandoid.starwars.R;
import com.cursoandoid.starwars.fragment.StarshipSearchFragment;
import com.cursoandoid.starwars.viewmodel.StarshipSearchViewModel;

import java.util.Objects;

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

            String searchExtras = getIntent().getStringExtra(SEARCH_INFORMATION_API_EXTRAS);

            //Se usar == não vai!
            if(Objects.equals(searchExtras, SEARCH_ALL_STARSHIPS_EXTRAS)) {
                System.out.println("Search: " + searchExtras);
                Toast.makeText(this, "starship all", Toast.LENGTH_SHORT).show();
            } else if(Objects.equals(searchExtras, SEARCH_BY_NAME_STARSHIPS_EXTRAS)) {
                System.out.println("Search: " + searchExtras);
                Toast.makeText(this, "starship by name", Toast.LENGTH_SHORT).show();
            }

        });

//        {
//            //TODO here get the string stored in the string variable and do
//            // setText() on userName
//        }
//        String newString;
//
//            if(extras == null) {
//                newString= null;
//            } else {
//                newString= extras.getString("STRING_I_NEED");
//            }
//        } else {
//            newString= (String) savedInstanceState.getSerializable("STRING_I_NEED");
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