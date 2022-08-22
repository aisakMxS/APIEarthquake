package com.example.appearthqueke;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appearthqueke.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding.eqRecycler.setLayoutManager(new LinearLayoutManager(this));

        EqAdapter adapter = new EqAdapter();

        adapter.setOnItemClickListener(earthquake ->{
            //Toast.makeText(MainActivity.this, earthquake.getPlace(),
            //Toast.LENGTH_SHORT).show();
            abrir_eq_detalle(earthquake);
                });



        binding.eqRecycler.setAdapter(adapter);

        viewModel.getEqList().observe(this, eqList->{
            adapter.submitList(eqList);
            if(eqList.isEmpty()){
                binding.emptyView.setVisibility(View.VISIBLE);
            }else{
                binding.emptyView.setVisibility(View.GONE);
            }
        });

        viewModel.getEarthquakes();

    }

    private void abrir_eq_detalle (Earthquake earthquake){
        Intent intent = new Intent(this, EqDetalle.class);
        intent.putExtra(EqDetalle.DT_EARTHQUAKE_KEY,earthquake);
        startActivity(intent);
    }
}