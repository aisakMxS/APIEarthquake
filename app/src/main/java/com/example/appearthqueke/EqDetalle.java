package com.example.appearthqueke;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.example.appearthqueke.databinding.ActivityEqDetalleBinding;

public class EqDetalle extends AppCompatActivity {

    public static final String DT_EARTHQUAKE_KEY = "earthquake";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEqDetalleBinding binding = ActivityEqDetalleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle extras = getIntent().getExtras();
        Earthquake earthquake = extras.getParcelable(DT_EARTHQUAKE_KEY);
        binding.setEarthquake(earthquake);
    }
}