package com.example.abcriana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Teste extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);
    }
    public void testeAlfabeto(View view){

        Intent intent = new Intent(this,TesteAlfabeto.class);
        startActivity(intent);
    }
    public void testeNumeros(View view){

        Intent intent = new Intent(this,TesteNumeros.class);
        startActivity(intent);
    }
    public void testeAnimais(View view){

        Intent intent = new Intent(this,TesteAnimais.class);
        startActivity(intent);
    }
    public void testeFrutas(View view){

        Intent intent = new Intent(this,TesteFrutas.class);
        startActivity(intent);
    }
}
