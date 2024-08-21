package com.example.latinhaexpress.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.room.Room;
import com.example.latinhaexpress.R;
import com.example.latinhaexpress.dao.AllDao;
import com.example.latinhaexpress.database.MyDatabase;
import com.example.latinhaexpress.views.MenuActivity;

public class ColetaFragment extends Fragment
{
    private MyDatabase db;
    private AllDao allDao;

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