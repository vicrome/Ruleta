package com.example.laruletadelafortuna;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class actividadInformacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);
        getSupportActionBar().hide();
    }
}