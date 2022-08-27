package com.example.abcriana;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Desenhar extends AppCompatActivity {
    PaintView paintView;
    String[] strings;
    private TextView textView;
    private int i;
    int color = 1;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        paintView  = new PaintView(this);
        strings = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        setContentView(R.layout.activity_desenhar);
        addContentView(paintView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,550));
        textView =findViewById(R.id.textViewDesenhar);
        textView.setText(strings[i]);
        button = findViewById(R.id.button7);
        button.setBackgroundColor(Color.GRAY);button.setText("Castanho");
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
    public void mudarCor(View view){
        if(color==8){
            color=1;
        }
        Paint brush = paintView.getBrush();
        button = findViewById(R.id.button7);
        switch (color){
            case 1:brush.setColor(Color.MAGENTA);button.setBackgroundColor(Color.MAGENTA);button.setText("Magenta");break;
            case 2:brush.setColor(Color.BLACK);button.setBackgroundColor(Color.BLACK);button.setText("Preto");break;
            case 3:brush.setColor(Color.BLUE);button.setBackgroundColor(Color.BLUE);button.setText("Azul");break;
            case 4:brush.setColor(Color.YELLOW);button.setBackgroundColor(Color.YELLOW);button.setText("Amarelo");break;
            case 5:brush.setColor(Color.GRAY);button.setBackgroundColor(Color.GRAY);button.setText("castanho");break;
            case 6:brush.setColor(Color.RED);button.setBackgroundColor(Color.RED);button.setText("Vermelho");break;
            case 7:brush.setColor(Color.GREEN);button.setBackgroundColor(Color.GREEN);button.setText("Verde");break;
        }
        color++;
        paintView.setBrush(brush);
        paintView.reset();
    }
    public void prev(View view){
        try {
            if(i!=0){
                --i;
            }
            textView.setText(strings[i]);
            paintView.reset();
        }catch (Exception e){
            i=-1;
            next(view);
        }
    }
}
