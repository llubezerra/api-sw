package com.cursoandoid.starwars.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandoid.starwars.GetDataService;
import com.cursoandoid.starwars.R;
import com.cursoandoid.starwars.adapter.AdapterCharacterSearch;
import com.cursoandoid.starwars.databinding.FragmentDefaultSearchBinding;
import com.cursoandoid.starwars.model.Character;
import com.cursoandoid.starwars.model.Characters;
import com.cursoandoid.starwars.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterSearchFragment extends Fragment {
    Context context;

    ProgressDialog progressDialog;

    protected FragmentDefaultSearchBinding binding;
    private AdapterCharacterSearch adapter;

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

        setupValues();
        //String.valueOf(quantity);

        return view;
    }
    
    private void setupValues() {



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        callApi();
    }

    public void saveContext(Context context){
        this.context = context;
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<Character> characterList) {
        adapter = new AdapterCharacterSearch(context, characterList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        binding.recyclerSearchList.setLayoutManager(layoutManager);
        binding.recyclerSearchList.setAdapter(adapter);
        Integer count2 = adapter.getItemCount();
        System.out.println(count2 + "adapter");
    }

    private void callApi(){
        // Create handle for the RetrofitInstance interface
        // ENTRAR NA TELA ABRIR TUDO
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Characters> call = service.getAllCharacters();
        call.enqueue(new Callback<Characters>() {
            @Override
            public void onResponse(Call<Characters> call, Response<Characters> response) {
                progressDialog.dismiss();
                if(response.body() != null) {
                    generateDataList(response.body().getResults());
                    Integer count = response.body().getCount();
                    System.out.println(count);
                }
            }

            @Override
            public void onFailure(Call<Characters> call, Throwable t) {
                //visible gone pro costraint
                Log.d("LOG", "onFailure: " + t.getMessage());
                progressDialog.dismiss();
                Toast.makeText(context, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }

        });
    }
}