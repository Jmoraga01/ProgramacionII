package com.ugb.controlesbasicos;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

//import com.denzcoskun.imageslider.ImageSlider;
//import com.denzcoskun.imageslider.constants.ScaleTypes;
//import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        Button verProductosButton = view.findViewById(R.id.button_productos);
        verProductosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a MainActivity2
                navigateToListView();
            }
        });

        return view;
    }

    private void navigateToListView() {
        // Crear un nuevo Intent para iniciar la actividad MainActivity2
        Intent intent = new Intent(getActivity(), ProductosActivity.class);
        // Iniciar la actividad MainActivity2
        startActivity(intent);
    }
}