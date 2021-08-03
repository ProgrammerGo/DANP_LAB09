package com.labplatform.laboratorio09;


import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.labplatform.laboratorio09.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding binding;
    private ActivityResultLauncher<String> resultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        initComponents(binding);
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.resultLauncher =
                registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean result) {
                        if (result) {
                        } else {
                            Toast.makeText(MainActivity.this.getApplicationContext(),"Active la camara por favor.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        this.resultLauncher.launch(Manifest.permission.CAMERA);

    }

    public void initComponents(ActivityMainBinding binding){
        binding.cam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CamOneActivity.class));
            }
        });
    }
}
