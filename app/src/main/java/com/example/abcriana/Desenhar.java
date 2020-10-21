package com.example.abcriana;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Desenhar extends AppCompatActivity {
    PaintView paintView;
    String[] strings;
    private TextView textView;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        paintView  = new PaintView(this);
        strings = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        setContentView(R.layout.activity_desenhar);
        addContentView(paintView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,550));
        textView =findViewById(R.id.textViewDesenhar);
        textView.setText(strings[i]);
    }

    public void next(View view){
        try {
            i++;
            textView.setText(strings[i]);
            paintView.reset();
        }catch (Exception e){
            i=-1;
            next(view);
        }
    }
    public void prev(View view){
        try {
            if(i!=0){
                --i;
            }
            textView.setText(strings[i]);
        }catch (Exception e){
            i=-1;
            next(view);
        }
    }
}
