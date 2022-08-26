package com.example.abcriana;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.Locale;

public class AprenderFrutas extends AppCompatActivity {
    String[] strings;
    int i = 0;
    private TextToSpeech mTTs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprender_frutas);
        strings = new String[]{"Ananás", "Banana","Laranja","Limão", "Maçã","Melão","Ovo", "Tomate"};
        mTTs = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    Locale locale = new Locale("PT", "PT");
                    int result = mTTs.setLanguage(locale);
                    speek(null);
                }else {
                    Log.e("TTS","Falha ao Iniciar!");
                }
            }
        });
        mostrar();
        speek(null);
    }
    public void next(View view){
        i++;
        if (i>7){
            i=0;
        }
        mostrar();
        speek(null);
    }

    public void prev(View view){
        if(i!=0){
            --i;
        }
        mostrar();
        speek(null);
    }
    private void mostrar(){
        ImageView imageView = findViewById(R.id.imageViewAprFrutas);

        switch (i){
            case 0:imageView.setBackgroundResource(R.drawable.ananas);break;
            case 1:imageView.setBackgroundResource(R.drawable.banana);break;
            case 2:imageView.setBackgroundResource(R.drawable.laranja);break;
            case 3:imageView.setBackgroundResource(R.drawable.limao);break;
            case 4:imageView.setBackgroundResource(R.drawable.maca);break;
            case 5:imageView.setBackgroundResource(R.drawable.melao);break;
            case 6:imageView.setBackgroundResource(R.drawable.ovo);break;
            case 7:imageView.setBackgroundResource(R.drawable.tomate);break;
        }
    }
    public void speek(View view){
        String text = strings[i];
        mTTs.speak(text, TextToSpeech.QUEUE_FLUSH,null);
    }

    @Override
    protected void onDestroy() {
        if(mTTs!=null){
            mTTs.stop();
            mTTs.shutdown();
        }
        super.onDestroy();
    }

}
