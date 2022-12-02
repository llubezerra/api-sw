package com.cursoandoid.starwars.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandoid.starwars.R;
import com.cursoandoid.starwars.adapter.AdapterStarshipSearch;
import com.cursoandoid.starwars.databinding.FragmentDefaultSearchBinding;
import com.cursoandoid.starwars.model.Starship;
import com.cursoandoid.starwars.viewmodel.StarshipSearchViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StarshipSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StarshipSearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StarshipSearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DefaultSearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StarshipSearchFragment newInstance(String param1, String param2) {
        StarshipSearchFragment fragment = new StarshipSearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    // POR ORA SÓ ESTOU USANDO ESSE
    Context context;
    ProgressDialog progressDialog;

    private StarshipSearchViewModel viewModel;
    protected FragmentDefaultSearchBinding binding;
    private AdapterStarshipSearch adapter;

    private Integer count;

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

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Use ViewModel
        viewModel = new ViewModelProvider(this).get(StarshipSearchViewModel.class);

        binding.textResults.setText(getString(R.string.results, count));

        viewModel.saveProgressDialog(progressDialog);
        viewModel.callGetAllStarships();

        //criar Observer da view model

//        count = response.body().getCount();
//        System.out.println(count);
    }

    public void saveContext(Context context) {
        this.context = context;
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<Starship> starshipList) {
        adapter = new AdapterStarshipSearch(context, starshipList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        binding.recyclerSearchList.setLayoutManager(layoutManager);
        binding.recyclerSearchList.setAdapter(adapter);
        Integer count2 = adapter.getItemCount();
        System.out.println(count2 + "adapter");
    }



}