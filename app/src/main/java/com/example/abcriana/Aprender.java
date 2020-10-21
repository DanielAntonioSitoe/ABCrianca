package com.example.abcriana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Aprender extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprender);
    }

    public void aprenderAlfabeto(View view){

        Intent intent = new Intent(this,AprenderAlfabeto.class);
        startActivity(intent);
    }
    public void aprenderNumeros(View view){

        Intent intent = new Intent(this,AprenderNumeros.class);
        startActivity(intent);
    }
    public void aprenderAnimais(View view){

        Intent intent = new Intent(this,AprenderAnimais.class);
        startActivity(intent);
    }
    public void aprenderFrutas(View view){

        Intent intent = new Intent(this,AprenderFrutas.class);
        startActivity(intent);
    }
}
