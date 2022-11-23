package com.cursoandoid.starwars.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.cursoandoid.starwars.R;
import com.cursoandoid.starwars.databinding.FragmentDefaultSearchBinding;

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
    protected FragmentDefaultSearchBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentDefaultSearchBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_default_search, container, false);
    }
}