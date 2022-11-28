package com.cursoandoid.starwars.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;

import com.cursoandoid.starwars.R;

public class CharacterSearchFragment extends Fragment {
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_default_search, container, false);

        // Search elements on view, wich is xml fragment_default_search
        ConstraintLayout cl = view.findViewById(R.id.cl_image);
        cl.setBackground(ContextCompat.getDrawable(context, R.drawable.icon_circle_person));

        ImageView image = view.findViewById(R.id.icon_in_circle);
        image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.person));

        TextView textName = view.findViewById(R.id.text_name);

        TextView text2 = view.findViewById(R.id.text_2);
        // 2 formatos no mesmo textView
        text2.setText(HtmlCompat.fromHtml(getString(R.string.height, 123), HtmlCompat.FROM_HTML_MODE_LEGACY));

        TextView text3 = view.findViewById(R.id.text_3);
        text3.setText(getString(R.string.eyes_color, "mock"));
        //binding.nlInstallmentsQuantity.setText(getString(R.string.pix_installments_options_current_quantity, String.valueOf(quantity)));

        return view;
    }

    public void saveContext(Context context){
        this.context = context;
    }
}