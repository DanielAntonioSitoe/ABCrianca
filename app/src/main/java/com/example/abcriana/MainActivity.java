package com.example.abcriana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void aprender(View view){
        Intent intent = new Intent(this,Aprender.class);
        startActivity(intent);
    }

    public void teste(View view){

        Intent intent = new Intent(this,Teste.class);
        startActivity(intent);
    }
    public void sobre(View view){

        Intent intent = new Intent(this, Sobre.class);
        startActivity(intent);
    }
    public void desenhar(View view){

        Intent intent = new Intent(this, Desenhar.class);
        startActivity(intent);
    }
}
