package com.example.latinhaexpress.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.latinhaexpress.R;

public class ColetaFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_coleta, container, false);

        setup(view);

        return view;
    }

    private void setup(View view)
    {
        TextView text_toolbar = view.findViewById(R.id.text_toolbar);
        text_toolbar.setText("Nova Coleta");
    }
}